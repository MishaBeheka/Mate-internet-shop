package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static final String CREATE_USER =
            "INSERT INTO users "
                    + "(first_name, last_name, login, password, token, salt)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_USER =
            "SELECT * FROM users "
                    + "INNER JOIN internet_shop.user_roles "
                    + "ON users.user_id = user_roles.user_id "
                    + "INNER JOIN internet_shop.role "
                    + "using (role_id) "
                    + "WHERE internet_shop.users.user_id = ?";

    private static final String UPDATE_USER =
            "UPDATE users SET"
                    + " first_name = ?,"
                    + " last_name = ?, "
                    + " login = ?,"
                    + " password = ?, "
                    + " token = ?, "
                    + " salt = ? "
                    + " WHERE user_id = ?";

    private static final String FIND_BY_LOGIN =
            "SELECT * FROM users "
                    + "INNER JOIN user_roles "
                    + "ON users.user_id = user_roles.user_id "
                    + "INNER JOIN role "
                    + "using (role_id) "
                    + "WHERE users.login = ?";

    private static final String FIND_BY_TOKEN =
            "SELECT user_id, first_name, last_name, login, password, token, salt "
                    + "FROM users WHERE token = ?";

    private static final String ADD_USER_ROLES =
            "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) throws DataProcessingException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            generatePreparedStatement(preparedStatement, user).executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setUserId(resultSet.getLong(1));
            }
            setUserRole(user);
        } catch (SQLException e) {
            throw new DataProcessingException("User wasn't created " + e);
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) throws DataProcessingException {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_USER)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                generateUser(resultSet, user);
                Role role = Role.of(resultSet.getString("role_name"));
                roles.add(role);
            }
            user.setRoles(roles);
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find user with id " + id + e);
        }
    }

    @Override
    public User update(User user) throws DataProcessingException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_USER)) {
            generatePreparedStatement(preparedStatement, user);
            preparedStatement.setLong(7, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("User wasn't updated " + e);
        }
        return user;
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        String query = "DELETE FROM internet_shop.users WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete user with id " + id + e);
        }
    }

    @Override
    public boolean deleteByEntity(User user) throws DataProcessingException {
        return deleteById(user.getUserId());
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement prSt = connection.prepareStatement(query)) {
            try (ResultSet resultSet = prSt.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    generateUser(resultSet, user);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all users " + e);
        }
        return users;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DataProcessingException {
        return findUserBySpecialParam(login, FIND_BY_LOGIN);
    }

    @Override
    public Optional<User> findByToken(String token) throws DataProcessingException {
        return findUserBySpecialParam(token, FIND_BY_TOKEN);
    }

    private Optional<User> findUserBySpecialParam(String searchParam, String query)
            throws DataProcessingException {
        User user = new User();
//        Set<Role> roles = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, searchParam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                generateUser(resultSet, user);
//                Role role = Role.of(resultSet.getString("role_name"));
//                roles.add(role);
            }
//            user.setRoles(roles);
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find user with search param "
                    + searchParam + e);
        }
    }

    private PreparedStatement generatePreparedStatement(PreparedStatement ps, User user)
            throws SQLException {
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getLogin());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getToken());
        ps.setBytes(6, user.getSalt());
        return ps;
    }

    private User generateUser(ResultSet resultSet, User user) throws SQLException {
        user.setUserId(resultSet.getLong("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setToken(resultSet.getString("token"));
        user.setSalt(resultSet.getBytes("salt"));
        return user;
    }

    private void setUserRole(User user) throws SQLException {
        try (PreparedStatement prStatementRole =
                     connection.prepareStatement(ADD_USER_ROLES)) {
            prStatementRole.setLong(1, user.getUserId());
            prStatementRole.setLong(2, 1);
            prStatementRole.executeUpdate();
        }
    }
}
