package mate.academy.internetshop.factory;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.implementation.BucketDaoImpl;
import mate.academy.internetshop.dao.implementation.ItemDaoImpl;
import mate.academy.internetshop.dao.implementation.OrderDaoImpl;
import mate.academy.internetshop.dao.implementation.UserDaoImpl;

public class DaoFactory {

    private static UserDao userDao;
    private static BucketDao bucketDao;
    private static ItemDao itemDao;
    private static OrderDao orderDao;

    public static UserDao getUserDao() {
        if (userDao == null) {
            return userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public static BucketDao getBucketDao() {
        if (bucketDao == null) {
            return bucketDao = new BucketDaoImpl();
        }
        return bucketDao;
    }

    public static ItemDao getItemDao() {
        if (itemDao == null) {
            return itemDao = new ItemDaoImpl();
        }
        return itemDao;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            return orderDao = new OrderDaoImpl();
        }
        return orderDao;
    }
}
