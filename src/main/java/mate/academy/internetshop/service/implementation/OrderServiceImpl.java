package mate.academy.internetshop.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean deleteById(Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public boolean deleteByEntity(Order order) {
        return orderDao.deleteByEntity(order);
    }

    @Override
    public Order completeOrder(List items, User user) {
        return orderDao.create(new Order(user.getUserId(),items));
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders
                .stream()
                .filter(order -> order.getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
    }
}
