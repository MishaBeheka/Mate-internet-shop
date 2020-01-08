package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional get(Long id) {
        return Optional.ofNullable(Storage.users
                .stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id " + id)));
    }

    @Override
    public User update(User user) {
        return Storage.users
                .stream()
                .filter(updateUser -> updateUser.getUserId().equals(user.getUserId()))
                .findFirst()
                .map(updateUser -> {
                    updateUser.setFirstName(user.getFirstName());
                    updateUser.setLastName(user.getLastName());
                    updateUser.setAddress(user.getAddress());
                    updateUser.setLogin(user.getLogin());
                    updateUser.setPassword(user.getPassword());
                    updateUser.setPhone(user.getPhone());
                    return updateUser;
                })
                .orElseThrow(() -> new RuntimeException("User isn't update " + user));
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users
                .removeIf(user -> user.getUserId().equals(id));
    }

    @Override
    public boolean delete(User user) {
        return Storage.users
                .removeIf(user1 -> user1.equals(user));
    }
}
