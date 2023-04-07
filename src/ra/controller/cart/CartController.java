package ra.controller.cart;

import ra.model.entity.Cart;
import ra.model.entity.CartItem;
import ra.model.service.cart.CartServiceIMPL;
import ra.model.service.cart.ICartService;

import java.util.List;

public class CartController implements ICartController{
    private static ICartService cartService = new CartServiceIMPL();
    private static List<Cart> cartList = cartService.findAll();

    public List<CartItem> getListCart() {
        Cart myCart= cartService.findById(cartService.getUserLogin().getUserId());
        return myCart.getCartItem();
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public void save(Cart cart) {

    }

    @Override
    public Cart findById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {
        cartService.delete(id);
    }

    @Override
    public List<Cart> searchByName(String searchName) {
        return null;
    }

    @Override
    public void addToCart(CartItem cartItem) {
        cartService.addToCart(cartItem);
    }
    public void updateCart(CartItem cartItem){
        cartService.updateCart(cartItem);
    }
    public void deleteAllCart(){
        cartService.deleteAllCart();
    }
}
