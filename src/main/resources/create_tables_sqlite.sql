CREATE TABLE "customer" (
	"id"	INTEGER NOT NULL,
	"name"	varchar(50) NOT NULL,
	"sec_name"	varchar(50) NOT NULL,
	"date"	datetime NOT NULL,
	"address"	varchar(300) NOT NULL,
	"post"	varchar(50) NOT NULL,
	"pin"	varchar(6),
	"phone"	varchar(27),
	"comment"	varchar(300),
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "loan" (
	"id"	INTEGER NOT NULL,
	"customer_id"	INTEGER NOT NULL,
	"status"	varchar(10) NOT NULL DEFAULT 'open',
	"weight"	decimal(13, 3) NOT NULL,
	"comment"	varchar(300),
	"alt_id"	varchar(10) UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("customer_id") REFERENCES "customer"("id")
);

CREATE TABLE "item" (
	"id"	INTEGER NOT NULL,
	"loan_id"	INTEGER NOT NULL,
	"name"	varchar(100) NOT NULL,
	"quantity"	INTEGER NOT NULL,
	FOREIGN KEY("loan_id") REFERENCES "loan"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "activity" (
	"id"	INTEGER NOT NULL,
	"loan_id"	INTEGER NOT NULL,
	"date"	datetime NOT NULL,
	"category"	varchar(100) NOT NULL,
	"amount"	INTEGER NOT NULL,
	FOREIGN KEY("loan_id") REFERENCES "loan"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE "misc" (
	"id"	INTEGER NOT NULL,
	"date"	datetime NOT NULL,
	"gold_rate"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);