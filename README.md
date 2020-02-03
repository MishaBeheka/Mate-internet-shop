# WEB-STORE

This project is a simple web-store template

<hr>

It implements the basic features that should be available in a web-store:
* Registration
* Authentication
* Authorization
* Add and view items
* Adding products to your bucket
* Purchase of items
* Logout

<hr>

# Project structure
  * Java 11
  * Tomcat 9.0.21
  * MySQL 8.0.17
  * Maven 4.0.0
  * javax.servlet-api 3.1.0
  * jstl 1.2
  * log4j 1.2.17
  * maven-checkstyle-plugin
  * mysql-connector-java 8.0.17
  * bootstrap 4.4.1
  <hr>
  
  # For developers
  * Open the project in your IDE.
  
  * Add it as maven project.
  * Configure Tomcat:
    * add artifact
    * add sdk 11.0.2
  
  * Add sdk 11.0.3 in project.
  * Use file /jv-internet-shop/src/main/resources/init_db.sql to create schema and all the tables required by this app in MySQL database.
  * At /jv-internet-shop/src/main/java/mate/academy/internetshop/factory/Factory class use username and password for your DB to create a Connection.
  * Change a path in /jv-internet-shop/src/main/resources/log4j.properties. It has to reach your logFile.
  * Run the project
  <hr>
  
  # Instruction of user
  When you start the application, you will be taken to the registration page.
  You need to sign in or sign in to start using the store if you already have an account.
  By registering, you receive the default USER role and are available to you:
  * view items in the shop
  * add items to your bucket
  * complete order
  * view your orders
  
  If you want to try yourself as an ADMIN, you should change the role of a registered user in DB manually.
  * Example UPDATE `internet_shop`.`user_roles` SET` role_id` = '2' WHERE `user_roles_id` = '1';
  * Remark - USERS have role_id = 1 and ADMINS have role_id = 2;
  By logging into ADMIN, you can access:
  * view all users
  * add items to the store
  
