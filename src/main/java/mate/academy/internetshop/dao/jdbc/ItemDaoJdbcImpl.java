package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static String DB_NAME = "internet_shop";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String name = item.getName();
        double price = item.getPrice();
        String query = String.format("INSERT INTO %s.items (name, price) VALUES('%s',%s)", DB_NAME, name, price);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn("Item wasn't created " + e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        Statement statement = null;
        String query = "SELECT * FROM " + DB_NAME + ".items WHERE item_id=" + id + ";";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                long item_id = rs.getLong("item_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                Item item = new Item();
                item.setItemId(item_id);
                item.setName(name);
                item.setPrice(price);
                return Optional.of(item);
            }

        } catch (SQLException e) {
            logger.warn("Can't get item by id=" + id);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement", e);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean deleteByEntity(Item entity) {
        return false;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.items", DB_NAME);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setItemId(resultSet.getLong("item_id"));
                    item.setName(resultSet.getString("name"));
                    item.setPrice(resultSet.getDouble("price"));
                    items.add(item);
                }

        } catch (SQLException e) {
            logger.info("Can't show items " + e);
        }
        return items;
    }
}
