package mate.academy.internetshop.service;

import java.util.Optional;
import mate.academy.internetshop.model.User;

public interface UserService {
    User create(User user);

    Optional get(Long id);

    User update(User user);

    boolean delete(Long id);

    boolean delete(User user);
}
