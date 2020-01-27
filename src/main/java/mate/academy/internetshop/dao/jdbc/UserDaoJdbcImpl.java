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
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);
    private static String DB_NAME_USERS = "internet_shop.users";
    private static String DB_NAME_USER_ROLES = "internet_shop.user_roles";

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO " + DB_NAME_USERS
                             + " (first_name, last_name, login, password, token)"
                             + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getToken());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setUserId(resultSet.getLong(1));
            }
            try (PreparedStatement prStatementRole =
                         connection.prepareStatement("INSERT INTO " + DB_NAME_USER_ROLES
                                 + " (user_id, role_id) VALUES (?, ?)")) {
                prStatementRole.setLong(1, user.getUserId());
                prStatementRole.setLong(2, 1);
                prStatementRole.executeUpdate();
            }
        } catch (SQLException e) {
            logger.warn("User wasn't created " + e);
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT * FROM internet_shop.users "
                                     + "INNER JOIN internet_shop.user_roles "
                                     + "ON users.user_id = user_roles.user_id "
                                     + "INNER JOIN internet_shop.role "
                                     + "using (role_id) "
                                     + "WHERE internet_shop.users.user_id = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user.setUserId(resultSet.getLong("user_id"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setToken(resultSet.getString("token"));
                    Role role = Role.of(resultSet.getString("role_name"));
                    roles.add(role);
                }
                user.setRoles(roles);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.info("Can't find user with id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public User update(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "UPDATE internet_shop.users SET"
                                     + " first_name = ?,"
                                     + " last_name = ?, "
                                     + " login = ?,"
                                     + " password = ?, "
                                     + " token = ? WHERE user_id = " + user.getUserId())) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getToken());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info("User wasn't updated ", e);
        }
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM internet_shop.users WHERE user_id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.info("Can't delete user with id " + id, e);
        }
        return false;
    }

    @Override
    public boolean deleteByEntity(User user) {
        return deleteById(user.getUserId());
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement prSt = connection.prepareStatement("SELECT * FROM internet_shop.users")) {
            try (ResultSet resultSet = prSt.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getLong(1));
                    user.setFirstName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setLogin(resultSet.getString(4));
                    user.setPassword(resultSet.getString(5));
                    user.setToken(resultSet.getString(6));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.info("Can't get all users ", e);
        }
        return users;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT * FROM internet_shop.users "
                                     + "INNER JOIN internet_shop.user_roles "
                                     + "ON users.user_id = user_roles.user_id "
                                     + "INNER JOIN internet_shop.role "
                                     + "using (role_id) "
                                     + "WHERE internet_shop.users.login = ?")) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user.setUserId(resultSet.getLong("user_id"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setToken(resultSet.getString("token"));
                    Role role = Role.of(resultSet.getString("role_name"));
                    roles.add(role);
                }
                user.setRoles(roles);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.info("Can't find user with login " + login, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByToken(String token) {
        User user = new User();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT user_id, first_name, last_name, login, password, token FROM "
                                     + DB_NAME_USERS + " WHERE token = ?")) {
            preparedStatement.setString(1, token);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user.setUserId(resultSet.getLong(1));
                    user.setFirstName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setLogin(resultSet.getString(4));
                    user.setPassword(resultSet.getString(5));
                    user.setToken(resultSet.getString(6));
                }
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.info("Can't find user with token " + token, e);
        }
        return Optional.empty();
    }
}
