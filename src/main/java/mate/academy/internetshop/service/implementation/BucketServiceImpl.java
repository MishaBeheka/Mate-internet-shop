package mate.academy.internetshop.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
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
    public Optional get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Long id) {
        return bucketDao.delete(id);
    }

    @Override
    public boolean delete(Bucket bucket) {
        return bucketDao.delete(bucket);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        Storage.buckets
                .stream()
                .filter(elementOfBucket -> elementOfBucket.equals(bucket))
                .findFirst()
                .map(neededBucket -> neededBucket.getItems().add(item));

    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        Storage.buckets
                .stream()
                .filter(neededBucket -> neededBucket.equals(bucket))
                .findFirst()
                .map(neededBucket -> neededBucket.getItems().remove(item));

    }

    @Override
    public void clear(Bucket bucket) {
        Storage.buckets
                .stream()
                .filter(bucket1 -> bucket1.equals(bucket))
                .findFirst()
                .map(b -> {
                    b.getItems().clear();
                    return b;
                });
    }

    @Override
    public List getAllItems(Bucket bucket) {
        return Storage.buckets
                .stream()
                .filter(bucket1 -> bucket1.equals(bucket))
                .map(bucket1 -> bucket1.getItems())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
