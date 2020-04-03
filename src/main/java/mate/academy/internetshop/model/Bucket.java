package mate.academy.internetshop.model;

import java.util.List;
import java.util.Objects;

public class Bucket {
    private Long bucketId;
    private List<Item> items;
    private Long userId;

    public Bucket(){

    }

    public Bucket(List<Item> items, Long userId) {
        this.items = items;
        this.userId = userId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bucket bucket = (Bucket) o;
        return Objects.equals(bucketId, bucket.bucketId)
                && Objects.equals(items, bucket.items)
                && Objects.equals(userId, bucket.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucketId, items, userId);
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "bucketId=" + bucketId
                + ", items=" + items
                + ", userId=" + userId
                + '}';
    }
}

