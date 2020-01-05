package mate.academy.internetshop.service;

import java.util.List;

import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface OrderService {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    boolean delete(Long id);

    boolean delete(Order order);

    Order completeOrder(List items, User user);

    List getUserOrders(User user);
}
