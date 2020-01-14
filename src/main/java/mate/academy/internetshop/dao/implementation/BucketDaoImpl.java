package mate.academy.internetshop.dao.implementation;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        return Storage.buckets
                .stream()
                .filter(bucket -> bucket.getBucketId().equals(bucketId))
                .findFirst();
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) {
        return Storage.buckets.stream()
                .filter(bucket1 -> bucket1.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public Bucket update(Bucket bucket) {
        return Storage.buckets
                .stream()
                .filter(bucket1 -> bucket1.getBucketId().equals(bucket.getBucketId()))
                .findFirst()
                .map(updateBucket -> {
                    updateBucket.setUserId(bucket.getUserId());
                    updateBucket.setItems(bucket.getItems());
                    return updateBucket;
                })
                .orElseThrow(() -> new RuntimeException("Bucket isn't updated " + bucket));
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.buckets
                .removeIf(deletedBucket -> deletedBucket.getBucketId().equals(id));
    }

    @Override
    public boolean deleteByEntity(Bucket bucket) {
        return Storage.buckets
                .removeIf(deletedBucket -> deletedBucket.equals(bucket));
    }

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }
}
