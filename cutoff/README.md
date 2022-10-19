This service implemented a simple function which can return currency pair cut-off time of given date.
It also gets updates of currency cutoff times from external source.

# 1. Setup and configuration

database url:

    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/mydb?user=postgres&password=postgres";

* create database tables
* startup application
* import cutoff time data

# 2. End point

## 2.1 Get cutoff time

GET /cutoff

for example: 

    http://localhost:8080/cutoff?pair=EUR/GBP&date=2022-10-18

* Never possible = 00:00
* Always possible = 23:59

## 2.2 Update cutoff time

POST /cutoff

    app/src/resources/curl.txt

# 3. How it works

diagram here:

main components include:

* database with 3 tables
* local cache
* REST controller with GET and POST
* JdbcClient to access database
* CutoffService as service with main logic

Scenarios

* Application start


## 3.1 Database

we used 3 tables as below:



# Design considerations

* Optimized for reading
* Kafka for data synchronization
* Concurrency
* JPA vs JDBC
* Swagger

# Components

## Local cache

## Model, Controller and Service

# Main logic

* Get currency cut off updates
* Init trading date
* Update cut off times for currency
* Remove from cache



# File list