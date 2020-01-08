package mate.academy.internetshop.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order {

    private static Long idGenerator = 0L;
    private Long orderId;
    private LocalDate date;
    private Long userId;
    private List<Item> items;

    public Order(Long userId, List<Item> items) {
        this.orderId = ++idGenerator;
        this.date = LocalDate.now();
        this.userId = userId;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
                && Objects.equals(date, order.date)
                && Objects.equals(userId, order.userId)
                && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, date, userId, items);
    }

    @Override
    public String toString() {
        return "Order{"
                + "orderId=" + orderId
                + ", date=" + date
                + ", userId=" + userId
                + ", items='" + items + '\''
                + '}';
    }
}
