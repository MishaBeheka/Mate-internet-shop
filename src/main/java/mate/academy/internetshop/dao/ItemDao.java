package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.model.Item;

public interface ItemDao extends GenericDao<Item, Long> {
    List<Item> getAllItems();
}
