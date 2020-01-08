package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Item;

import java.util.Optional;

public interface ItemDao {

    Item create(Item item);

    Optional get(Long id);

    Item update(Item item);

    boolean delete(Long id);

    boolean delete(Item item);
}
