package mate.academy.internetshop.dao.implementation;

import java.util.List;
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
    public Optional<User> get(Long id) {
        return Storage.users
                .stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst();
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
    public boolean deleteById(Long id) {
        return Storage.users
                .removeIf(user -> user.getUserId().equals(id));
    }

    @Override
    public boolean deleteByEntity(User user) {
        return Storage.users
                .removeIf(user1 -> user1.equals(user));
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Storage.users
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }
}
