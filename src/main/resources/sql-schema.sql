
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims`;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NULL DEFAULT NULL,
    `value` DOUBLE NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `customer_id` INT(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_cid_1` FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `order_id` INT(11) DEFAULT NULL,
    `item_id` INT(11) DEFAULT NULL,
    `quantity` INT(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_oid_1` FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`) ON DELETE CASCADE,
    CONSTRAINT `FK_iid_1` FOREIGN KEY (`item_id`) REFERENCES `items`(`id`) ON DELETE CASCADE
);

USE `ims`;