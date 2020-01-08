package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.model.Order;

public interface OrderDao {

    Order create(Order order);

    Optional get(Long id);

    Order update(Order order);

    boolean delete(Long id);

    boolean delete(Order order);
}
