# Technical Interview 99X-Technology

This is a test project created to demonstrate a simple price calculator using Java - Spring boot.

# Prerequisites 
- Beginner or intermediate Java 8 programming skills 
- Familiarity with Spring Boot, Spring Hibernate, JPA, Mockito Unit Testing


# Import the code directly to the Preferred IDE
Spring Tool Suite (STS) or
IntelliJ IDEA

# Introduction
The price structures are as follows:
- The rare product "Penguin-ears" is 20 units per carton. A carton is 175,-
- The product "Horseshoe" is 5 units per carton, a carton is 825,-
- If you purchase single units, the price is acquired using the carton price multiplied by an increase of
30% (1.3). (to compensate for manual labor)
- If you purchase 3 cartons or more, you will receive a 10% discount off the carton price

# Background 
When executing this project the test coverage ( using jacoco ) of the implementation can be seen

# Database Scripts
The DB scripts for the project are as follows:

```sql

CREATE DATABASE IF NOT EXISTS `product_prices`;
USE `product_prices`;

/*Creating Price table*/
CREATE TABLE IF NOT EXISTS `price` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `product_id` int(4) DEFAULT NULL,
  `carton_size` varchar(20) DEFAULT NULL,
  `carton_price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_price_product` (`product_id`),
  CONSTRAINT `FK_price_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Inserting data into the Price table*/
INSERT INTO `price` (`id`, `product_id`, `carton_size`, `carton_price`) VALUES
	(1, 1, '20', 175),
	(2, 2, '5', 825);

/*Creating Product table*/
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Inserting data into the Product table*/
INSERT INTO `product` (`id`, `product_name`) VALUES
	(1, 'penguinEars'),
	(2, 'horseShoes');

```
