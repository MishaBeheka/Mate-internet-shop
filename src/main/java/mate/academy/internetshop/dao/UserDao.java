package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.User;

import java.util.Optional;

public interface UserDao {

    User create(User user);

    Optional get(Long id);

    User update(User user);

    boolean delete(Long id);

    boolean delete(User user);
}
