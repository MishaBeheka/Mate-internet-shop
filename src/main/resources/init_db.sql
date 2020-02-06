DROP SCHEMA IF EXISTS `internet_shop`;
CREATE SCHEMA `internet_shop`;
USE internet_shop;
CREATE TABLE `items` (
                         `item_id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) NOT NULL,
                         `price` decimal(10,2) NOT NULL,
                         PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('BMW x5', '32000');
INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('Tesla X', '36500');
INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('DeLorean DMC-12', '100000');
INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('Mustang GTX', '41800');

CREATE TABLE `users` (
                         `user_id` int(11) NOT NULL AUTO_INCREMENT,
                         `first_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
                         `last_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
                         `login` varchar(255) COLLATE utf8_bin NOT NULL,
                         `password` varchar(255) COLLATE utf8_bin NOT NULL,
                         `token` varchar(255) COLLATE utf8_bin DEFAULT NULL,
                         `salt` varbinary(16) DEFAULT NULL,
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `role` (
                        `role_id` int(11) NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
                        PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `internet_shop`.`role` (`role_name`) VALUES ('USER');
INSERT INTO `internet_shop`.`role` (`role_name`) VALUES ('ADMIN');

CREATE TABLE `user_roles` (
                              `user_roles_id` int(11) NOT NULL AUTO_INCREMENT,
                              `user_id` int(11) NOT NULL,
                              `role_id` int(11) NOT NULL,
                              PRIMARY KEY (`user_roles_id`),
                              KEY `from user_roles.user_id to users.user_id_idx` (`user_id`),
                              KEY `from user_roles.role_id to role.role_id_idx` (`role_id`),
                              CONSTRAINT `from user_roles.role_id to role.role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
                              CONSTRAINT `from user_roles.user_id to users.user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `buckets` (
                           `bucket_id` int(11) NOT NULL AUTO_INCREMENT,
                           `user_id` int(11) NOT NULL,
                           PRIMARY KEY (`bucket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `bucket_items` (
                                `bucket_items_id` int(11) NOT NULL AUTO_INCREMENT,
                                `bucket_id` int(11) DEFAULT NULL,
                                `item_id` int(11) DEFAULT NULL,
                                PRIMARY KEY (`bucket_items_id`),
                                KEY `bucket_items_id  to bucket_id_idx` (`bucket_id`),
                                KEY `bucket_items_user_id to item_id_idx` (`item_id`),
                                CONSTRAINT `bucket_items_id  to bucket_id` FOREIGN KEY (`bucket_id`) REFERENCES `buckets` (`bucket_id`),
                                CONSTRAINT `bucket_items_user_id to item_id` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `orders` (
                          `order_id` int(11) NOT NULL AUTO_INCREMENT,
                          `user_id` int(11) NOT NULL,
                          PRIMARY KEY (`order_id`),
                          KEY `order to user_idx` (`user_id`),
                          CONSTRAINT `to user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `orders_items` (
                                `orders_items_id` int(11) NOT NULL AUTO_INCREMENT,
                                `item_id` int(11) NOT NULL,
                                `order_id` int(11) NOT NULL,
                                PRIMARY KEY (`orders_items_id`),
                                KEY `orders_items to order_idx` (`order_id`),
                                KEY `to items_idx` (`item_id`),
                                CONSTRAINT `orders_items to order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT `to items` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;











