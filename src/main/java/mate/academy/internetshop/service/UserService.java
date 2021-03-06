package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.User;

public interface UserService extends GenericService<User, Long> {
    List<User> getAll() throws DataProcessingException;

    User login(String login, String psw) throws DataProcessingException;

    Optional<User> findByToken(String token) throws DataProcessingException;
}
