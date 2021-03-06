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
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static final String CREATE_ITEM =
            "INSERT INTO internet_shop.items (name, price) VALUES (?, ?)";

    private static final String GET_ITEM_BY_ID =
            "SELECT * FROM internet_shop.items WHERE item_id = ?";

    private static final String UPDATE_ITEM =
            "UPDATE internet_shop.items SET name = ?, price = ? WHERE item_id = ?";

    private static final String DELETE_ITEM_BY_ID =
            "DELETE FROM internet_shop.items WHERE item_id = ?";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) throws DataProcessingException {
        try (PreparedStatement pr = connection.prepareStatement(
                CREATE_ITEM, Statement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, item.getName());
            pr.setDouble(2, item.getPrice());
            pr.executeUpdate();
            ResultSet resultSet = pr.getGeneratedKeys();
            while (resultSet.next()) {
                item.setItemId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create item " + e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long id) throws DataProcessingException {
        Item item = new Item();
        try (PreparedStatement ps = connection.prepareStatement(GET_ITEM_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    generateItem(resultSet, item);
                }
                return Optional.of(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get item by id " + id);
        }
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        try (PreparedStatement ps =
                     connection.prepareStatement(UPDATE_ITEM)) {
            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setLong(3, item.getItemId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Item wasn't updated " + e);
        }
        return item;
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        try (PreparedStatement pr = connection.prepareStatement(DELETE_ITEM_BY_ID)) {
            pr.setLong(1, id);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Item with id " + id + " wasn't deleted " + e);
        }
    }

    @Override
    public boolean deleteByEntity(Item item) throws DataProcessingException {
        return deleteById(item.getItemId());
    }

    @Override
    public List<Item> getAll() throws DataProcessingException {
        String query = "SELECT * FROM internet_shop.items";
        List<Item> items = new ArrayList<>();
        try (PreparedStatement pr = connection.prepareStatement(query)) {
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item();
                    generateItem(resultSet, item);
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't show items " + e);
        }
        return items;
    }

    private Item generateItem(ResultSet resultSet, Item item) throws SQLException {
        item.setItemId(resultSet.getLong(1));
        item.setName(resultSet.getString(2));
        item.setPrice(resultSet.getDouble(3));
        return item;
    }
}
