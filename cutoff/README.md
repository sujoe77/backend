This service implemented a simple function which can return currency pair cut-off time of given date.
It also get updates of currency cutoff times from external source.

# End points

## Get cutoff time

## Update cutoff time

# Database design

we used 3 tables as below:

# Local cache

# Lifecycle

* Get currency cut off updates
* Init trading date
* Update cut off times for currency
* Remove from cache