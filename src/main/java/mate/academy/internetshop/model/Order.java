package mate.academy.internetshop.model;

import java.util.List;
import java.util.Objects;

public class Order {

    private Long orderId;
    private Long userId;
    private List<Item> items;

    public Order() {}

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
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
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId)
                && Objects.equals(userId, order.userId)
                && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId,userId, items);
    }

    @Override
    public String toString() {
        return "Order{"
                + "orderId=" + orderId
                + ", userId=" + userId
                + ", items='" + items + '\''
                + '}';
    }
}
