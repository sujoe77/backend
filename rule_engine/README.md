# Decision Tree based rule-engine implementation

Here described a decision tree based rule-engine, include the problem it will solve, the design considerations and implementation, and what we can improve in the future, but due to the limit of time and resources, we did not do.

Thank you for taking time to read this, please kindly let me know if there is something I can improve. :)

## 1. The Problem

When we develop applications, from time to time we see problems with similar pattern. For example, given an order, apply some actions according to its price, products, physical or virtual, or given a payment, do something if it is US dollar, do something else if it is Euro, reject if it related to some country or people on the black list etc.

This kind of problems, for a more generic perspective is a "[Statistical classification](https://en.wikipedia.org/wiki/Statistical_classification)" problem, as described in its definition.

> In statistics, classification is the problem of identifying to which of a set of categories (sub-populations) a new observation belongs, on the basis of a training set of data containing observations (or instances) whose category membership is known.

For our case, instead of checking against all the rules for each order, we will do the following.

1. create order categories
2. decide actions to each category
3. design a logic can
   1) classify an order into a category
   2) apply actions of the category to the order

after analysis of the rules we have 5 categories:

| Category | Description |Actions |
|------------|----------|-----------|
| Books | physical, book | packing slip for shipping, packing slip for royalty, commission payment|
| Video - learn to ski|physical, video | packing slip for shipping, commission payment, "First Aid"|
| Video - other | physical, video |  packing slip for shipping, commission payment |
| Membership - Activate | virtual, membership activate | activate, Email |
| Membership - Update | virtual, membership update | activate, update, Email |

## 2. Design

### 2.1 Design goals
Our design goals should be a system with:
* Extensibility
  - the logic model should be generic enough and flexible, so new logic can be added with little effort
  
* Consistency
  - we should process orders in the order they are coming.
  - exactly-once, same order should not processed twice.

* Fault tolerant
  - if something is wrong, we should be able to continue from where we failed
  
* Scalability
  - it should be easy to handle more orders simply by add more servers
  - ideally, some components with heavy load should be able to scale independently
  
* Performance
  - through-put, should be able to handle as more orders as we can
  - latency, the latency should be small
  
* Usability
  - the system should be easy to understand and use

### 2.1 Generic model
The most challenging and important part of this kind of solution is to use a more generic model, for the reasons below:

1. Flexibility

    it should be easy to add new functionality in the future, since it solve a category of problems instead of a specific one.

2. Easy to use and maintain

    one example is Apache Spark, which can deal with batch, stream and interactive workloads, make it easier to develop and use than 3 independent systems.

3. Reuse existing tools and methodologies
    
    if the model is generic, it is more likely we will find more tools and components we can reuse. for example, there are many models in Machine Learning for classification problem.

### 2.2 Relational model vs Decision Tree


There are 2 approaches we can implement this engine

1. Rule Table based
2. Decision Tree based

for Rule Table based approach, we can have a rule table like below:

| ID | Type | Product | Name | Packing_Slip | Commission_Payment | Email | Membership_action |
|-----|-----|-----|-----|-----|-------|------|------|
| 1 | Physical | Book | Book1 | shipping,royalty | Y | N | |
| 2 | Physical | Video | Learn to ski | shipping, "First Aid" | Y | N | |
| 2 | Virtual | Membership - update | club1 member | | Y | Y | U,A |

This implementation is straight forward for database user, but it has its limitations.

1. Relation model not fit well
   
   Relation model is more suitable for Entity and Relationship model, not for represent a decision logic.

2. Difficult to extend
   
   for example, when we want to add new product "Video Game", and it has its own "game type", and we need to add some promotion to certain kind of game, we have to add more columns to the rule table, and need to consider what's the default value of these columns, and as more and more columns added, the number of rules we have to configure can increase exponentially. we also need to consider apply these rules in the correct order and they must not conflict with each other.

3. Difficult to use

   when we have many rules in the table, it becomes difficult to understand and maintain, especially for user without database and good math and logic knowledge.

4. Efficiency

   when we implement a rule-engine, we need to go through all rules in the correct order, and from the first to the very last, since we do not know if the last rule will apply, as we mentioned before it could be many rules in the table, it is not efficient implementation.

The inspiration of using decision tree for this problem come from:

1. Machine Learning
   
   in Machine Learning, there is a category of model called ["Decision Trees"](https://en.wikipedia.org/wiki/Decision_tree), it is powerful as well as easy to understand.


2. Tree data structure
   
   The tree and graph data structure has been widely used in computer science and most of them with good efficiency.

3. Stream systems and data analytic platform

  many data analytic platforms, also use graph as its underlying data model. our problem had much in common with these kind of data processing tasks.

According to the requirements, we got a decision tree below:

![decision tree](https://github.com/sujoe77/backend/blob/master/rule_engine/img/rule-engine.jpg?raw=true "")

comparing with "Rule Table" solution before, decision tree solution has benefits as below:

1. Easy to understand
2. Efficient to run
3. Extensible

### 2.3 Stream processing

We should also consider design the rule-engine as stream based, because:

1. Nature of Order data
   
   Orders are more like continue and unbounded data

2. Lower latency

   comparing with batch style processing, stream systems has lower latency

3. Order of processing

   if we use log-based messaging like Kafka, we can guarantee the processing order thus maintain strong consistency 

Rule Engine can read all orders from a Kafka stream, process orders one by one, and finally write to database. we can scale the system by adding more kafka partitions and rule engine nodes.

we can keep a checkpoint for successfully processed orders, when something goes wrong, we can replay it from Kafka.

we can use consistent hashing to load balanced orders, make sure a order with same id always go to the same rule engine node, together with Order id we make sure we process the order exactly-once.

We may need coordination service like Zookeeper

## 3. Implementation

### 3.2 Run the engine
in main method Class: "com.zs.rule.TreeRuleEngine" you will see how it works, it initialize the rule engine first, and processing a list of different orders, for each order, rule engine will do something according to rules.

### 3.1 The code

* Entities
  
  all entity classes are in package: "com.zs.rule.entity", we should keep in mind "programming to interface instead of implementations"

* Decision Tree

   Decision tree classes are in package: "com.zs.rule.tree".
   There are 2 category of classes in this package:
   1. Decision
   
      it represent tree node which make a decision, they will check the order received and return a true or false, for example, PhysicalProductDecision will return true for Book but false for Membership.

      each Decision node has 2 sub nodes, 
      if the predict method return true, order will be passed to its "trueNode" for further processing, if false "falseNode"

   2. Action
      
      They will do something to the order, for example activate membership or send Email etc.

* Tests

  There is no logic in entity classes, all tests are against decision tree classes. for each Decision class and Action class, we verify it works as expected.

  also on the whole engine level, we use a list of orders, to test the engine works as expected.

  
### 3.2 Deployment

We should deploy each engine instance in Docker on the cloud, or as a server-less service.

Each engine instance is a consumer of Kafka stream which as the source of the orders, when order is processed they should be write into another Kafka stream or database.

## 4. What's next

* Load engine configuration from json
  
  we should be able to load a rule engine instance from a configuration, like json file, instead of do it with code like in main method.

* Tree editing tools

  we can provide a tree editing tool, so user can edit the configuration.

* Reliability 

  Since we deploy the solution to cloud, so we need to consider reliability patterns like retry, timeout etc, to make sure the system is resilient.

* Monitoring

  we should add more monitoring, like latency, so it be easy for us to locate the problem and for future improvement.

* Other

  As work load increase, we may have some other things need to improve in scalability, performance, resilience etc.