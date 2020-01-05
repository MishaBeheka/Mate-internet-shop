package mate.academy.internetshop.lib;

import java.util.HashMap;
import java.util.Map;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.DaoFactory;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(UserDao.class, DaoFactory.getUserDao());
        classMap.put(BucketDao.class, DaoFactory.getBucketDao());
        classMap.put(ItemDao.class, DaoFactory.getItemDao());
        classMap.put(OrderDao.class, DaoFactory.getOrderDao());
        classMap.put(UserService.class, DaoFactory.getUserService());
        classMap.put(OrderService.class, DaoFactory.getOrderService());
        classMap.put(ItemService.class, DaoFactory.getItemService());
        classMap.put(BucketService.class, DaoFactory.getBucketService());
    }

    public static Object getDaoImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
