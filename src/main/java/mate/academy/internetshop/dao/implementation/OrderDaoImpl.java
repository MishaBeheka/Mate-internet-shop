package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
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
        return Storage.orders
                .stream()
                .filter(order1 -> order1.equals(order))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Order isn't created " + order));
    }

    @Override
    public Optional get(Long id) {
        return Optional.ofNullable(Storage.orders
                .stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find order with ID " + id)));
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
    public boolean delete(Long id) {
        return Storage.orders.removeIf(order -> order.getOrderId().equals(id));
    }

    @Override
    public boolean delete(Order order) {
        return Storage.orders.removeIf(order1 -> order1.equals(order));
    }
}
