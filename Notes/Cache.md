https://coolshell.cn/articles/17416.html

There are 4 commonly used cache updating patterns:

* Cache aside, 
* Read through, 
* Write through, 
* Write behind caching

4 scenarios (state, if see it as a state machine) to handle, write back mode has more.

* read, cache hit
* read, cache miss
* write, cache hit
* write, cache miss

2 threads

* client thread
* cache thread

2 modes

* cache mode, cache just a piece of memory
* cache is a data store service

How to update cache and DB

* the order
* when to update
* who, which thread

# Cache aside

## How it works

At the beginning, cache is just a piece of memory, driven by client thread.

In order to maintain the consistency between DB and cache, we need to know if cache is consistent with DB, instead of comparing it every time, we introduce a boolean flag to represent if cache is up to date, so called cache hit.

Regarding how to update, if we write cache first, we may lose changes, DB is still the main store of the data, so we update DB first, cache later.

after DB updated, if object is not in cache, we do nothing. if object in cache already, instead of update cache immediately, we just update the flag to invalidate the cache, which is cheaper.

when a cache reading coming, which meets cache invalid, the client thread will read DB first, and write to cache.


![](img/cache_aside.png)
![](img/cache_aside2.png)

4 states

    S2 - read, cache miss

    1. read from DB
    2. update cache

    S3

    1. write to DB
    2. invalidate cache

    S4, update DB only

    client thread do above.

* a typical error
* How it works
* paper of Facebook, question on Quora
* potencial problem
    - how to avoid

## A potential concurrency issue

As described in the article, a problem may occure like below:



# Read through

"Cache aside" use client thread to update cache, may have concurrency issues.

Read / Write through use server side thread to update cache, avoid concurrency problem.

read through is similar to cache aside, the difference is cache thread update cache and DB.

![](img/read-through.png)

# Write through

S2 is same with read through.

*S3 update Cache, then DB

    this is faster then read through, but with weaker consistency

S4 update DB only

![](img/write_through.png)

# Write back

more complex, update cache only, update DB async. in batch

![](img/write_back.png)


# Ref

article on coolshell https://coolshell.cn/articles/17416.html

6.824 cache consistency

Facebook paper

Quora question

Cache wikipedia

    https://en.wikipedia.org/wiki/Cache_(computing)

    https://en.wikipedia.org/wiki/Cache_coherence