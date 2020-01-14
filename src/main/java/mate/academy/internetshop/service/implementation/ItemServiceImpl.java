package mate.academy.internetshop.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public boolean deleteById(Long id) {
        return itemDao.deleteById(id);
    }

    @Override
    public boolean deleteByEntity(Item item) {
        return itemDao.deleteByEntity(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }
}
