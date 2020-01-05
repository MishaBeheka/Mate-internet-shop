package mate.academy.internetshop.model;

import java.time.LocalDate;
import java.util.Objects;

public class Order {

    private static Long id = 0L;
    private Long orderId;
    private LocalDate date;
    private Bucket bucket;
    private String deliveryAddress;

    public Order(Bucket bucket, String deliveryAddress) {
        this.orderId = ++id;
        this.date = LocalDate.now();
        this.bucket = bucket;
        this.deliveryAddress = deliveryAddress;
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
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
                && Objects.equals(bucket, order.bucket)
                && Objects.equals(deliveryAddress, order.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, date, bucket, deliveryAddress);
    }

    @Override
    public String toString() {
        return "Order{"
                + "orderId=" + orderId
                + ", date=" + date
                + ", bucket=" + bucket
                + ", deliveryAddress='" + deliveryAddress + '\''
                + '}';
    }
}
