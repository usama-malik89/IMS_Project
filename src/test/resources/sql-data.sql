INSERT INTO `test_ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `test_ims`.`customers` (`first_name`, `surname`) VALUES ('usama', 'malik');

INSERT INTO `test_ims`.`items` (`name`, `value`) VALUES ('apple', '1.23');

INSERT INTO `test_ims`.`orders` (`customer_id`) VALUES ('1');

INSERT INTO `test_ims`.`order_items` (`order_id`, `item_id`) VALUES ('1','1');