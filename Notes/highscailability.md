Reading list from http://highscalability.com/all-time-favorites/, reorgnized in topics

# 1. Resources<br/>

[Stuff the Internet Says on Scalability](http://highscalability.com/blog/category/hot-links)(scalability)<br/>
[Useful Scalability Blogs](http://highscalability.com/blog/category/blog)(scalability)<br/>
[Hacker News](http://ycombinator.com)<br/>
[reddit](https://www.reddit.com/)<br/>

# 2. Strategies

[What Can The Amazing Race To The South Pole Teach Us About Startups?](http://highscalability.com/blog/2013/8/19/what-can-the-amazing-race-to-the-south-pole-teach-us-about-s.html)(strategy)<br/>
[Google Strategy: Tree Distribution Of Requests And Responses](http://highscalability.squarespace.com/blog/2011/2/1/google-strategy-tree-distribution-of-requests-and-responses.html)(strategy)<br/>
[Strategy: Disk Backup For Speed, Tape Backup To Save Your Bacon, Just Ask Google](http://highscalability.squarespace.com/blog/2011/3/24/strategy-disk-backup-for-speed-tape-backup-to-save-your-baco.html)(devops)(strategy)<br/>
[Aztec Empire Strategy: Use Dual Pipes In Your Aqueduct For High Availability](http://highscalability.com/blog/2011/3/28/aztec-empire-strategy-use-dual-pipes-in-your-aqueduct-for-hi.html)(strategy)<br/>
[Heroku Emergency Strategy: Incident Command System And 8 Hour Ops Rotations For Fresh Minds](http://highscalability.com/blog/2011/4/27/heroku-emergency-strategy-incident-command-system-and-8-hour.html)(devops, strategy)<br/>
[7 Scaling Strategies Facebook Used To Grow To 500 Million Users](http://highscalability.com/blog/2010/8/2/7-scaling-strategies-facebook-used-to-grow-to-500-million-us.html)(strategy)<br/>

# 3. DevOps

[Heroku Emergency Strategy: Incident Command System And 8 Hour Ops Rotations For Fresh Minds](http://highscalability.com/blog/2011/4/27/heroku-emergency-strategy-incident-command-system-and-8-hour.html)(devops)<br/>

[Paper: Large-Scale Cluster Management At Google With Borg](http://highscalability.com/blog/2015/4/16/paper-large-scale-cluster-management-at-google-with-borg.html)(devops,paper)<br/>
[10 Things Bitly Should Have Monitored](http://highscalability.com/blog/2014/1/29/10-things-bitly-should-have-monitored.html)(devops)<br/>
[Log Everything All The Time](http://highscalability.com/blog/2007/8/30/log-everything-all-the-time.html)(devops)<br/>
[Vinted Architecture: Keeping A Busy Portal Stable By Deploying Several Hundred Times Per Day](http://highscalability.com/blog/2015/2/9/vinted-architecture-keeping-a-busy-portal-stable-by-deployin.html)(devops)<br/>

[The Always On Architecture - Moving Beyond Legacy Disaster Recovery](http://highscalability.com/blog/2016/8/23/the-always-on-architecture-moving-beyond-legacy-disaster-rec.html)(devops,Reliability,google)<br/>

article on a paper from Google: High-Availability at Massive Scale: [Building Google’s Data Infrastructure for Ads](https://static.googleusercontent.com/media/research.google.com/en//pubs/archive/44686.pdf) that explains their history with Always On. It seems appropriate. Here's the article.

The main idea of the paper is that the typical failover architecture used when moving from a single datacenter to multiple datacenters doesn’t work well in practice. What does work, where work means using fewer resources while providing high availability and consistency, is a natively multihomed architecture:

# 4. Architecture<br/>

[Ask HighScalability: Choose An Async App Server Or Multiple Blocking Servers?](http://highscalability.com/blog/2015/8/24/ask-highscalability-choose-an-async-app-server-or-multiple-b.html)(async)<br/>

[Building Globally Distributed, Mission Critical Applications: Lessons From The Trenches Part 2](http://highscalability.com/blog/2015/9/2/building-globally-distributed-mission-critical-applications.html)(lessons,strategy,caseStudy)<br/>

I’ve walked through a wide variety of lessons we’ve learned building and scaling distributed, mission critical systems. You’ll never get everything right from the beginning, but you can put yourself in a good position to react gracefully and efficiently as your company grows.

[Architecting Backend For A Social Product](http://highscalability.com/blog/2015/7/22/architecting-backend-for-a-social-product.html)(lessons,strategy,caseStudy)<br/>

This is aimed towards taking you through key architectural decisions which will make a social application a true next generation social product. The proposed changes addresses following attributes; a) availability b) reliability c) scalability d) performance and flexibility towards extensions (not modifications)

[Using Gossip Protocols For Failure Detection, Monitoring, Messaging And Other Good Things](http://highscalability.com/blog/2011/11/14/using-gossip-protocols-for-failure-detection-monitoring-mess.html)(distributed,consistency)<br/>

[How Can We Build Better Complex Systems? Containers, Microservices, And Continuous Delivery.](http://highscalability.com/blog/2015/4/27/how-can-we-build-better-complex-systems-containers-microserv.html)(lessons,microservice)<br/>

Poppendieck, co-author of the ‘Lean Software Development’ book series, opened the talk by stating that software developers create complex systems. As Fred Brooks identified in his seminal book, ‘The Mythical Man Month’, not only are software systems complex, but the complexity scales non-linearly with size. Poppendieck stated that one of the core goals of the talk was to explore the notion that a ‘good' complex system has the properties of 'low fiction' and limited risk. The remainder of the talk examined this from the perspective of the historical evolution of hardware and software, software architecture, engineering principles, organisational structure, and designing for reliability.

[Did The Microsoft Stack Kill MySpace?](http://highscalability.com/blog/2011/3/25/did-the-microsoft-stack-kill-myspace.html)<br/>
[Aeron: Do We Really Need Another Messaging System?](http://highscalability.com/blog/2014/11/17/aeron-do-we-really-need-another-messaging-system.html)<br/>
[This Stuff Isn't Taught, You Learn It Bit By Bit As You Solve Each Problem.](http://highscalability.com/blog/2011/2/23/this-stuff-isnt-taught-you-learn-it-bit-by-bit-as-you-solve.html)(lessons,read)<br/>
[What Do You Believe Now That You Didn't Five Years Ago? Centralized Wins. Decentralized Loses.](http://highscalability.com/blog/2018/8/22/what-do-you-believe-now-that-you-didnt-five-years-ago-centra.html)(distributed)<br/>
[Paper: The End Of An Architectural Era (It’s Time For A Complete Rewrite)](http://highscalability.com/blog/2009/4/16/paper-the-end-of-an-architectural-era-its-time-for-a-complet.html)(paper)<br/>
[AppBackplane - A Framework For Supporting Multiple Application Architectures](http://highscalability.com/blog/2013/3/25/appbackplane-a-framework-for-supporting-multiple-application.html)(hardware)<br/>
[Ask HS: What Will Programming And Architecture Look Like In 2020?](http://highscalability.com/blog/2012/12/23/ask-hs-what-will-programming-and-architecture-look-like-in-2.html)<br/>

[Beyond Threads And Callbacks - Application Architecture Pros And Cons](http://highscalability.com/blog/2013/3/18/beyond-threads-and-callbacks-application-architecture-pros-a.html)(multithread,mustRead)<br/>

how different programming languages deal with thread, callbacks. like golang, Erlang.

[Vinted Architecture: Keeping A Busy Portal Stable By Deploying Several Hundred Times Per Day](http://highscalability.com/blog/2015/2/9/vinted-architecture-keeping-a-busy-portal-stable-by-deployin.html)(devops)<br/>
[Getting Things Right: A Look At Centralized Vs Decentralized Systems Through The Eyes Of Instant Replay](http://highscalability.com/blog/2014/9/15/getting-things-right-a-look-at-centralized-vs-decentralized.html)(distributed)<br/>
[10 Common Server Setups For Your Web Application](http://highscalability.com/blog/2014/9/10/10-common-server-setups-for-your-web-application.html)(read,pattern)<br/>

If you need a good overview of different ways to setup your web service then Mitchell Anicas has written a good article for you: 5 Common Server Setups For Your Web Application.

We've even included a few additional possibilities at no extra cost.

## 4.1 Company<br/>

### 4.1.1 Google

[Google Architecture](http://highscalability.com/google-architecture)<br/>
[The Three Ages Of Google - Batch, Warehouse, Instant](http://highscalability.squarespace.com/blog/2011/8/29/the-three-ages-of-google-batch-warehouse-instant.html)<br/>
[Google Finds: Centralized Control, Distributed Data Architectures Work Better Than Fully Decentralized Architectures](http://highscalability.com/blog/2014/4/7/google-finds-centralized-control-distributed-data-architectu.html)<br/>
[Matt Cutts: 10 Lessons Learned From The Early Days Of Google](http://highscalability.com/blog/2015/2/4/matt-cutts-10-lessons-learned-from-the-early-days-of-google.html)(lesson)<br/>
[YouTube Architecture](http://highscalability.com/youtube-architecture)<br/>

### 4.1.2 Facebook

[Facebook Timeline: Brought To You By The Power Of Denormalization](http://highscalability.com/blog/2012/1/23/facebook-timeline-brought-to-you-by-the-power-of-denormaliza.html)<br/>
[How Facebook Tells Your Friends You're Safe In A Disaster In Under Five Minutes](http://highscalability.com/blog/2015/9/28/how-facebook-tells-your-friends-youre-safe-in-a-disaster-in.html)<br/>
[Facebook](http://highscalability.com/blog/category/facebook)<br/>
[The Instagram Architecture Facebook Bought For A Cool Billion Dollars](http://highscalability.com/blog/2012/4/9/the-instagram-architecture-facebook-bought-for-a-cool-billio.html)<br/>
[The WhatsApp Architecture Facebook Bought For \$19 Billion](http://highscalability.com/blog/2014/2/26/the-whatsapp-architecture-facebook-bought-for-19-billion.html)<br/>

### 4.1.3 Instagram

[This. Just. This.](http://highscalability.com/blog/2015/7/16/this-just-this.html)<br/>
[Instagram Architecture Update: What’s New With Instagram?](http://highscalability.com/blog/2012/4/16/instagram-architecture-update-whats-new-with-instagram.html)<br/>
[Instagram Improved Their App's Performance. Here's How.](http://highscalability.com/blog/2014/9/29/instagram-improved-their-apps-performance-heres-how.html)<br/>
[5 Lessons From 5 Years Of Building Instagram](http://highscalability.com/blog/2015/10/21/5-lessons-from-5-years-of-building-instagram.html)(lesson)<br/>

### 4.1.4 Netflix

[Netflix: What Happens When You Press Play?](http://highscalability.com/blog/2017/12/11/netflix-what-happens-when-you-press-play.html)(netflix,caseStudy,mustRead)<br/>

Netflix architecture, strategy overview.

[A 360 Degree View Of The Entire Netflix Stack](http://highscalability.com/blog/2015/11/9/a-360-degree-view-of-the-entire-netflix-stack.html)(mustRead,netflix,caseStudy)<br/>

Netflix overview

[The Eternal Cost Savings Of Netflix's Internal Spot Market](http://highscalability.com/blog/2017/12/4/the-eternal-cost-savings-of-netflixs-internal-spot-market.html)(netflix,aws,cost)<br/>

How Neflix use AWS spot instances to save cost.


### 4.1.5 Flickr

[Flickr Architecture](http://highscalability.com/flickr-architecture)<br/>
[How To Succeed At Capacity Planning Without Really Trying : An Interview With Flickr's John Allspaw On His New Book](http://highscalability.com/blog/2009/6/29/how-to-succeed-at-capacity-planning-without-really-trying-an.html)<br/>

### 4.1.6 Amazon

[Amazon Architecture](http://highscalability.com/amazon-architecture)<br/>

### 4.1.7 Microsoft

[Plenty Of Fish Architecture](http://highscalability.com/plentyoffish-architecture)<br/>
[Stack Overflow Architecture](http://highscalability.com/blog/2009/8/5/stack-overflow-architecture.html)<br/>

### 4.1.8 Other

[5 Lessons And 8 Industry Changes Over 5 Years As Etsy CTO](http://highscalability.com/blog/2015/9/16/5-lessons-and-8-industry-changes-over-5-years-as-etsy-cto.html)(lesson)<br/>

Kellan Elliott-McCrea, who recently announced he was leaving his job after five successful years as the CTO of Etsy. Kellan wrote a rather remarkable going away post: [Five years, building a culture, and handing it off](http://laughingmeme.org/2015/08/31/five-years-building-a-culture-and-handing-it-off/), brimming with both insight and thoughtful commentary.

This post is just a short gloss of the major points. He goes into more depth on each point, so please read his post.


[Segment: Rebuilding Our Infrastructure With Docker, ECS, And Terraform](http://highscalability.com/blog/2015/10/19/segment-rebuilding-our-infrastructure-with-docker-ecs-and-te.html)<br/>

[Cinchcast Architecture - Producing 1,500 Hours Of Audio Every Day](http://highscalability.com/blog/2012/7/16/cinchcast-architecture-producing-1500-hours-of-audio-every-d.html)<br/>
[Justin.Tv's Live Video Broadcasting Architecture](http://highscalability.com/blog/2010/3/16/justintvs-live-video-broadcasting-architecture.html)<br/>

[StubHub Architecture: The Surprising Complexity Behind The World’s Largest Ticket Marketplace](http://highscalability.com/blog/2012/6/25/stubhub-architecture-the-surprising-complexity-behind-the-wo.html)<br/>
[NYTimes Architecture: No Head, No Master, No Single Point Of Failure](http://highscalability.com/blog/2014/1/13/nytimes-architecture-no-head-no-master-no-single-point-of-fa.html)<br/>

[Ten Lessons From GitHub’s First Year In 2008](http://highscalability.com/blog/2013/9/11/ten-lessons-from-githubs-first-year-in-2008.html)(lesson)<br/>
[Is Node.Js Becoming A Part Of The Stack? SimpleGeo Says Yes.](http://highscalability.com/blog/2011/2/22/is-nodejs-becoming-a-part-of-the-stack-simplegeo-says-yes.html)<br/>

[64 Network DO’s And DON’Ts For Game Engines. Part IIIa: Server-Side](http://highscalability.com/blog/2015/7/15/64-network-dos-and-donts-for-game-engines-part-iiia-server-s.html)<br/>

[Make Any Framework Suck Less With These 10 Insightful Lessons](http://highscalability.com/blog/2014/11/26/make-any-framework-suck-less-with-these-10-insightful-lesson.html)(lesson,frontEnd)<br/>

## 4.2 Resilience

[Here's A 1300 Year Old Solution To Resilience - Rebuild, Rebuild, Rebuild](http://highscalability.com/blog/2014/4/23/heres-a-1300-year-old-solution-to-resilience-rebuild-rebuild.html)(resilience)<br/>
[Resiliency Is The New Normal - A Deep Look At What It Means And How To Build It](http://highscalability.com/blog/2012/12/3/resiliency-is-the-new-normal-a-deep-look-at-what-it-means-an.html)(resilience)<br/>
[Designing For Resiliency Will Be So 2013](http://highscalability.com/blog/2012/12/31/designing-for-resiliency-will-be-so-2013.html)(resilience)<br/>

# 5. Microservices<br/>

[Cell Architectures](http://highscalability.com/blog/2012/5/9/cell-architectures.html)<br/>
[The Great Microservices Vs Monolithic Apps Twitter Melee](http://highscalability.com/blog/2014/7/28/the-great-microservices-vs-monolithic-apps-twitter-melee.html)<br/>

[How To Build Your Property Management System Integration Using Microservices](http://highscalability.com/blog/2016/2/10/how-to-build-your-property-management-system-integration-usi.html)(microservices,caseStudy)<br/>

This is a guest post by Rafael Neves, Head of Enterprise Architecture at ALICE, a NY-based hospitality technology startup. While the domain is Property Management, it's also a good microservices intro.


[Deep Lessons From Google And EBay On Building Ecosystems Of Microservices](http://highscalability.com/blog/2015/12/1/deep-lessons-from-google-and-ebay-on-building-ecosystems-of.html)(lesson)<br/>
[Seven Of The Nastiest Anti-Patterns In Microservices](http://highscalability.com/blog/2015/8/3/seven-of-the-nastiest-anti-patterns-in-microservices.html)<br/>
[The Convergence That Changes Everything: Data Gravity + Containers + Microservices](http://highscalability.com/blog/2015/3/25/the-convergence-that-changes-everything-data-gravity-contain.html)<br/>
[This Is Why Microsoft Won. And Why They Lost.](http://highscalability.com/blog/2014/4/21/this-is-why-microsoft-won-and-why-they-lost.html)<br/>
[Microservices - Not A Free Lunch!](http://highscalability.com/blog/2014/4/8/microservices-not-a-free-lunch.html)<br/>
[Netflix: Continually Test By Failing Servers With Chaos Monkey](http://highscalability.com/blog/2010/12/28/netflix-continually-test-by-failing-servers-with-chaos-monke.html)<br/>

# 6. Cloud<br/>

[That's Not My Problem - I'm Renting Them](http://highscalability.com/blog/2014/10/8/thats-not-my-problem-im-renting-them.html)<br/>
[Startups Are Creating A New System Of The World For IT](http://highscalability.com/blog/2012/5/7/startups-are-creating-a-new-system-of-the-world-for-it.html)<br/>
[Cloud Architecture Revolution](http://highscalability.com/blog/2014/6/5/cloud-architecture-revolution.html)<br/>

This post wants to analyze some key words and concepts, usually used in traditional architectures, redefining them according the standpoint of the cloud.  Understanding the meaning of new words is crucial to grasp the essence of a pure cloud architecture.

* Infrastructure - commodity HW instead of high-end HW
* Scalability - dynamic instead of static
* Availability - resiliency instead of no failure
* Performance - decomposition and distribution instead of stress
* Reliability - MTTR instead of MTBF
* Capacity Planning - scale unit planning instead of  worst case planning
* Enable Scaling - be able to adapt to environmental conditions
* Expect Failure - be able to adopt a resilient attitude

[The Canonical Cloud Architecture](http://highscalability.com/blog/2009/8/7/the-canonical-cloud-architecture.html)(aws,paper)<br/>

The canonical cloud architecture that has evolved revolves around dynamically scalable CPUs consuming asynchronous, persistently queued events. We talked about this idea already in Flickr - Do the Essential Work Up-front and Queue the Rest. The cloud is just another way of implementing the same idea.

Amazon suggests a few applications of the Cloud Architecture as:

---------------------------

SmugMug's Cloud Architecture
AWS pioneer Don MacAskill of SmugMug details how they process high-resolution photos and high-definition video use a cloud hosted queuing architecture in SkyNet Lives! (aka EC2 @ SmugMug).

-----------------------

* [Building GrepTheWeb In The Cloud](https://aws.amazon.com/blogs/aws/white-paper-on/)<br/>
Amazon has published a great couple of articles on building a canonical Cloud Architecture:
    - [Building GrepTheWeb in the Cloud, Part 1: Cloud Architectures](https://aws.amazon.com/articles/building-greptheweb-in-the-cloud-part-1-cloud-architectures/)
    - Building GrepTheWeb in the Cloud, Part 2: Best Practices.


[Cloud Programming Directly Feeds Cost Allocation Back Into Software Design](http://highscalability.com/blog/2009/3/6/cloud-programming-directly-feeds-cost-allocation-back-into-s.html)<br/>

Usually in programming the costs we talk about are time, space, latency, bandwidth, storage, person hours, etc. Listening to the Google folks talk about how one of their explicit design goals was to require programmers to be mindful of operations that will cost money made me realize in cloud programming cost will be another aspect of design we'll have to factor in.

[Machine VM + Cloud API - Rewriting The Cloud From Scratch](http://highscalability.com/blog/2010/10/21/machine-vm-cloud-api-rewriting-the-cloud-from-scratch.html)(paper,OS)<br/>

An introduction on https://mirage.io/<br/>

MirageOS is a library operating system that constructs unikernels for secure, high-performance network applications across a variety of cloud computing and mobile platforms. Code can be developed on a normal OS such as Linux or MacOS X, and then compiled into a fully-standalone, specialised unikernel that runs under a Xen or KVM hypervisor.

[Under Snowden's Light Software Architecture Choices Become Murky](http://highscalability.com/blog/2014/1/8/under-snowdens-light-software-architecture-choices-become-mu.html)(cloud,security,caseStudy)<br/>

Most big enterprise companies are actively working on their AWS rollout now. Most of them are also trying to get an in-house cloud to work, with varying amounts of success, but even the best private clouds are still years behind the feature set of public clouds, which is has a big impact on the agility and speed of product development

## 6.1 Linux

[The Black Magic Of Systematically Reducing Linux OS Jitter](http://highscalability.com/blog/2015/4/8/the-black-magic-of-systematically-reducing-linux-os-jitter.html)<br/>
[Is It Time To Get Rid Of The Linux OS Model In The Cloud?](http://highscalability.com/blog/2012/1/19/is-it-time-to-get-rid-of-the-linux-os-model-in-the-cloud.html)<br/>

## 6.2 AWS

[Strategy: Run A Scalable, Available, And Cheap Static Site On S3 Or GitHub](http://highscalability.com/blog/2011/8/22/strategy-run-a-scalable-available-and-cheap-static-site-on-s.html)<br/>

[The Stunning Scale Of AWS And What It Means For The Future Of The Cloud](http://highscalability.com/blog/2015/1/12/the-stunning-scale-of-aws-and-what-it-means-for-the-future-o.html)<br/>

[The Updated Big List Of Articles On The Amazon Outage](http://highscalability.com/blog/2011/5/2/the-updated-big-list-of-articles-on-the-amazon-outage.html)(SRE)<br/>
[10 Things You Should Know About AWS](http://highscalability.com/blog/2013/11/5/10-things-you-should-know-about-aws.html)<br/>
[The Serverless Start-Up - Down With Servers!](http://highscalability.com/blog/2015/12/7/the-serverless-start-up-down-with-servers.html)<br/>

## 6.3 Google Cloud

[What Google App Engine Price Changes Say About The Future Of Web Architecture](http://highscalability.com/blog/2011/9/7/what-google-app-engine-price-changes-say-about-the-future-of.html)<br/>
[Google AppEngine - A Second Look](http://highscalability.com/blog/2009/2/21/google-appengine-a-second-look.html)<br/>

[How Google Invented An Amazing Datacenter Network Only They Could Create](http://highscalability.com/blog/2015/8/10/how-google-invented-an-amazing-datacenter-network-only-they.html)<br/>
[Google+ Is Built Using Tools You Can Use Too: Closure, Java Servlets, JavaScript, BigTable, Colossus, Quick Turnaround](http://highscalability.com/blog/2011/7/12/google-is-built-using-tools-you-can-use-too-closure-java-ser.html)<br/>
[C Is For Compute - Google Compute Engine (GCE)](http://highscalability.com/blog/2012/7/2/c-is-for-compute-google-compute-engine-gce.html)<br/>
[Google Says Cloud Prices Will Follow Moore’s Law: Are We All Renters Now?](http://highscalability.com/blog/2014/5/14/google-says-cloud-prices-will-follow-moores-law-are-we-all-r.html)<br/>
[Google+ Is Built Using Tools You Can Use Too: Closure, Java Servlets, JavaScript, BigTable, Colossus, Quick Turnaround](http://highscalability.com/blog/2011/7/12/google-is-built-using-tools-you-can-use-too-closure-java-ser.html)<br/>
[What Google App Engine Price Changes Say About The Future Of Web Architecture](http://highscalability.com/blog/2011/9/7/what-google-app-engine-price-changes-say-about-the-future-of.html)<br/>

## 6.4 Netflix

[Netflix: Developing, Deploying, And Supporting Software According To The Way Of The Cloud](http://highscalability.com/blog/2011/12/12/netflix-developing-deploying-and-supporting-software-accordi.html)<br/>

## 6.5 Tripadvisor

[An Epic TripAdvisor Update: Why Not Run On The Cloud? The Grand Experiment.](http://highscalability.com/blog/2012/10/2/an-epic-tripadvisor-update-why-not-run-on-the-cloud-the-gran.html)<br/>

## 6.6 Pinterest

[Pinterest Cut Costs From $54 To $20 Per Hour By Automatically Shutting Down Systems](http://highscalability.com/blog/2012/12/12/pinterest-cut-costs-from-54-to-20-per-hour-by-automatically.html)<br/>

## 6.7 Hardware

[How will memristors change everything?](http://highscalability.com/blog/2010/5/5/how-will-memristors-change-everything.html)(hardware)<br/>
[Busting 4 Modern Hardware Myths - Are Memory, HDDs, And SSDs Really Random Access?](http://highscalability.com/blog/2013/6/13/busting-4-modern-hardware-myths-are-memory-hdds-and-ssds-rea.html)(hardware)<br/>

## 6.8 Book

[Explain the Cloud Like I'm 10](https://smile.amazon.com/Explain-Cloud-Like-Im-10-ebook/dp/B0765C4SNR)<br/>
[Von Neumann Had One Piece Of Advice For Us: Not To Originate Anything.](http://highscalability.com/blog/2015/1/5/von-neumann-had-one-piece-of-advice-for-us-not-to-originate.html)<br/>

# 7. Latency<br/>

[Latency is Everywhere and it Costs You Sales - How to Crush it](http://highscalability.com/blog/2009/7/25/latency-is-everywhere-and-it-costs-you-sales-how-to-crush-it.html)(mustRead)<br/>

[Numbers Everyone Should Know](http://highscalability.com/numbers-everyone-should-know)<br/>
[More Numbers Every Awesome Programmer Must Know](http://highscalability.com/blog/2013/1/15/more-numbers-every-awesome-programmer-must-know.html)<br/>
[Google Pro Tip: Use Back-Of-The-Envelope-Calculations To Choose The Best Design](http://highscalability.com/blog/2011/1/26/google-pro-tip-use-back-of-the-envelope-calculations-to-choo.html)<br/>
[Grace Hopper To Programmers: Mind Your Nanoseconds!](http://highscalability.com/blog/2012/3/1/grace-hopper-to-programmers-mind-your-nanoseconds.html)<br/>

[How Does The Use Of Docker Effect Latency?](http://highscalability.com/blog/2015/12/16/how-does-the-use-of-docker-effect-latency.html)<br/>
[How Can Batching Requests Actually Reduce Latency?](http://highscalability.com/blog/2013/12/4/how-can-batching-requests-actually-reduce-latency.html)<br/>
[The Clever Ways Chrome Hides Latency By Anticipating Your Every Need](http://highscalability.com/blog/2012/6/18/the-clever-ways-chrome-hides-latency-by-anticipating-your-ev.html)<br/>

[Google: Taming The Long Latency Tail - When More Machines Equals Worse Results](http://highscalability.com/blog/2012/3/12/google-taming-the-long-latency-tail-when-more-machines-equal.html)<br/>
[Google On Latency Tolerant Systems: Making A Predictable Whole Out Of Unpredictable Parts](http://highscalability.com/blog/2012/6/18/google-on-latency-tolerant-systems-making-a-predictable-whol.html)<br/>

## 7.1 Realtime<br/>

[DataSift Architecture: Realtime Datamining At 120,000 Tweets Per Second](http://highscalability.com/blog/2011/11/29/datasift-architecture-realtime-datamining-at-120000-tweets-p.html)<br/>
[Facebook's New Real-Time Messaging System: HBase To Store 135+ Billion Messages A Month](http://highscalability.com/blog/2010/11/16/facebooks-new-real-time-messaging-system-hbase-to-store-135.html)<br/>
[Google's Colossus Makes Search Real-Time By Dumping MapReduce](http://highscalability.com/blog/2010/9/11/googles-colossus-makes-search-real-time-by-dumping-mapreduce.html)<br/>
[Update On Disqus: It's Still About Realtime, But Go Demolishes Python](http://highscalability.com/blog/2014/5/7/update-on-disqus-its-still-about-realtime-but-go-demolishes.html)<br/>
[How Uber Scales Their Real-Time Market Platform](http://highscalability.com/blog/2015/9/14/how-uber-scales-their-real-time-market-platform.html)<br/>

# 8. Scailing<br/>

[We Finally Cracked The 10K Problem - This Time For Managing Servers With 2000x Servers Managed Per Sysadmin](http://highscalability.com/blog/2013/11/19/we-finally-cracked-the-10k-problem-this-time-for-managing-se.html)<br/>
[Your Load Generator Is Probably Lying To You - Take The Red Pill And Find Out Why](http://highscalability.com/blog/2015/10/5/your-load-generator-is-probably-lying-to-you-take-the-red-pi.html)<br/>
[StackOverflow Update: 560M Pageviews A Month, 25 Servers, And It's All About Performance](http://highscalability.com/blog/2014/7/21/stackoverflow-update-560m-pageviews-a-month-25-servers-and-i.html)<br/>
[How Next Big Sound Tracks Over A Trillion Song Plays, Likes, And More Using A Version Control System For Hadoop Data](http://highscalability.com/blog/2014/1/28/how-next-big-sound-tracks-over-a-trillion-song-plays-likes-a.html)<br/>
[Evolution Of Bazaarvoice’s Architecture To 500M Unique Users Per Month](http://highscalability.com/blog/2013/12/2/evolution-of-bazaarvoices-architecture-to-500m-unique-users.html)<br/>
[Algolia's Fury Road To A Worldwide API Part 3](http://highscalability.com/blog/2015/7/27/algolias-fury-road-to-a-worldwide-api-part-3.html)<br/>
[Google's Transition From Single Datacenter, To Failover, To A Native Multihomed Architecture](http://highscalability.com/blog/2016/2/23/googles-transition-from-single-datacenter-to-failover-to-a-n.html)(scalability,caseStudy)<br/>
[Why Are Facebook, Digg, And Twitter So Hard To Scale?](http://highscalability.com/blog/2009/10/13/why-are-facebook-digg-and-twitter-so-hard-to-scale.html)<br/>
[A Perfect Fifth Of Notes On Scalability](http://highscalability.com/blog/2012/1/10/a-perfect-fifth-of-notes-on-scalability.html)<br/>
[StackExchange Architecture Updates - Running Smoothly, Amazon 4x More Expensive](http://highscalability.com/blog/2011/10/24/stackexchange-architecture-updates-running-smoothly-amazon-4.html)<br/>
[Pursue Robust Indefinite Scalability With The Movable Feast Machine](http://highscalability.com/blog/2011/9/28/pursue-robust-indefinite-scalability-with-the-movable-feast.html)<br/>
[Peecho Architecture - Scalability On A Shoestring](http://highscalability.com/blog/2011/8/1/peecho-architecture-scalability-on-a-shoestring.html)<br/>
[The FireBox Warehouse Scale Computer In 2020 Will Have 1K Sockets, 100K Cores, 100PB NV RAM, And A 4Pb/S Network](http://highscalability.com/blog/2014/9/17/the-firebox-warehouse-scale-computer-in-2020-will-have-1k-so.html)<br/>
[ESPN's Architecture At Scale - Operating At 100,000 Duh Nuh Nuhs Per Second](http://highscalability.com/blog/2013/11/4/espns-architecture-at-scale-operating-at-100000-duh-nuh-nuhs.html)<br/>
[Planetary-Scale Computing Architectures For Electronic Trading And How Algorithms Shape Our World](http://highscalability.com/blog/2014/2/19/planetary-scale-computing-architectures-for-electronic-tradi.html)<br/>
[The Machine: HP's New Memristor Based Datacenter Scale Computer - Still Changing Everything](http://highscalability.com/blog/2014/12/15/the-machine-hps-new-memristor-based-datacenter-scale-compute.html)<br/>
[Scalability As A Service](http://highscalability.com/blog/2014/12/22/scalability-as-a-service.html)<br/>
[How We Scale VividCortex's Backend Systems](http://highscalability.com/blog/2015/3/30/how-we-scale-vividcortexs-backend-systems.html)<br/>
[HappyPancake: A Retrospective On Building A Simple And Scalable Foundation](http://highscalability.com/blog/2015/2/23/happypancake-a-retrospective-on-building-a-simple-and-scalab.html)<br/>
[How Autodesk Implemented Scalable Eventing Over Mesos](http://highscalability.com/blog/2015/8/17/how-autodesk-implemented-scalable-eventing-over-mesos.html)<br/>
[How Shopify Scales To Handle Flash Sales From Kanye West And The Superbowl](http://highscalability.com/blog/2015/11/2/how-shopify-scales-to-handle-flash-sales-from-kanye-west-and.html)<br/>
[How Does Google Do Planet-Scale Engineering For A Planet-Scale Infrastructure?](http://highscalability.com/blog/2016/7/18/how-does-google-do-planet-scale-engineering-for-a-planet-sca.html)(SRE,caseStudy,scalability)<br/>
[How Facebook Live Streams To 800,000 Simultaneous Viewers](http://highscalability.com/blog/2016/6/27/how-facebook-live-streams-to-800000-simultaneous-viewers.html)<br/>
[How Twitter Handles 3,000 Images Per Second](http://highscalability.com/blog/2016/4/20/how-twitter-handles-3000-images-per-second.html)<br/>
[Elements Of Scale: Composing And Scaling Data Platforms](http://highscalability.com/blog/2015/5/4/elements-of-scale-composing-and-scaling-data-platforms.html)<br/>
[The Secret Of Scaling: You Can't Linearly Scale Effort With Capacity](http://highscalability.com/blog/2014/6/4/the-secret-of-scaling-you-cant-linearly-scale-effort-with-ca.html)<br/>
[Must See: 5 Steps To Scaling MongoDB (Or Any DB) In 8 Minutes](http://highscalability.com/blog/2011/9/13/must-see-5-steps-to-scaling-mongodb-or-any-db-in-8-minutes.html)<br/>
[The Three Ages Of Google - Batch, Warehouse, Instant](http://highscalability.com/blog/2011/8/29/the-three-ages-of-google-batch-warehouse-instant.html)<br/>
[10 EBay Secrets For Planet Wide Scaling](http://highscalability.com/blog/2009/11/17/10-ebay-secrets-for-planet-wide-scaling.html)<br/>
[Vertical Scaling Ascendant - How Are SSDs Changing &nbsp;Architectures?](http://highscalability.com/blog/2012/7/25/vertical-scaling-ascendant-how-are-ssds-changing-architectur.html)<br/>
[Scaling Secret #2: Denormalizing Your Way To Speed And Profit](http://highscalability.com/blog/2007/8/16/scaling-secret-2-denormalizing-your-way-to-speed-and-profit.html)<br/>
[Strategy: Diagonal Scaling - Don't Forget To Scale Out AND Up](http://highscalability.com/blog/2007/11/5/strategy-diagonal-scaling-dont-forget-to-scale-out-and-up.html)<br/>
[GridGain: One Compute Grid, Many Data Grids](http://highscalability.com/blog/2008/9/25/gridgain-one-compute-grid-many-data-grids.html)<br/>
[Scaling Spam Eradication Using Purposeful Games: Die Spammer Die!](http://highscalability.com/blog/2008/10/17/scaling-spam-eradication-using-purposeful-games-die-spammer.html)<br/>
[The Changing Face Of Scale - The Downside Of Scaling In The Contextual Age](http://highscalability.com/blog/2013/3/27/the-changing-face-of-scale-the-downside-of-scaling-in-the-co.html)<br/>
[IDoneThis - Scaling An Email-Based App From Scratch](http://highscalability.com/blog/2012/6/20/idonethis-scaling-an-email-based-app-from-scratch.html)<br/>
[The Secret Of Scaling: You Can't Linearly Scale Effort With Capacity](http://highscalability.com/blog/2014/6/4/the-secret-of-scaling-you-cant-linearly-scale-effort-with-ca.html)<br/>
[Playfish's Social Gaming Architecture - 50 Million Monthly Users And Growing](http://highscalability.com/blog/2010/9/21/playfishs-social-gaming-architecture-50-million-monthly-user.html)<br/>
[Instagram Architecture: 14 Million Users, Terabytes Of Photos, 100s Of Instances, Dozens Of Technologies](http://highscalability.com/blog/2011/12/6/instagram-architecture-14-million-users-terabytes-of-photos.html)<br/>

## 8.1 Patterns<br/>

[7 Design Patterns For Almost-Infinite Scalability](http://highscalability.com/blog/2010/12/16/7-design-patterns-for-almost-infinite-scalability.html)<br/>
[10 Core Architecture Pattern Variations For Achieving Scalability](http://highscalability.com/blog/2011/11/7/10-core-architecture-pattern-variations-for-achieving-scalab.html)<br/>
[8 Commonly Used Scalable System Design Patterns](http://highscalability.com/blog/2010/12/1/8-commonly-used-scalable-system-design-patterns.html)<br/>
[Scaling An AWS Infrastructure - Tools And Patterns](http://highscalability.com/blog/2010/8/16/scaling-an-aws-infrastructure-tools-and-patterns.html)<br/>

## 8.2 Mistake<br/>

[6 Ways To Kill Your Servers - Learning How To Scale The Hard Way](http://highscalability.com/blog/2010/8/23/6-ways-to-kill-your-servers-learning-how-to-scale-the-hard-w.html)<br/>
[Sean Hull's 20 Biggest Bottlenecks That Reduce And Slow Down Scalability](http://highscalability.com/blog/2013/8/28/sean-hulls-20-biggest-bottlenecks-that-reduce-and-slow-down.html)<br/>
[Little’s Law, Scalability And Fault Tolerance: The OS Is Your Bottleneck. What You Can Do?](http://highscalability.com/blog/2014/2/5/littles-law-scalability-and-fault-tolerance-the-os-is-your-b.html)<br/>
[Big List Of 20 Common Bottlenecks](http://highscalability.com/blog/2012/5/16/big-list-of-20-common-bottlenecks.html)<br/>
[The 10 Deadly Sins Against Scalability](http://highscalability.com/blog/2013/6/10/the-10-deadly-sins-against-scalability.html)<br/>
[5 Scalability Poisons And 3 Cloud Scalability Antidotes](http://highscalability.com/blog/2011/9/21/5-scalability-poisons-and-3-cloud-scalability-antidotes.html)<br/>
[42 Monster Problems That Attack As Loads Increase](http://highscalability.com/blog/2013/2/27/42-monster-problems-that-attack-as-loads-increase.html)<br/>

## 8.3 Service<br/>

[Paper: On Designing And Deploying Internet-Scale Services](http://highscalability.com/blog/2008/3/25/paper-on-designing-and-deploying-internet-scale-services.html)(paper)<br/>
[Making The Case For Building Scalable Stateful Services In The Modern Era](http://highscalability.com/blog/2015/10/12/making-the-case-for-building-scalable-stateful-services-in-t.html)<br/>

## 8.4 Lessons &amp; best practice<br/>

[Robert Scoble's Rules For Successfully Scaling Startups](http://highscalability.com/blog/2008/7/18/robert-scobles-rules-for-successfully-scaling-startups.html)<br/>
[Strategy: Three Techniques To Survive Traffic Surges By Quickly Scaling Your Site](http://highscalability.com/blog/2014/3/19/strategy-three-techniques-to-survive-traffic-surges-by-quick.html)(strategy)<br/>
[The Four Meta Secrets Of Scaling At Facebook](http://highscalability.com/blog/2010/6/10/the-four-meta-secrets-of-scaling-at-facebook.html)<br/>
[22 Recommendations For Building Effective High Traffic Web Software](http://highscalability.com/blog/2013/12/16/22-recommendations-for-building-effective-high-traffic-web-s.html)<br/>
[7 Strategies For 10x Transformative Change](http://highscalability.com/blog/2015/8/26/7-strategies-for-10x-transformative-change.html)<br/>
[6 Lessons From Dropbox - One Million Files Saved Every 15 Minutes](http://highscalability.com/blog/2011/3/14/6-lessons-from-dropbox-one-million-files-saved-every-15-minu.html)<br/>
[Matt Cutts: 10 Lessons Learned From The Early Days Of Google](http://highscalability.com/blog/2015/2/4/matt-cutts-10-lessons-learned-from-the-early-days-of-google.html)<br/>
[7 Lessons Learned While Building Reddit To 270 Million Page Views A Month](http://highscalability.com/blog/2010/5/17/7-lessons-learned-while-building-reddit-to-270-million-page.html)<br/>
[Six Lessons Learned The Hard Way About Scaling A Million User System](http://highscalability.com/blog/2014/4/16/six-lessons-learned-the-hard-way-about-scaling-a-million-use.html)<br/>
[Lessons Learned From Scaling Uber To 2000 Engineers, 1000 Services, And 8000 Git Repositories](http://highscalability.com/blog/2016/10/12/lessons-learned-from-scaling-uber-to-2000-engineers-1000-ser.html)<br/>
[Learn From My Pain - 5 Lessons From Ello's Adventures In Rapid Scaling](http://highscalability.com/blog/2015/1/21/learn-from-my-pain-5-lessons-from-ellos-adventures-in-rapid.html)<br/>
[Lessons Learned From Scaling Uber To 2000 Engineers, 1000 Services, And 8000 Git Repositories](http://highscalability.com/blog/2016/10/12/lessons-learned-from-scaling-uber-to-2000-engineers-1000-ser.html)<br/>
[7 Years Of YouTube Scalability Lessons In 30 Minutes](http://highscalability.com/blog/2012/3/26/7-years-of-youtube-scalability-lessons-in-30-minutes.html)<br/>
[Bitly: Lessons Learned Building A Distributed System That Handles 6 Billion Clicks A Month](http://highscalability.com/blog/2014/7/14/bitly-lessons-learned-building-a-distributed-system-that-han.html)<br/>
[17 Techniques Used To Scale Turntable.Fm And Labmeeting To Millions Of Users](http://highscalability.com/blog/2011/9/26/17-techniques-used-to-scale-turntablefm-and-labmeeting-to-mi.html)<br/>
[The Secret To 10 Million Concurrent Connections -The Kernel Is The Problem, Not The Solution](http://highscalability.com/blog/2013/5/13/the-secret-to-10-million-concurrent-connections-the-kernel-i.html)<br/>
[Russ’ 10 Ingredient Recipe for Making 1 Million TPS on \$5K Hardware](http://highscalability.com/blog/2012/9/10/russ-10-ingredient-recipe-for-making-1-million-tps-on-5k-har.html)<br/>

## 8.5 Other Scal<br/>

[Mollom Architecture - Killing Over 373 Million Spams At 100 Requests Per Second](http://highscalability.com/blog/2011/2/8/mollom-architecture-killing-over-373-million-spams-at-100-re.html)<br/>
[Facebook At 13 Million Queries Per Second Recommends: Minimize Request Variance](http://highscalability.com/blog/2010/11/4/facebook-at-13-million-queries-per-second-recommends-minimiz.html)<br/>
[DuckDuckGo Architecture - 1 Million Deep Searches A Day And Growing](http://highscalability.com/blog/2013/1/28/duckduckgo-architecture-1-million-deep-searches-a-day-and-gr.html)<br/>
[Leveraging Cloud Computing At Yelp - 102 Million Monthly Vistors And 39 Million Reviews](http://highscalability.com/blog/2013/6/26/leveraging-cloud-computing-at-yelp-102-million-monthly-visto.html)<br/>
[Scaling Mailbox - From 0 To One Million Users In 6 Weeks And 100 Million Messages Per Day](http://highscalability.com/blog/2013/6/18/scaling-mailbox-from-0-to-one-million-users-in-6-weeks-and-1.html)<br/>
[How The AOL.Com Architecture Evolved To 99.999% Availability, 8 Million Visitors Per Day, And 200,000 Requests Per Second](http://highscalability.com/blog/2014/2/17/how-the-aolcom-architecture-evolved-to-99999-availability-8.html)<br/>
[How League Of Legends Scaled Chat To 70 Million Players - It Takes Lots Of Minions](http://highscalability.com/blog/2014/10/13/how-league-of-legends-scaled-chat-to-70-million-players-it-t.html)<br/>
[Scaling Kim Kardashian To 100 Million Page Views](http://highscalability.com/blog/2015/2/16/scaling-kim-kardashian-to-100-million-page-views.html)<br/>
[The Etsy Saga: From Silos To Happy To Billions Of Pageviews A Month](http://highscalability.com/blog/2012/1/9/the-etsy-saga-from-silos-to-happy-to-billions-of-pageviews-a.html)<br/>
[Tumblr Architecture - 15 Billion Page Views A Month And Harder To Scale Than Twitter](http://highscalability.com/blog/2012/2/13/tumblr-architecture-15-billion-page-views-a-month-and-harder.html)<br/>
[Tagged Architecture - Scaling To 100 Million Users, 1000 Servers, And 5 Billion Page Views](http://highscalability.com/blog/2011/8/8/tagged-architecture-scaling-to-100-million-users-1000-server.html)<br/>
[Salesforce Architecture - How They Handle 1.3 Billion Transactions A Day](http://highscalability.com/blog/2013/9/23/salesforce-architecture-how-they-handle-13-billion-transacti.html)<br/>
[The Tumblr Architecture Yahoo Bought For A Cool Billion Dollars](http://highscalability.com/blog/2013/5/20/the-tumblr-architecture-yahoo-bought-for-a-cool-billion-doll.html)<br/>
[Scaling Pinterest - From 0 To 10s Of Billions Of Page Views A Month In Two Years](http://highscalability.com/blog/2013/4/15/scaling-pinterest-from-0-to-10s-of-billions-of-page-views-a.html)<br/>
[The Secret Of Scaling: You Can't Linearly Scale Effort With Capacity](http://highscalability.com/blog/2014/6/4/the-secret-of-scaling-you-cant-linearly-scale-effort-with-ca.html)<br/>
[Building Super Scalable Systems: Blade Runner Meets Autonomic Computing in the Ambient&nbsp;Cloud](http://highscalability.com/blog/2009/12/16/building-super-scalable-systems-blade-runner-meets-autonomic.html)<br/>

[How Twitter Stores 250 Million Tweets A Day Using MySQL](http://highscalability.com/blog/2011/12/19/how-twitter-stores-250-million-tweets-a-day-using-mysql.html)<br/>
[Scaling Twitter: Making Twitter 10000 Percent Faster](http://highscalability.com/scaling-twitter-making-twitter-10000-percent-faster)<br/>
[How WhatsApp Grew To Nearly 500 Million Users, 11,000 Cores, And 70 Million Messages A Second](http://highscalability.com/blog/2014/3/31/how-whatsapp-grew-to-nearly-500-million-users-11000-cores-an.html)<br/>
[A Beginner's Guide To Scaling To 11 Million+ Users On Amazon's AWS](http://highscalability.com/blog/2016/1/11/a-beginners-guide-to-scaling-to-11-million-users-on-amazons.html)(aws,good,cloud,scal,mustRead)<br/>
[StackOverflow Update: 560M Pageviews A Month, 25 Servers, And It's All About Performance](http://highscalability.com/blog/2014/7/21/stackoverflow-update-560m-pageviews-a-month-25-servers-and-i.html)<br/>
[How PayPal Scaled To Billions Of Transactions Daily Using Just 8VMs](http://highscalability.com/blog/2016/8/15/how-paypal-scaled-to-billions-of-transactions-daily-using-ju.html)<br/>
[How Does Google Do Planet-Scale Engineering For A Planet-Scale Infrastructure?](http://highscalability.com/blog/2016/7/18/how-does-google-do-planet-scale-engineering-for-a-planet-sca.html)<br/>
[Pinterest Architecture Update - 18 Million Visitors, 10x Growth,12 Employees, 410 TB Of Data](http://highscalability.com/blog/2012/5/21/pinterest-architecture-update-18-million-visitors-10x-growth.html)<br/>
[](http://highscalability.com/blog/category/blog)<br/>
[Scaling Traffic: People Pod Pool of On Demand Self Driving Robotic Cars who Automatically Refuel from Cheap&nbsp;Solar](http://highscalability.com/blog/2009/7/16/scaling-traffic-people-pod-pool-of-on-demand-self-driving-ro.html)<br/>
[Oculus Causes A Rift, But The Facebook Deal Will Avoid A Scaling Crisis For Virtual Reality](http://highscalability.com/blog/2014/3/26/oculus-causes-a-rift-but-the-facebook-deal-will-avoid-a-scal.html)<br/>
[How I Learned to Stop Worrying and Love Using a Lot of Disk Space to Scale](http://highscalability.com/how-i-learned-stop-worrying-and-love-using-lot-disk-space-scale)<br/>
[The Architecture Twitter Uses To Deal With 150M Active Users, 300K QPS, A 22 MB/S Firehose, And Send Tweets In Under 5 Seconds](http://highscalability.com/blog/2013/7/8/the-architecture-twitter-uses-to-deal-with-150m-active-users.html)<br/>
[GPU Vs CPU Smackdown : The Rise Of Throughput-Oriented Architectures](http://highscalability.com/blog/2010/12/3/gpu-vs-cpu-smackdown-the-rise-of-throughput-oriented-archite.html)<br/>
[Great Introductory Video On Scalability From Harvard Computer Science](http://highscalability.com/blog/2010/11/24/great-introductory-video-on-scalability-from-harvard-compute.html)<br/>
[Facebook At 13 Million Queries Per Second Recommends: Minimize Request Variance](http://highscalability.com/blog/2010/11/4/facebook-at-13-million-queries-per-second-recommends-minimiz.html)<br/>
[Facebook: An Example Canonical Architecture For Scaling Billions Of Messages](http://highscalability.com/blog/2011/5/17/facebook-an-example-canonical-architecture-for-scaling-billi.html)<br/>
[How I Learned To Stop Worrying And Love Using A Lot Of Disk Space To Scale](http://highscalability.com/how-i-learned-stop-worrying-and-love-using-lot-disk-space-scale)<br/>

[How Uber Manages A Million Writes Per Second Using Mesos And Cassandra Across Multiple Datacenters](http://highscalability.com/blog/2016/9/28/how-uber-manages-a-million-writes-per-second-using-mesos-and.html)(scalability,caseStudy)<br/>
[A Journey Through How Zapier Automates Billions Of Workflow Automation Tasks](http://highscalability.com/blog/2016/2/29/a-journey-through-how-zapier-automates-billions-of-workflow.html)(scalability,caseStudy)<br/>
[The Dollar Shave Club Architecture Unilever Bought For \$1 Billion](http://highscalability.com/blog/2016/9/13/the-dollar-shave-club-architecture-unilever-bought-for-1-bil.html)(scalability,caseStudy)<br/>
[The Easy Way Of Building A Growing Startup Architecture Using HAProxy, PHP, Redis And MySQL To Handle 1 Billion Requests A Week](http://highscalability.com/blog/2014/8/11/the-easy-way-of-building-a-growing-startup-architecture-usin.html)<br/>
[TripAdvisor Architecture - 40M Visitors, 200M Dynamic Page Views, 30TB Data](http://highscalability.com/blog/2011/6/27/tripadvisor-architecture-40m-visitors-200m-dynamic-page-view.html)<br/>

# 9. Algo<br/>

[9 Principles Of High Performance Programs](http://highscalability.com/blog/2014/5/21/9-principles-of-high-performance-programs.html)<br/>

Two fundamental causes of performance problems
* Mem Latency
* Context switching

Rules to help balance the forces of evil:
* batch works
* avoid magic numbers
* Allocate memory buffers up front
* Organically adapt your job-batching sizes
* Adapt receive buffer sizes for sockets
* Always complete all work queued
* Only signal a worker thread to wake up

[How Do You Program A Computer With 10 Terabytes Of RAM?](http://highscalability.com/blog/2015/8/5/how-do-you-program-a-computer-with-10-terabytes-of-ram.html)<br/>

Problems of huge memory system, and **HP’s Memory-Driven Computing**

[Google And Netflix Strategy: Use Partial Responses To Reduce Request Sizes](http://highscalability.squarespace.com/blog/2011/3/9/google-and-netflix-strategy-use-partial-responses-to-reduce.html)(strategy,google,netflix,performance)<br/>

[Google Strategy: Tree Distribution Of Requests And Responses](http://highscalability.com/blog/2011/2/1/google-strategy-tree-distribution-of-requests-and-responses.html)<br/>
[Strategy: Change The Problem](http://highscalability.com/blog/2014/9/3/strategy-change-the-problem.html)<br/>
[Big Data Counting: How To Count A Billion Distinct Objects Using Only 1.5KB Of Memory](http://highscalability.com/blog/2012/4/5/big-data-counting-how-to-count-a-billion-distinct-objects-us.html)<br/>
[Linus: The Whole "Parallel Computing Is The Future" Is A Bunch Of Crock.](http://highscalability.com/blog/2014/12/31/linus-the-whole-parallel-computing-is-the-future-is-a-bunch.html)(distributed)(multithread,mustRead)<br/>

# 10. Data<br/>

# 10.0 Data Science<br/>

[Machine Learning Driven Programming: A New Programming For A New World](http://highscalability.com/blog/2016/7/6/machine-learning-driven-programming-a-new-programming-for-a.html)(AI,google)<br/>

Short introduction on Machine Learning Driven Programming at Google

[Jeff Dean On Large-Scale Deep Learning At Google](http://highscalability.com/blog/2016/3/16/jeff-dean-on-large-scale-deep-learning-at-google.html)(AI,google)<br/>

Google's practice on Deep Learning.

[Let's Build Maker Cities For Maker People Around New Resources Like Bandwidth, Compute, And Atomically-Precise Manufacturing](http://highscalability.com/blog/2014/9/1/lets-build-maker-cities-for-maker-people-around-new-resource.html)<br/>
[6 Ways To Defeat The Coming Robot Army Swarms](http://highscalability.com/blog/2015/4/1/6-ways-to-defeat-the-coming-robot-army-swarms.html)<br/>
[Prismatic Architecture - Using Machine Learning On Social Networks To Figure Out What You Should Read On The Web](http://highscalability.com/blog/2012/7/30/prismatic-architecture-using-machine-learning-on-social-netw.html)<br/>

## 10.1 NoSQL

[Hilarious Video: Relational Database Vs NoSQL Fanbois](http://highscalability.com/blog/2010/9/5/hilarious-video-relational-database-vs-nosql-fanbois.html)<br/>
[5 Tips For Scaling NoSQL Databases: Don’t Trust Assumptions](http://highscalability.com/blog/2014/9/24/5-tips-for-scaling-nosql-databases-dont-trust-assumptionstes.html)<br/>
[Seven Signs You May Need A NoSQL Database](http://highscalability.com/blog/2010/2/16/seven-signs-you-may-need-a-nosql-database.html)<br/>
[The Anatomy Of Search Technology: Blekko’s NoSQL Database](http://highscalability.com/blog/2012/4/25/the-anatomy-of-search-technology-blekkos-nosql-database.html)<br/>
[What The Heck Are You Actually Using NoSQL For?](http://highscalability.com/blog/2010/12/6/what-the-heck-are-you-actually-using-nosql-for.html)<br/>
[35+ Use Cases For Choosing Your Next NoSQL Database](http://highscalability.com/blog/2011/6/20/35-use-cases-for-choosing-your-next-nosql-database.html)<br/>
[101 Questions To Ask When Considering A NoSQL Database](http://highscalability.com/blog/2011/6/15/101-questions-to-ask-when-considering-a-nosql-database.html)<br/>
[The State Of NoSQL In 2012](http://highscalability.com/blog/2012/1/24/the-state-of-nosql-in-2012.html)<br/>

--------------------

[Must See: 5 Steps To Scaling MongoDB (Or Any DB) In 8 Minutes](http://highscalability.com/blog/2011/9/13/must-see-5-steps-to-scaling-mongodb-or-any-db-in-8-minutes.html)<br/>
[LevelDB - Fast And Lightweight Key/Value Database From The Authors Of MapReduce And BigTable](http://highscalability.com/blog/2011/8/10/leveldb-fast-and-lightweight-keyvalue-database-from-the-auth.html)<br/>

## 10.2 Sharding

[Sharding The Hibernate Way](http://highscalability.com/blog/2008/7/26/sharding-the-hibernate-way.html)<br/>
[Troubles With Sharding - What Can We Learn From The Foursquare Incident?](http://highscalability.com/blog/2010/10/15/troubles-with-sharding-what-can-we-learn-from-the-foursquare.html)<br/>
[An Unorthodox Approach to Database Design : The Coming of the Shard](http://highscalability.com/blog/2009/8/6/an-unorthodox-approach-to-database-design-the-coming-of-the.html)<br/>
[Think Of Latency As A Pseudo-Permanent Network Partition](http://highscalability.com/blog/2010/8/12/think-of-latency-as-a-pseudo-permanent-network-partition.html)(latency)<br/>

## 10.3 Hadoop

[Why My Water Droplet Is Better Than Your Hadoop Cluster](http://highscalability.com/blog/2015/8/12/why-my-water-droplet-is-better-than-your-hadoop-cluster.html)<br/>
[Why My Slime Mold Is Better Than Your Hadoop Cluster](http://highscalability.com/blog/2012/4/9/why-my-slime-mold-is-better-than-your-hadoop-cluster.html)<br/>

## 10.4 Google
[Google Spanner's Most Surprising Revelation: NoSQL is Out and NewSQL is In](http://highscalability.com/blog/2012/9/24/google-spanners-most-surprising-revelation-nosql-is-out-and.html)<br/>
[Google's Sanjay Ghemawat On What Made Google Google And Great Big Data Career Advice](http://highscalability.com/blog/2013/10/21/googles-sanjay-ghemawat-on-what-made-google-google-and-great.html)<br/>
[How Google Backs Up The Internet Along With Exabytes Of Other Data](http://highscalability.com/blog/2014/2/3/how-google-backs-up-the-internet-along-with-exabytes-of-othe.html)<br/>
[How Google Serves Data From Multiple Datacenters](http://highscalability.com/blog/2009/8/24/how-google-serves-data-from-multiple-datacenters.html)<br/>

## 10.5 in-mem DB
[VoltDB Decapitates Six SQL Urban Myths And Delivers Internet Scale OLTP In The Process](http://highscalability.com/blog/2010/6/28/voltdb-decapitates-six-sql-urban-myths-and-delivers-internet.html)(scalability)<br/>
[MemSQL Architecture - The Fast (MVCC, InMem, LockFree, CodeGen) And Familiar (SQL)](http://highscalability.com/blog/2012/8/14/memsql-architecture-the-fast-mvcc-inmem-lockfree-codegen-and.html)<br/>
[How Will New Memory Technologies Impact In-Memory Databases?](http://highscalability.com/blog/2015/9/23/how-will-new-memory-technologies-impact-in-memory-databases.html)<br/>

## 10.6 Other

[The Big Problem Is Medium Data](http://highscalability.com/blog/2014/12/17/the-big-problem-is-medium-data.html)<br/>

[Jim Starkey Is Creating A Brave New World By Rethinking Databases For The Cloud](http://highscalability.com/blog/2011/8/4/jim-starkey-is-creating-a-brave-new-world-by-rethinking-data.html)<br/>
[The Mother Of All Database Normalization Debates On Coding Horror](http://highscalability.com/blog/2008/7/16/the-mother-of-all-database-normalization-debates-on-coding-h.html)<br/>

[Changing Architectures: New Datacenter Networks Will Set Your Code And Data Free](http://highscalability.com/blog/2012/9/11/changing-architectures-new-datacenter-networks-will-set-your.html)<br/>

[8 Ways Stardog Made Its Database Insanely Scalable](http://highscalability.com/blog/2014/1/20/8-ways-stardog-made-its-database-insanely-scalable.html)(Scalability)<br/>
[How Twitter Stores 250 Million Tweets A Day Using MySQL](http://highscalability.com/blog/2011/12/19/how-twitter-stores-250-million-tweets-a-day-using-mysql.html)<br/>
[Facebook's New Realtime Analytics System: HBase To Process 20 Billion Events Per Day](http://highscalability.com/blog/2011/3/22/facebooks-new-realtime-analytics-system-hbase-to-process-20.html)(realtime)<br/>
[Code Generation: The Inner Sanctum Of Database Performance](http://highscalability.com/blog/2016/9/7/code-generation-the-inner-sanctum-of-database-performance.html)(database,performance,)<br/>

This is guest post by Drew Paroski, architect and engineering manager at MemSQL. Previously he worked at Facebook and developed HHVM, the popular real-time PHP compiler used across the company’s web scale application.

[Switch Your Databases To Flash Storage. Now. Or You're Doing It Wrong.](http://highscalability.com/blog/2012/12/10/switch-your-databases-to-flash-storage-now-or-youre-doing-it.html)<br/>
[We Are Leaving 3x-4x Performance On The Table Just Because Of Configuration](http://highscalability.com/blog/2014/11/19/we-are-leaving-3x-4x-performance-on-the-table-just-because-o.html)<br/>
[When All The Program's A Graph - Prismatic's Plumbing Library](http://highscalability.com/blog/2013/2/14/when-all-the-programs-a-graph-prismatics-plumbing-library.html)<br/>
[ZooKeeper - A Reliable, Scalable Distributed Coordination System](http://highscalability.com/blog/2008/7/15/zookeeper-a-reliable-scalable-distributed-coordination-syste.html)(distributed)<br/>
[Building Scalable Systems Using Data As A Composite Material](http://highscalability.com/blog/2009/11/16/building-scalable-systems-using-data-as-a-composite-material.html)<br/>
[Drop ACID And Think About Data](http://highscalability.com/blog/2009/5/5/drop-acid-and-think-about-data.html)<br/>
[Stack Overflow Makes Slow Pages 100x Faster By Simple SQL Tuning](http://highscalability.com/blog/2011/5/2/stack-overflow-makes-slow-pages-100x-faster-by-simple-sql-tu.html)<br/>

# 11. Software Engineering<br/>

[This Stuff Isn't Taught, You Learn It Bit By Bit As You Solve Each Problem](http://highscalability.squarespace.com/blog/2011/2/23/this-stuff-isnt-taught-you-learn-it-bit-by-bit-as-you-solve.html)<br/>
[Rescuing An Outsourced Project From Collapse: 8 Problems Found And 8 Lessons Learned](http://highscalability.com/blog/2015/2/11/rescuing-an-outsourced-project-from-collapse-8-problems-foun.html)(lesson)<br/>
[All Employees Should Be Limited Only By Their Ability Rather Than An Absence Of Resources.](http://highscalability.com/blog/2014/12/3/all-employees-should-be-limited-only-by-their-ability-rather.html)<br/>
[How Debugging Is Like Hunting Serial Killers](http://highscalability.com/blog/2015/7/30/how-debugging-is-like-hunting-serial-killers.html)<br/>
[100 Curse Free Lessons From Gordon Ramsay On Building Great Software](http://highscalability.com/blog/2013/8/12/100-curse-free-lessons-from-gordon-ramsay-on-building-great.html)(lesson)<br/>
[How Do You Create A 100th Monkey Software Development Culture?](http://highscalability.com/blog/2013/7/17/how-do-you-create-a-100th-monkey-software-development-cultur.html)<br/>
[At Some Point The Cost Of Servers Outweighs The Cost Of Programmers](http://highscalability.com/blog/2009/4/5/at-some-point-the-cost-of-servers-outweighs-the-cost-of-prog.html)<br/>
[How Is Software Developed At Amazon?](http://highscalability.com/blog/2019/3/4/how-is-software-developed-at-amazon.html)(strategy)<br/>

The key themes from the talk: decomposition, automation, and organize around the customer.

The key idea:

Scaling is by mitosis. Teams split apart into smaller teams that completely own a service. EC2 started as one two pizza team.

[The Ultimate Guide: 5 Methods For Debugging Production Servers At Scale](http://highscalability.com/blog/2015/1/7/the-ultimate-guide-5-methods-for-debugging-production-server.html)<br/>
[Malice Or Stupidity Or Inattention? Using Code Reviews To Find Backdoors](http://highscalability.com/blog/2016/3/2/malice-or-stupidity-or-inattention-using-code-reviews-to-fin.html)(security)<br/>

# 12. Cache<br/>

[How HipChat Stores And Indexes Billions Of Messages Using ElasticSearch And Redis](http://highscalability.com/blog/2014/1/6/how-hipchat-stores-and-indexes-billions-of-messages-using-el.html)<br/>[Are Cloud Based Memory Architectures the Next Big Thing?](http://highscalability.com/blog/2009/3/16/are-cloud-based-memory-architectures-the-next-big-thing.html)<br/>

[11 Common Web Use Cases Solved In Redis](http://highscalability.com/blog/2011/7/6/11-common-web-use-cases-solved-in-redis.html)<br/>
[Ask HighScalability: Facing Scaling Issues With News Feeds On Redis. Any Advice?](http://highscalability.com/blog/2012/8/13/ask-highscalability-facing-scaling-issues-with-news-feeds-on.html)<br/>
[How Twitter Uses Redis To Scale - 105TB RAM, 39MM QPS, 10,000+ Instances](http://highscalability.com/blog/2014/9/8/how-twitter-uses-redis-to-scale-105tb-ram-39mm-qps-10000-ins.html)<br/>
[The Performance Of Distributed Data-Structures Running On A "Cache-Coherent" In-Memory Data Grid](http://highscalability.com/blog/2012/8/20/the-performance-of-distributed-data-structures-running-on-a.html)<br/>
[Strategy: Break Up The Memcache Dog Pile](http://highscalability.squarespace.com/blog/2009/8/7/strategy-break-up-the-memcache-dog-pile.html)<br/>
[MySQL And Memcached: End Of An Era?](http://highscalability.com/blog/2010/2/26/mysql-and-memcached-end-of-an-era.html)<br/>
[Strategy: Break Up The Memcache Dog Pile](http://highscalability.com/blog/2009/8/7/strategy-break-up-the-memcache-dog-pile.html)<br/>
[10 Program Busting Caching Mistakes](http://highscalability.com/blog/2014/7/16/10-program-busting-caching-mistakes.html)<br/>
[Better Browser Caching Is More Important Than No Javascript Or Fast Networks For HTTP Performance](http://highscalability.com/blog/2013/1/30/better-browser-caching-is-more-important-than-no-javascript.html)<br/>

# 13. Mobile<br/>

[Uber Goes Unconventional: Using Driver Phones As A Backup Datacenter](http://highscalability.com/blog/2015/9/21/uber-goes-unconventional-using-driver-phones-as-a-backup-dat.html)<br/>
[10 Golden Principles For Building Successful Mobile/Web Applications](http://highscalability.com/blog/2012/7/5/10-golden-principles-for-building-successful-mobileweb-appli.html)<br/>
[AppLovin: Marketing To Mobile Consumers Worldwide By Processing 30 Billion Requests A Day](http://highscalability.com/blog/2015/3/9/applovin-marketing-to-mobile-consumers-worldwide-by-processi.html)<br/>
[Facebook Mobile Drops Pull For Push-Based Snapshot + Delta Model](http://highscalability.com/blog/2014/10/20/facebook-mobile-drops-pull-for-push-based-snapshot-delta-mod.html)<br/>
[How Facebook Makes Mobile Work At Scale For All Phones, On All Screens, On All Networks](http://highscalability.com/blog/2014/9/22/how-facebook-makes-mobile-work-at-scale-for-all-phones-on-al.html)<br/>
[Misco: A MapReduce Framework For Mobile Systems - Start Of The Ambient Cloud?](http://highscalability.com/blog/2010/8/18/misco-a-mapreduce-framework-for-mobile-systems-start-of-the.html)<br/>
[If You're Programming A Cell Phone Like A Server You're Doing It Wrong](http://highscalability.com/blog/2013/9/18/if-youre-programming-a-cell-phone-like-a-server-youre-doing.html)<br/>

# 15. Paper<br/>

[Paper: The Akamai Network - 61,000 Servers, 1,000 Networks, 70 Countries&nbsp;](http://highscalability.com/blog/2011/8/18/paper-the-akamai-network-61000-servers-1000-networks-70-coun.html)(cdn)<br/>
[Paper: Immutability Changes Everything By Pat Helland](http://highscalability.com/blog/2015/1/26/paper-immutability-changes-everything-by-pat-helland.html)<br/>
[Paper: Network Stack Specialization For Performance](http://highscalability.com/blog/2014/2/12/paper-network-stack-specialization-for-performance.html)(network,performance)<br/>
[Awesome List Of Advanced Distributed Systems Papers](http://highscalability.com/blog/2011/5/31/awesome-list-of-advanced-distributed-systems-papers.html)(distributed,paper)<br/>

# 16. Security<br/>

[Auth0 Architecture - Running In Multiple Cloud Providers And Regions](http://highscalability.com/blog/2014/12/1/auth0-architecture-running-in-multiple-cloud-providers-and-r.html)<br/>
[How Do You Explain The Unreasonable Effectiveness Of Cloud Security?](http://highscalability.com/blog/2018/9/19/how-do-you-explain-the-unreasonable-effectiveness-of-cloud-s.html)<br/>
[How Facebook's Safety Check Works](http://highscalability.com/blog/2015/11/14/how-facebooks-safety-check-works.html)<br/>
[The Cat-And-Mouse Story Of Implementing Anti-Spam For Mail.Ru Group’s Email Service And What Tarantool Has To Do With This](http://highscalability.com/blog/2016/8/30/the-cat-and-mouse-story-of-implementing-anti-spam-for-mailru.html)<br/>

# 17. IOT<br/>

[How Would You Build The Next Internet? Loons, Drones, Copters, Satellites, Or Something Else?](http://highscalability.com/blog/2014/1/22/how-would-you-build-the-next-internet-loons-drones-copters-s.html)<br/>
[Want IoT? Here's How A Major US Utility Collects Power Data From Over 5.5 Million Meters](http://highscalability.com/blog/2015/9/7/want-iot-heres-how-a-major-us-utility-collects-power-data-fr.html)<br/>
[Hidden History: Driving The Last Spike Of The Transcontinental Railroad Was An Early Version Of The Internet Of Things](http://highscalability.com/blog/2013/11/27/hidden-history-driving-the-last-spike-of-the-transcontinenta.html)<br/>

# 18. Other<br/>

[What If Cars Were Rented Like We Hire Programmers?](http://highscalability.com/blog/2013/1/16/what-if-cars-were-rented-like-we-hire-programmers.html)<br/>
[Trade Stimulators And The Very Old Idea Of Increasing User Engagement&nbsp;](http://highscalability.com/blog/2015/9/9/trade-stimulators-and-the-very-old-idea-of-increasing-user-e.html)<br/>
[Let's Donate Our Organs And Unused Cloud Cycles To Science](http://highscalability.com/blog/2016/1/6/lets-donate-our-organs-and-unused-cloud-cycles-to-science.html)<br/>
[The Gig Economy Breaks Social Security](http://highscalability.com/blog/2016/4/12/the-gig-economy-breaks-social-security.html)(economy)<br/>
[What Ideas In IT Must Die?](http://highscalability.com/blog/2015/10/26/what-ideas-in-it-must-die.html)<br/>
[Let's Build Maker Cities For Maker People Around New Resources Like Bandwidth, Compute, And Atomically-Precise Manufacturing](http://highscalability.com/blog/2014/9/1/lets-build-maker-cities-for-maker-people-around-new-resource.html)(economy)<br/>
[6 Ways Not To Scale That Will Make You Hip, Popular And Loved By VCs](http://highscalability.com/blog/2011/4/18/6-ways-not-to-scale-that-will-make-you-hip-popular-and-loved.html)<br/>
[How Do We Explain The Unreasonable Effectiveness Of IT?](http://highscalability.com/blog/2015/7/6/how-do-we-explain-the-unreasonable-effectiveness-of-it.html)(book)<br/>
[What Happens While Your Brain Sleeps Is Surprisingly Like How Computers Stay Sane](http://highscalability.com/blog/2013/12/23/what-happens-while-your-brain-sleeps-is-surprisingly-like-ho.html)<br/>
[How Can We Spark The Movement Of Research Out Of The Ivory Tower And Into Production?](http://highscalability.com/blog/2010/7/22/how-can-we-spark-the-movement-of-research-out-of-the-ivory-t.html)<br/>
