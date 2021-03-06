package mate.academy.internetshop.model;

import java.util.Objects;

public class Item {
    private Long itemId;
    private String name;
    private Double price;

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId)
                && Objects.equals(name, item.name)
                && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, price);
    }

    @Override
    public String toString() {
        return "Item{"
                + "itemId=" + itemId
                + ", name='" + name + '\''
                + ", price=" + price
                + '}';
    }
}
