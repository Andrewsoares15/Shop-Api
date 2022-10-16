CREATE TABLE shop(
id int auto_increment PRIMARY KEY,
identifier varchar NOT NULL,
status varchar NOT NULL,
date_shop date
);

CREATE TABLE shop_item(
id int auto_increment PRIMARY KEY,
product_identifier varchar(100) NOT NULL,
amount int NOT NULL,
price float NOT NULL,
shop_id int REFERENCES shop(id)
);