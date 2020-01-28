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
    private static final String UPDATE_ITEM =
            "UPDATE internet_shop.items SET name = ?, price = ? WHERE item_id = ?";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) throws DataProcessingException {
        try (PreparedStatement pr = connection.prepareStatement(
                "INSERT INTO internet_shop.items (name, price) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
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
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM internet_shop.items WHERE item_id = ?")) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    item.setItemId(resultSet.getLong("item_id"));
                    item.setName(resultSet.getString("name"));
                    item.setPrice(resultSet.getDouble("price"));
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
        try (PreparedStatement pr = connection.prepareStatement(
                "DELETE FROM internet_shop.items WHERE item_id = ?")) {
            pr.setLong(1, id);
            pr.executeUpdate();
            return true;
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
        List<Item> items = new ArrayList<>();
        try (PreparedStatement pr = connection.prepareStatement(
                "SELECT * FROM internet_shop.items")) {
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setItemId(resultSet.getLong("item_id"));
                    item.setName(resultSet.getString("name"));
                    item.setPrice(resultSet.getDouble("price"));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't show items " + e);
        }
        return items;
    }
}
