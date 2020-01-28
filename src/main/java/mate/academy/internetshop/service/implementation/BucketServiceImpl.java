package mate.academy.internetshop.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
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
    public Bucket create(Bucket bucket) throws DataProcessingException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) throws DataProcessingException {
        return bucketDao.get(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Bucket getByUserId(Long userId) throws DataProcessingException {
        return bucketDao.getByUserId(userId).orElse(create(new Bucket(new ArrayList<>(),userId)));
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return bucketDao.deleteById(id);
    }

    @Override
    public boolean deleteByEntity(Bucket bucket) throws DataProcessingException {
        return bucketDao.deleteByEntity(bucket);
    }

    @Override
    public void addItem(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().add(item);
        update(bucket);

    }

    @Override
    public void deleteItem(Bucket bucket, Item item) throws DataProcessingException {
        Bucket upBucket = bucketDao.get(bucket.getBucketId()).get();
        List<Item> itemsBucket = upBucket.getItems();
        itemsBucket.remove(item);
        bucketDao.update(upBucket);
    }

    @Override
    public void clear(Bucket bucket) throws DataProcessingException {
        Bucket bufferBucket = bucketDao.get(bucket.getBucketId()).get();
        bufferBucket.getItems().clear();
        bucketDao.update(bufferBucket);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) throws DataProcessingException {
        return bucketDao.get(bucket.getBucketId()).get().getItems();
    }
}
