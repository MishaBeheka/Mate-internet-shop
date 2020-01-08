package mate.academy.internetshop.factory;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.implementation.BucketDaoImpl;
import mate.academy.internetshop.dao.implementation.ItemDaoImpl;
import mate.academy.internetshop.dao.implementation.OrderDaoImpl;
import mate.academy.internetshop.dao.implementation.UserDaoImpl;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.implementation.BucketServiceImpl;
import mate.academy.internetshop.service.implementation.ItemServiceImpl;
import mate.academy.internetshop.service.implementation.OrderServiceImpl;
import mate.academy.internetshop.service.implementation.UserServiceImpl;

public class DaoFactory {

    private static UserDao userDao;
    private static BucketDao bucketDao;
    private static ItemDao itemDao;
    private static OrderDao orderDao;
    private static UserService userService;
    private static OrderService orderService;
    private static ItemService itemService;
    private static BucketService bucketService;

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public static BucketDao getBucketDao() {
        if (bucketDao == null) {
            bucketDao = new BucketDaoImpl();
        }
        return bucketDao;
    }

    public static ItemDao getItemDao() {
        if (itemDao == null) {
            itemDao = new ItemDaoImpl();
        }
        return itemDao;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoImpl();
        }
        return orderDao;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    public static ItemService getItemService() {
        if (itemService == null) {
            itemService = new ItemServiceImpl();
        }
        return itemService;
    }

    public static BucketService getBucketService() {
        if (bucketService == null) {
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }
}
