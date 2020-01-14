package mate.academy.internetshop.dao.implementation;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders
                .stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst();

    }

    @Override
    public Order update(Order order) {
        return Storage.orders
                .stream()
                .filter(updateOrder -> updateOrder.getOrderId().equals(order.getOrderId()))
                .findFirst()
                .map(updateOrder -> {
                    updateOrder.setDate(order.getDate());
                    updateOrder.setUserId(order.getUserId());
                    updateOrder.setItems(order.getItems());
                    return updateOrder;
                })
                .orElseThrow(() -> new RuntimeException("Order isn't update " + order));
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.orders.removeIf(order -> order.getOrderId().equals(id));
    }

    @Override
    public boolean deleteByEntity(Order order) {
        return Storage.orders.removeIf(order1 -> order1.equals(order));
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }
}
