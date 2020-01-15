package mate.academy.internetshop.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public boolean deleteByEntity(User user) {
        return userDao.deleteByEntity(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User login(String login, String psw) {
        Optional<User> user = userDao.findByLogin(login);
        if (user.isEmpty() || !user.get().getPassword().equals(psw)) {
            throw new RuntimeException("Incorrect login or password ");
        }
        return user.get();
    }
}
