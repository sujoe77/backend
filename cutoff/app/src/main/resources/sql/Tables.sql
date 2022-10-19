-- define all pair can be traded in system, and some frequent traded should be cached
create table pair_config (
	base varchar(3) not null,
	term varchar(3) not null,
	cached boolean,
	PRIMARY KEY (base, term)
)

-- actual pair cutoff times in database
create table pair_cutoff (
	base varchar(3) not null,
	term varchar(3) not null,
	trading_date date not null,
	cutoff_time time,
	PRIMARY KEY (base, term, trading_date)
)

-- append only table, keep records of all updates received
create table cutoff_updates (
	currency varchar(3) not null,	
	trading_date date not null,
	cutoff_time time,
	description text,
	PRIMARY KEY (currency, trading_date)
)
