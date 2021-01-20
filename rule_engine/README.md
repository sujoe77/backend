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
2. Tree data structure
3. Stream systems and data analytic platform

According to the requirements, we got a decision tree below:

### 2.3 Decision Tree based 
![decision tree](http://./img/rule_engine.jpg "rule-engine.jpg")

## 3. Implementation

## What's next
