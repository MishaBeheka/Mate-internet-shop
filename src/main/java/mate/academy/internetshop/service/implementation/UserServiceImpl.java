package mate.academy.internetshop.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) throws DataProcessingException {
        user.setToken(getToken());
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.create(user);
    }

    @Override
    public User get(Long id) throws DataProcessingException {
        return userDao.get(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User update(User user) throws DataProcessingException {
        return userDao.update(user);
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return userDao.deleteById(id);
    }

    @Override
    public boolean deleteByEntity(User user) throws DataProcessingException {
        return userDao.deleteByEntity(user);
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        return userDao.getAll();
    }

    @Override
    public User login(String login, String psw)
            throws AuthenticationException, DataProcessingException {
        Optional<User> user = userDao.findByLogin(login);
        if (user.isPresent() && user.get().getUserId() != null) {
            String hashPassword = HashUtil.hashPassword(psw, user.get().getSalt());
            if (hashPassword.equals(user.get().getPassword())) {
                return user.get();
            }
        }
        throw new AuthenticationException("Incorrect login or password ");
    }

    @Override
    public Optional<User> findByToken(String token) throws DataProcessingException {
        return userDao.findByToken(token);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}
