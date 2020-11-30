Here described design consideration, implementation, and what we can improve in the future, but due to the limit of time resources, we did not do.

# 1. Implementation

## 1.0 Run the service
Everything is in one jar, services can run either in IDE, or from command line with main calsses specified with dependencies.
No need to deploy to tomcat or JEE server.

REST service with port 8080, gPRC port 10001.

* Main classes
```
    com.zs.prime.rest.RestServer    ->  main class for proxy-service
    com.zs.prime.grpc.PrimeServer   ->  main class for gRPC service     
```

## 1.1 Packages
The code has been arranged in packages as below:

* src/main/proto/prime/prime.proto
    the proto file
    
* com.zs.prime.cache

    a local cache

* com.zs.prime.grpc

    gRPC serer and client

* com.zs.prime.math

    the class calculate primes

* com.zs.prime.rest

    proxy-service for REST

* com.zs.prime.rest.error

    error handler for REST service

## 1.2 Dependencies
Dependencies we used are as below:
* Jersey, for REST service
* Jetty for embedded http server
* grpc-alts, protoc for gRPC
* failSafe for reliability patterns [5]
* testng, jmockit, joor for testing

we also consider using Spring-boot or Play, but Jersey with embedded Jetty seems more light-weight.

## 1.3 Prime number algorithm

There is a classic algorithm for the prime number problem, "Sieve of Eratosthenes" [1], The time complexity of this algorithm is O(n log log n).
So we won't re-invent the wheels here, just use it.

## 1.4 Testing

Test classes as below, ProxyServiceTest is integration test, others are unit tests.
```
com.zs.prime.rest.ProxyServiceTest    ->  Test for proxy service in REST
com.zs.prime.cache.LocalCacheTest  ->  test for local cache
com.zs.prime.grpc.PrimeClientTest   ->  gRPC client test
com.zs.prime.grpc.PrimeServerTest   ->  gRPC server test
com.zs.prime.EratosthenesTest   ->  prime algorithm test
```


# 2. Design considerations

## 2.1 Consistency
This two services are read-only, no transaction, or Linearizability [9] required.
the only place we should pay attention to, is in the "com.zs.prime.cache.LocalCache", we should consider race condition when one thread will write and the other want to read situation.
our workload is read-intensive, so a concurrent collection should be good enough. 
we use ConcurrentHashMap, since it provide a thread-safe and efficient solution to our cache use case.
we can also consider using lock or synchronous, or AtomicReference, but ConcurrentHashMap provided better performance than Lock, and more useful functions than AtomicReference.     

## 2.2 Availability
Availability can be achieved by replication or load balancing, 
since our service is simple and stateless, availability should be simple we can just add more node as we need.  

* Cache
    
    Our cache (com.zs.prime.cache.LocalCache) helps improve the availability, for example, since we have cache in proxy services, even backend gPRC service is down, 
    we can still serve some requests from cache.
    
* Reliability Patterns
    
    We have re-try and timeout when calling gPRC service.
    ```
    FailsafeExecutor<Object> executor = Failsafe.with(getRetryPolicy(), getTimeOut());
    response = executor
        .getAsync(() -> newBlockingStub(channel[0]).getPrimes(request))
        .get();
    ```

    Distributed system are complex with problems like unreliable network, replication lag, different clock etc.
    Netflix have a set of tools to dealing with this, like hystrix. here we use FailSafe [5], a light-weight framework to mitigate these problems.  

## 2.3 Fault tolerance
* Validation and exception handling

	In proxy-service, we will validate the number user input, if it is less than 0, we will reject the request.
	in package "com.zs.prime.rest.error", we have error handler classes each handle a specific of exception, and give user a meaningful feedback.
	Play framework has something similar.
	
* Communicate errors

    we use error code and error message to communicate error between proxy-service and gRPC service.
    ```
    message PrimeResponse {
      repeated int32 prime = 1;
      int32 errorCode = 2;
      string errorMessage = 3;
    }
    ```

* Stateless modules

    All of our classes are stateless, i.e. only with in-mutable fields.
    Stateless services (classes) are preferred because of determinism, efficiency and resiliency, functional programming support this idea very well. 
    according to Netflix[4], their stateless services can be recovered in seconds, comparing to hours or longer for stateful ones.    

