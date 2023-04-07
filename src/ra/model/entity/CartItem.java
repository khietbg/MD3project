package ra.model.entity;

import java.io.Serializable;
import java.util.StringJoiner;

public class CartItem implements Serializable {
    private Product product;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CartItem.class.getSimpleName() + "[", "]")
                .add("product=" + product)
                .add("quantity=" + quantity)
                .toString();
    }
}
