CREATE TABLE "customer" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"name"	varchar(50) NOT NULL,
	"sec_name"	varchar(50) NOT NULL,
	"date"	datetime NOT NULL,
	"address"	varchar(300) NOT NULL,
	"post"	varchar(50) NOT NULL,
	"pin"	varchar(6),
	"phone"	varchar(27)
);

CREATE TABLE "loan" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"customer_id"	INTEGER NOT NULL,
	"status"	INTEGER NOT NULL,
	"weight"	decimal(13 , 3) NOT NULL,
	"comment"	varchar(300),
	FOREIGN KEY("customer_id") REFERENCES "customer"("id")
);

CREATE TABLE "item" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"loan_id"	INTEGER NOT NULL,
	"name"	varchar(100) NOT NULL,
	"quantity"	INTEGER NOT NULL,
	FOREIGN KEY("loan_id") REFERENCES "loan"("id")
);

CREATE TABLE "activity" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"loan_id"	INTEGER NOT NULL,
	"date"	datetime NOT NULL,
	"category"	INTEGER NOT NULL,
	"amount"	INTEGER NOT NULL,
	FOREIGN KEY("loan_id") REFERENCES "loan"("id")
);