* Disaster Recovery

    we initialize our cache with values instead of empty, since need to consider the cache miss when start up.  
    
    ```
      private static final ConcurrentHashMap<String, int[]> cache = new ConcurrentHashMap<String, int[]>() {{
          put(KEY, new int[]{1, 2, 3, 5, 7});
      }};
    ```
    in real case, we can consider save cache data into a file, and reload into cache when startup.
    So we have less cache miss from the beginning. in the worst cases, cache missing can even crash the backend service.


## 2.4 Performance
There are 2 things we should consider throughput and latency.

### 2.4.1 Throughput

The throughput can be improved by using load balancing, multi-thread, asynchronous.
REST services and gRPC by nature are multi-threaded, we need to pay attention to how to optimized settings in containter.
we use asynchronous when calling gRPC from proxy service, this also help increase the throughput and responsiveness.

```
response = executor
    .getAsync(() -> newBlockingStub(channel[0]).getPrimes(request))
    .get();
```

our use of cache also help improve the throughput, and reduce IO and the workload of backend.

make each "thread" more light weight also help improve the  

### 2.4.2 Latency

Latency should be monitored, so some performance problem can be found. we recorded some of our latencies in log.

```
logger.log(INFO, format("gRPC service return: %s, errorCode: %d, errorMessage: [%s], latency: %d milli-seconds",
```
cache can be used to reduce latency, since we do not need to call gRPC service.
Since CPU cache is much faster than memory, do computing can be faster than loading from cahce.

we try to use some techniques to reduce the memory usage, exp. use primary type than classes, array instead of collection, make class stateless etc.
but this can be a very big topic, can refer to [3].

## 2.5 Scalability
Our service ares stateless, should be easy to scale out by adding more nodes, also we may need consider using distributed cache like Redis.

When concurrent connections numbers are very high, we need to consider limitation of system settings, 
for example, max number of file we can open etc, we may also bypass the OS when doing IO. see C10K problem. [7]

Another thing is Java thread is heavy, golang and Erlang do it better.

# 3. What's Next
There are something we can also consider, if we want to further improve the service, 
for example, better performance, scalability etc. just list some of them here

* Calculate Primes in range

	in Eratosthenes, every time we calculate primes from 1 to N, we can improve the algorithm, to enable calculate for a range, like 400 to 600.
	for example, first time we got primes from 1 to 400, next time when N is 600, instead of start from 1, we just calculate 400 to 600 and combine with result before. 
	this can also be used when we want to do this calculation in parallel,  like mentioned later in "multi-thread on gRPC services"

* Multi-thread on gRPC service

    if the number is very big, to make a maximum use of multiple cores, we can consider use each thread (node) to compute range of data, similar to the idea of Map/Reduce.
    for example, if we need to get primes less than 10000, and we have 4 threads, we use the 1st thread got 1 to 2500, 2nd thread got 2501 to 5000 etc.
    finally we combine the result. 
    But multi-thread not necessarily faster, we need to consider cost of the context switching and IO.

* Backend process do pre-computing

    some system or database use backend processes to do backend optimization and maintenance work. JVM GC is an example.
    we can borrow this idea, when service is idle, we can pre-compute some values and save in cache for future use.

* bitmap to reduce the size of data

    we can use bitmap to map integers to bit, for example instead of integer array [1,2,3,5,7] we can save it as [01010111]
    then we can use one Java Integer to save prime number from 1 to 31.

* 1 proxy service, multiple gRPCs
    
    so we can
    - distributed the workload    
    - fault tolerant, retry on be routed to available nodes

* Distributed cache like redis


 
# 4. References
1. Sieve of Eratosthenes https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
2. Release It!, 2nd Edition. by Michael T. Nygard. Released January 2018. Publisher(s): Pragmatic Bookshelf. ISBN: 9781680502398.
3. How to write memory efficient Java code    https://www.youtube.com/watch?v=f2aNWtt0QRo&t=607s&ab_channel=OracleDevelopers
4. Mastering Chaos - A Netflix Guide to Microservices   https://www.youtube.com/watch?v=CZ3wIuvmHeM&ab_channel=InfoQ
5. Failsafe https://jodah.net/failsafe/
6. Fallacies of distributed computing https://en.wikipedia.org/wiki/Fallacies_of_distributed_computing
7. C10K, https://en.wikipedia.org/wiki/C10k_problem
8. bitmap, https://en.wikipedia.org/wiki/Bitmap
9. Linearizability, https://en.wikipedia.org/wiki/Linearizability