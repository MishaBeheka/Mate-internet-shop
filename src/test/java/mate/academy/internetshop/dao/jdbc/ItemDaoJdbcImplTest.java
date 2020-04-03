package mate.academy.internetshop.dao.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemDaoJdbcImplTest {

    private static final String CREATE_ITEM =
            "INSERT INTO internet_shop.items (name, price) VALUES (?, ?)";

    private static final String GET_ITEM_BY_ID =
            "SELECT * FROM internet_shop.items WHERE item_id = ?";

    private static final String UPDATE_ITEM =
            "UPDATE internet_shop.items SET name = ?, price = ? WHERE item_id = ?";

    private static final String DELETE_ITEM_BY_ID =
            "DELETE FROM internet_shop.items WHERE item_id = ?";

    private static final String FIND_ALL_ITEMS =
            "SELECT * FROM internet_shop.items";

    private static final Item TEST_ITEM = new Item();
    private static final Item MOCK_ITEM = new Item();

    private Item item;
    private ItemDaoJdbcImpl itemDaoJdbc;

    private Connection mockConnection = mock(Connection.class);
    private PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
    private ResultSet mockResultSet = mock(ResultSet.class);

    @BeforeEach
    void setUp() {
        TEST_ITEM.setName("Car");
        TEST_ITEM.setPrice(100D);

        MOCK_ITEM.setItemId(1L);
        MOCK_ITEM.setName("Car");
        MOCK_ITEM.setPrice(100D);
        itemDaoJdbc = new ItemDaoJdbcImpl(mockConnection);
    }

    @Test
    public void whenItemSuccessfullySaved() throws SQLException, DataProcessingException {
        when(mockConnection.prepareStatement(CREATE_ITEM,
                Statement.RETURN_GENERATED_KEYS)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getLong(1)).thenReturn(1L);

        item = itemDaoJdbc.create(TEST_ITEM);
        assertEquals(MOCK_ITEM, item);
    }

    @Test
    public void whenHappenTroubleWithDbWhileCreateItem() throws SQLException {
        when(mockConnection.prepareStatement(CREATE_ITEM,
                Statement.RETURN_GENERATED_KEYS)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);

        doThrow(new SQLException()).when(mockPreparedStatement).executeUpdate();
        assertThrows(DataProcessingException.class, () -> itemDaoJdbc.create(TEST_ITEM));

    }

    @Test
    public void whenGotItemSuccessfullyById() throws SQLException, DataProcessingException {
        when(mockConnection.prepareStatement(GET_ITEM_BY_ID)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        generateResultSet(mockResultSet);

        item = itemDaoJdbc.get(MOCK_ITEM.getItemId()).get();
        assertEquals(MOCK_ITEM, item);
    }

    @Test
    public void whenHappenTroubleWithDbWhileGetItemById() throws SQLException {
        when(mockConnection.prepareStatement(GET_ITEM_BY_ID)).thenReturn(mockPreparedStatement);
        doThrow(new SQLException()).when(mockPreparedStatement).executeQuery();

        assertThrows(DataProcessingException.class, () -> itemDaoJdbc.get(MOCK_ITEM.getItemId()));
    }

    @Test
    void whenUpdateItemSuccessfully() throws SQLException, DataProcessingException {
        when(mockConnection.prepareStatement(UPDATE_ITEM)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        Item updatedItem = new Item();
        updatedItem.setItemId(3L);
        updatedItem.setName("Plane");
        updatedItem.setPrice(1000D);

        updatedItem = itemDaoJdbc.update(MOCK_ITEM);
        assertEquals(MOCK_ITEM, updatedItem);
    }

    @Test
    void whenItemWasNotUpdated() throws SQLException {
        when(mockConnection.prepareStatement(UPDATE_ITEM)).thenReturn(mockPreparedStatement);
        doThrow(new SQLException()).when(mockPreparedStatement).executeUpdate();

        assertThrows(DataProcessingException.class, () -> itemDaoJdbc.update(MOCK_ITEM));
    }

    @Test
    void whenTheItemWasSuccessfullyDeleteById() throws SQLException, DataProcessingException {
        when(mockConnection.prepareStatement(DELETE_ITEM_BY_ID)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        assertTrue(itemDaoJdbc.deleteById(MOCK_ITEM.getItemId()));
    }

    @Test
    void whenTheItemWasNotDeletedById() throws SQLException, DataProcessingException {
        when(mockConnection.prepareStatement(DELETE_ITEM_BY_ID)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        assertFalse(itemDaoJdbc.deleteById(MOCK_ITEM.getItemId()));
    }

    @Test
    public void whenHappenTroubleWithDbWhileDeleteItemById() throws SQLException {
        when(mockConnection.prepareStatement(DELETE_ITEM_BY_ID)).thenReturn(mockPreparedStatement);
        doThrow(new SQLException()).when(mockPreparedStatement).executeUpdate();

        assertThrows(DataProcessingException.class, () -> itemDaoJdbc.deleteById(MOCK_ITEM.getItemId()));
    }

    @Test
    void whenGotSuccessfullyAllItems() throws SQLException, DataProcessingException {
        when(mockConnection.prepareStatement(FIND_ALL_ITEMS)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        generateResultSet(mockResultSet);

        List<Item> items = itemDaoJdbc.getAll();
        assertEquals(1, items.size());
        assertEquals(MOCK_ITEM, items.get(0));
    }

    @Test
    void whenHaveEmptyListAfterGetAllItems() throws SQLException, DataProcessingException {
        when(mockConnection.prepareStatement(FIND_ALL_ITEMS)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        List<Item> items = itemDaoJdbc.getAll();
        assertEquals(0, items.size());
    }

    @Test
    public void whenHappenTroubleWithDbWhileGetAllItems() throws SQLException {
        when(mockConnection.prepareStatement(FIND_ALL_ITEMS)).thenReturn(mockPreparedStatement);
        doThrow(new SQLException()).when(mockPreparedStatement).executeQuery();

        assertThrows(DataProcessingException.class, () -> itemDaoJdbc.getAll());

    }

    private void generateResultSet(ResultSet mockResultSet) throws SQLException {
        when(mockResultSet.getLong(1)).thenReturn(MOCK_ITEM.getItemId());
        when(mockResultSet.getString(2)).thenReturn(MOCK_ITEM.getName());
        when(mockResultSet.getDouble(3)).thenReturn(MOCK_ITEM.getPrice());
    }
}