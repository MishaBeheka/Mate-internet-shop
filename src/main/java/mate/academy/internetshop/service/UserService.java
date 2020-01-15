package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.User;

public interface UserService extends GenericService<User, Long> {
    List<User> getAll();

    User login(String login, String psw);

    Optional<User> findByToken(String token);
}
