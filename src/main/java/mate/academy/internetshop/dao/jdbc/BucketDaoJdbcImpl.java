package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);
    private static final String GET_ALL_ITEMS = "SELECT items.item_id, "
            + "items.name, items.price "
            + "FROM items INNER JOIN bucket_items "
            + "ON items.item_id = bucket_items.item_id "
            + "WHERE bucket_items.bucket_id = ?";
    private static final String ADD_ITEMS_TO_BUCKET =
            "INSERT INTO bucket_items (bucket_id, item_id) VALUES (?, ?)";

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             "INSERT INTO buckets (user_id) VALUES (?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, bucket.getUserId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                bucket.setBucketId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Can't create bucket ", e);
        }
        return addItemsToBucket(bucket);
    }

    @Override
    public Optional<Bucket> get(Long id) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             "SELECT * FROM buckets WHERE bucket_id = ?")) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            Bucket bucket = new Bucket();
            while (resultSet.next()) {
                bucket.setUserId(resultSet.getLong("user_id"));
                bucket.setBucketId(resultSet.getLong("bucket_id"));
            }
            bucket.setItems(getAllItems(id));
            return Optional.of(bucket);
        } catch (SQLException e) {
            logger.error("Can't get bucket with ID " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Bucket update(Bucket bucket) {
        try (PreparedStatement pr =
                     connection.prepareStatement(
                             "DELETE FROM bucket_items WHERE bucket_id = ?")) {
            pr.setLong(1, bucket.getBucketId());
            pr.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete items ", e);
        }
        for (Item item : bucket.getItems()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO bucket_items (bucket_id, item_id) VALUES (?, ?)")) {
                ps.setLong(1, bucket.getBucketId());
                ps.setLong(2, item.getItemId());
                ps.executeUpdate();
            } catch (SQLException e) {
                logger.error("Can't add item or bucket to bucket_items");
            }
        }
        return bucket;
    }

    @Override
    public boolean deleteById(Long id) {
        try (PreparedStatement ps =
                     connection.prepareStatement("DELETE FROM bucket_items WHERE bucket_id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete items from bucket_items with ID " + id);
        }

        try (PreparedStatement ps =
                     connection.prepareStatement("DELETE FROM buckets WHERE bucket_id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("");
        }
    }

    @Override
    public boolean deleteByEntity(Bucket bucket) {
        return deleteById(bucket.getBucketId());
    }

    @Override
    public List<Bucket> getAll() {
        List<Bucket> buckets = new ArrayList<>();
        try (PreparedStatement ps =
                     connection.prepareStatement("SELECT * FROM buckets")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Bucket bucket = get(resultSet.getLong(1)).orElseThrow();
                buckets.add(bucket);
            }
        } catch (SQLException e) {
            logger.error("Can't get all buckets ", e);
        }
        return buckets;
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             "SELECT * FROM buckets WHERE user_id = ?")) {
            ps.setLong(1, userId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return get(resultSet.getLong("bucket_id"));
            }
        } catch (SQLException e) {
            logger.error("Can't get bucket's user with id " + userId, e);
        }
        return Optional.empty();
    }

    public void clear(Bucket bucket) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             "DELETE FROM bucket_items WHERE bucket_id = ?")) {
            ps.setLong(1, bucket.getBucketId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't clear bucket with ID " + bucket.getBucketId(), e);
        }
    }

    private Bucket addItemsToBucket(Bucket bucket) {
        for (Item item : bucket.getItems()) {
            try (PreparedStatement ps =
                         connection.prepareStatement(ADD_ITEMS_TO_BUCKET)) {
                ps.setLong(1, bucket.getBucketId());
                ps.setLong(2, item.getItemId());
            } catch (SQLException e) {
                logger.error("Can't add item with id " + bucket.getBucketId(), e);
            }
        }
        return bucket;
    }

    private List<Item> getAllItems(Long id) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps =
                     connection.prepareStatement(GET_ALL_ITEMS)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setPrice(resultSet.getDouble("price"));
                item.setName(resultSet.getString("name"));
                item.setItemId(resultSet.getLong("item_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get all items from bucket with ID " + id, e);
        }
        return items;
    }
}
