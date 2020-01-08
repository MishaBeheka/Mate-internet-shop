package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional get(Long id) {
        return Optional.ofNullable(Storage.items
                .stream()
                .filter(i -> i.getItemId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find item with id " + id)));
    }

    @Override
    public Item update(Item item) {
        return Storage.items
                .stream()
                .filter(item1 -> item1.getItemId().equals(item.getItemId()))
                .findFirst()
                .map(updateItem -> {
                    updateItem.setName(item.getName());
                    updateItem.setPrice(item.getPrice());
                    return updateItem;
                })
                .orElseThrow(() -> new RuntimeException("Item isn't updated " + item));
    }

    @Override
    public boolean delete(Long id) {
        return Storage.items.removeIf(removedItem -> removedItem.getItemId().equals(id));
    }

    @Override
    public boolean delete(Item item) {
        return Storage.items.removeIf(removedItem -> removedItem.equals(item));
    }
}
