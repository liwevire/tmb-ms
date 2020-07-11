BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "customer" (
	"id"	SERIAL NOT NULL PRIMARY KEY,
	"name"	varchar(50) NOT NULL,
	"sec_name"	varchar(50) NOT NULL,
	"date"	timestamp NOT NULL, 
	"address"	varchar(300) NOT NULL,
	"post"	varchar(50) NOT NULL,
	"pin"	varchar(6),
	"phone"	varchar(27)
);
CREATE TABLE IF NOT EXISTS "loan" (
	"id"	SERIAL NOT NULL PRIMARY KEY,
	"customer_id"	INTEGER NOT NULL,
	"status"	INTEGER NOT NULL,
	"weight"	decimal(13 , 3) NOT NULL,
	"comment"	varchar(300),
	FOREIGN KEY("customer_id") REFERENCES "customer"("id")
);
CREATE TABLE IF NOT EXISTS "activity" (
	"id"	SERIAL NOT NULL PRIMARY KEY,
	"loan_id"	INTEGER NOT NULL,
	"date"	timestamp NOT NULL,
	"category"	INTEGER NOT NULL,
	"amount"	INTEGER NOT NULL,
	FOREIGN KEY("loan_id") REFERENCES "loan"("id")
);
CREATE TABLE IF NOT EXISTS "item" (
	"id"	SERIAL NOT NULL PRIMARY KEY,
	"loan_id"	INTEGER NOT NULL,
	"name"	varchar(100) NOT NULL,
	"quantity"	INTEGER NOT NULL,
	FOREIGN KEY("loan_id") REFERENCES "loan"("id")
);
INSERT INTO "customer" ("id","name","sec_name","date","address","post","pin","phone") VALUES (1,'Premkumar','T',NOW(),'18 E','Thiruvanmiyur','614804','456789456'),
 (2,'Pradheep','M',NOW(),'18 E','MMNagar','600041','1234684123'),
 (3,'Prakz','V',NOW(),'18 E','Trivandrum','600041','1234684123');
INSERT INTO "loan" ("id","customer_id","status","weight","comment") VALUES (1,1,1,16,'test123'),
 (2,2,2,16,''),
 (3,3,12,199,'test');
INSERT INTO "activity" ("id","loan_id","date","category","amount") VALUES (1,1,NOW(),1,100);
INSERT INTO "item" ("id","loan_id","name","quantity") VALUES (1,1,'Chain',1),
 (4,2,'Chain',3),
 (5,3,'Chain',1);
 
COMMIT;
