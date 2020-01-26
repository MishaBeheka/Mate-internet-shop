CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internet_shop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`item_id`));

INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('Volvo', '5000.00');

CREATE TABLE `internet_shop`.`users` (
                                         `user_id` INT NOT NULL AUTO_INCREMENT,
                                         `first_name` VARCHAR(45) NULL,
                                         `last_name` VARCHAR(45) NULL,
                                         `login` VARCHAR(255) NOT NULL,
                                         `password` VARCHAR(255) NOT NULL,
                                         `token` VARCHAR(255) NULL,
                                         PRIMARY KEY (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;

INSERT INTO internet_shop.users (first_name, last_name, login, password) VALUES ('Misha', 'Beheka', 'admin@ukr.net', '777');
INSERT INTO internet_shop.users (first_name, last_name, login, password) VALUES ('Vova', 'Polik', 'user1@ukr.net', '111');
INSERT INTO internet_shop.users (first_name, last_name, login, password) VALUES ('Sasha', 'Dolik', 'user2@ukr.net', '222');



CREATE TABLE `internet_shop`.`role` (
                                        `role_id` INT NOT NULL AUTO_INCREMENT,
                                        `role_name` VARCHAR(45) NULL,
                                        PRIMARY KEY (`role_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;

INSERT INTO `internet_shop`.`role` (`role_name`) VALUES ('USER');
INSERT INTO `internet_shop`.`role` (`role_name`) VALUES ('ADMIN');



CREATE TABLE `internet_shop`.`user_roles` (
                                              `user_roles_id` INT NOT NULL AUTO_INCREMENT,
                                              `user_id` INT NOT NULL,
                                              `role_id` INT NOT NULL,
                                              PRIMARY KEY (`user_roles_id`),
                                              INDEX `from user_roles.user_id to users.user_id_idx` (`user_id` ASC) VISIBLE,
                                              INDEX `from user_roles.role_id to role.role_id_idx` (`role_id` ASC) VISIBLE,
                                              CONSTRAINT `from user_roles.user_id to users.user_id`
                                                  FOREIGN KEY (`user_id`)
                                                      REFERENCES `internet_shop`.`users` (`user_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION,
                                              CONSTRAINT `from user_roles.role_id to role.role_id`
                                                  FOREIGN KEY (`role_id`)
                                                      REFERENCES `internet_shop`.`role` (`role_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;

INSERT INTO `internet_shop`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '2');
