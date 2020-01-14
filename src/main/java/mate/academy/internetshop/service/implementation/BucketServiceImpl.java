package mate.academy.internetshop.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;
    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Bucket getByUserId(Long userId) {
        return bucketDao.getByUserId(userId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean deleteById(Long id) {
        return bucketDao.deleteById(id);
    }

    @Override
    public boolean deleteByEntity(Bucket bucket) {
        return bucketDao.deleteByEntity(bucket);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        bucketDao.getAll()
                .stream()
                .filter(elementOfBucket -> elementOfBucket.equals(bucket))
                .findFirst()
                .map(neededBucket -> neededBucket.getItems().add(item));

    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        bucketDao.getAll()
                .stream()
                .filter(neededBucket -> neededBucket.equals(bucket))
                .findFirst()
                .map(neededBucket -> neededBucket.getItems().remove(item));

    }

    @Override
    public void clear(Bucket bucket) {
        bucketDao.getAll()
                .stream()
                .filter(bucket1 -> bucket1.equals(bucket))
                .findFirst()
                .map(b -> {
                    b.getItems().clear();
                    return b;
                });
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucketDao.getAll()
                .stream()
                .filter(bucket1 -> bucket1.equals(bucket))
                .map(Bucket::getItems)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
