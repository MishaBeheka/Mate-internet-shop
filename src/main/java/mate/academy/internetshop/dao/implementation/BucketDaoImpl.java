package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
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
    public Optional get(Long bucketId) {
        return Optional.ofNullable(Storage.buckets
                .stream()
                .filter(bucket -> bucket.getBucketId().equals(bucketId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id " + bucketId)));
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
    public boolean delete(Long id) {
        return Storage.buckets
                .removeIf(deletedBucket -> deletedBucket.getBucketId().equals(id));
    }

    @Override
    public boolean delete(Bucket bucket) {
        return Storage.buckets
                .removeIf(deletedBucket -> deletedBucket.equals(bucket));
    }
}
