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
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    private static final String GET_ALL_ITEMS = "SELECT items.item_id, "
            + "items.name, items.price "
            + "FROM items INNER JOIN bucket_items "
            + "ON items.item_id = bucket_items.item_id "
            + "WHERE bucket_items.bucket_id = ?";
    private static final String ADD_ITEMS_TO_BUCKET =
            "INSERT INTO bucket_items (bucket_id, item_id) VALUES (?, ?)";

    private static final String CREATE_BUCKET =
            "INSERT INTO buckets (user_id) VALUES (?)";

    private static final String GET_BUCKET =
            "SELECT * FROM buckets WHERE bucket_id = ?";

    private static final String DELETE_ITEMS_BY_BUCKET_ID =
            "DELETE FROM bucket_items WHERE bucket_id = ?";

    private static final String DELETE_BUCKET_BY_ID =
            "DELETE FROM buckets WHERE bucket_id = ?";

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        try (PreparedStatement ps = connection.prepareStatement(
                CREATE_BUCKET, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, bucket.getUserId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                bucket.setBucketId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create bucket " + e);
        }
        return addItemsToBucket(bucket);
    }

    @Override
    public Optional<Bucket> get(Long id) throws DataProcessingException {
        try (PreparedStatement ps =
                     connection.prepareStatement(GET_BUCKET)) {
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
            throw new DataProcessingException("Can't get bucket with ID " + id + e);
        }
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        clear(bucket.getBucketId(), DELETE_ITEMS_BY_BUCKET_ID);
        for (Item item : bucket.getItems()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO bucket_items (bucket_id, item_id) VALUES (?, ?)")) {
                ps.setLong(1, bucket.getBucketId());
                ps.setLong(2, item.getItemId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new DataProcessingException("Can't add item or bucket to bucket_items" + e);
            }
        }
        return bucket;
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return clear(id, DELETE_ITEMS_BY_BUCKET_ID) && clear(id, DELETE_BUCKET_BY_ID);
    }

    @Override
    public boolean deleteByEntity(Bucket bucket) throws DataProcessingException {
        return deleteById(bucket.getBucketId());
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
        List<Bucket> buckets = new ArrayList<>();
        try (PreparedStatement ps =
                     connection.prepareStatement("SELECT * FROM buckets")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Bucket bucket = get(resultSet.getLong(1)).orElseThrow();
                buckets.add(bucket);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all buckets " + e);
        }
        return buckets;
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) throws DataProcessingException {
        String query = "SELECT * FROM buckets WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, userId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return get(resultSet.getLong("bucket_id"));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get bucket's user with id " + userId + e);
        }
        return Optional.empty();
    }

    private boolean clear(Long id, String query) throws DataProcessingException {
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException(
                    "Can't execute action with QUERY " + query + " and ID " + id + e);
        }
    }

    private Bucket addItemsToBucket(Bucket bucket) throws DataProcessingException {
        for (Item item : bucket.getItems()) {
            try (PreparedStatement ps =
                         connection.prepareStatement(ADD_ITEMS_TO_BUCKET)) {
                ps.setLong(1, bucket.getBucketId());
                ps.setLong(2, item.getItemId());
            } catch (SQLException e) {
                throw new DataProcessingException(
                        "Can't add item with id " + bucket.getBucketId() + e);
            }
        }
        return bucket;
    }

    private List<Item> getAllItems(Long id) throws DataProcessingException {
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
            throw new DataProcessingException("Can't get all items from bucket with ID " + id + e);
        }
        return items;
    }
}
