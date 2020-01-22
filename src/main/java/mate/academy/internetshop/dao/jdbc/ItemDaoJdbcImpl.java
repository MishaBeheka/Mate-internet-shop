package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        String query = String.format("SELECT * FROM %s.items WHERE item_id = %d;", DB_NAME, id);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                long item_id = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item();
                item.setItemId(item_id);
                item.setName(name);
                item.setPrice(price);
                return Optional.of(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get item by id " + id);
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item item) {
        String query = String.format(Locale.ROOT,
                "UPDATE %s.items SET name = '%s', price = %.2f WHERE item_id = %d;",
                DB_NAME, item.getName(), item.getPrice(), item.getItemId());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Item wasn't updated " + e);
        }
        return item;
    }

    @Override
    public boolean deleteById(Long id) {
        String query = String.format("DELETE FROM %s.items WHERE item_id = %d",
                DB_NAME, id);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            logger.error("Item with id " + id + " wasn't deleted " + e);
        }
        return false;
    }

    @Override
    public boolean deleteByEntity(Item item) {
        String query = String.format("DELETE FROM %s.items WHERE item_id = %d",
                DB_NAME, item.getItemId());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            logger.error("Item wasn't deleted " + e);
        }
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
