package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderJdbcImpl extends AbstractDao<Order> implements OrderDao {

    private static final String GET_ALL_ITEMS =
            "SELECT items.item_id, items.name, items.price FROM items"
                    + " INNER JOIN orders_items ON items.item_id = orders_items.item_id"
                    + " WHERE orders_items.order_id = ?";

    public OrderJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) throws DataProcessingException {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             "INSERT INTO orders (user_id) VALUES (?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, order.getUserId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                order.setOrderId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create order " + e);
        }
        addItemsToDB(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) throws DataProcessingException {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM orders WHERE orders.order_id = ?")) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            Order order = new Order();
            while (resultSet.next()) {
                order.setOrderId(resultSet.getLong("order_id"));
                order.setUserId(resultSet.getLong("user_id"));
            }
            order.setItems(getAllItems(id));
            return Optional.of(order);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get order with ID " + id + e);
        }
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE orders SET order_id = ? WHERE user_id = ?")) {
            ps.setLong(1, order.getOrderId());
            ps.setLong(2, order.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException(
                    "Can't update order with ID " + order.getOrderId() + e);
        }
        return order;
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM orders WHERE order_id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order with ID " + id + e);
        }
    }

    @Override
    public boolean deleteByEntity(Order order) throws DataProcessingException {
        return deleteById(order.getOrderId());
    }

    @Override
    public List<Order> getAll() throws DataProcessingException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM orders")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Order order = get(resultSet.getLong("order_id")).orElseThrow();
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all orders " + e);
        }
        return orders;
    }

    private void addItemsToDB(Order order) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO orders_items (item_id, order_id) VALUES (?, ?)")) {
            ps.setLong(2, order.getOrderId());
            for (Item item : order.getItems()) {
                ps.setLong(1, item.getItemId());
                ps.execute();
            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }

    private List<Item> getAllItems(Long orderId) throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ITEMS)) {
            ps.setLong(1, orderId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getLong("item_id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all items with order ID " + orderId + e);
        }
        return items;
    }
}
