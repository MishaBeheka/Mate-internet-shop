package mate.academy.internetshop.dao.implementation;

import java.util.List;
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
    public Optional<Item> get(Long id) {
        return Storage.items
                .stream()
                .filter(i -> i.getItemId().equals(id))
                .findFirst();

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
    public boolean deleteById(Long id) {
        return Storage.items.removeIf(removedItem -> removedItem.getItemId().equals(id));
    }

    @Override
    public boolean deleteByEntity(Item item) {
        return Storage.items.removeIf(removedItem -> removedItem.equals(item));
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }
}
