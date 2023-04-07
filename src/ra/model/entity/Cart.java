package ra.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

public class Cart implements Serializable {
    private User user;
    private List<CartItem> cartItem;

    public Cart() {
    }

    public Cart(User user, List<CartItem> cartItem) {
        this.user = user;
        this.cartItem = cartItem;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Cart.class.getSimpleName() + "[", "]")
                .add("user=" + user)
                .add("cartItem=" + cartItem)
                .toString();
    }
}
