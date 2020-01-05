package mate.academy.internetshop.model;

import java.util.List;
import java.util.Objects;

public class Bucket {
    private static Long id = 0L;
    private Long bucketId;
    private List<Item> items;
    private User user;

    public Bucket(List<Item> items, User user) {
        this.bucketId = ++id;
        this.items = items;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                && Objects.equals(user, bucket.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucketId, items, user);
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "bucketId=" + bucketId
                + ", items=" + items
                + ", user=" + user
                + '}';
    }
}

