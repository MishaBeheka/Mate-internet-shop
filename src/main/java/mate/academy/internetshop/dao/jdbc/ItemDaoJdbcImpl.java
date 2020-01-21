package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        try (PreparedStatement pr =
                     connection.prepareStatement("INSERT INTO " + DB_NAME + ".items (name, price) "
                             + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, name);
            pr.setDouble(2, price);
            pr.executeUpdate();
            ResultSet rs = pr.getGeneratedKeys();
            rs.next();
            item.setItemId(rs.getLong(1));
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
        return null;
    }
}
