package ra.model.service.cart;

import ra.config.Config;
import ra.model.entity.Cart;
import ra.model.entity.CartItem;
import ra.model.entity.User;
import ra.model.service.product.IProductService;
import ra.model.service.product.ProductServiceIMPL;
import ra.model.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class CartServiceIMPL implements ICartService {
    private static List<Cart> cartList = new Config<Cart>().readFromFile(Config.PATH_CART);
    private static User userLogin = new UserService().getCurrentLogin();
    private static IProductService productServiceIMPL = new ProductServiceIMPL();
    @Override
    public List<Cart> findAll() {
        return cartList;
    }

    @Override
    public void save(Cart cart) {
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getUser().equals(cart.getUser())){
                cartList.set(i,cart);
                new Config<Cart>().writeToFile(Config.PATH_CART,cartList);
                return;
            }
        }
        cartList.add(cart);
        new Config<Cart>().writeToFile(Config.PATH_CART,cartList);
    }

    @Override
    public Cart findById(int id) {
        for (Cart cart:cartList) {
            if (cart.getUser().getUserId()==id){
                return cart;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        Cart myCart=findById(userLogin.getUserId());
        List<CartItem> itemList = myCart.getCartItem();
        for (CartItem cartItem: itemList) {
            if (cartItem.getProduct().getProductId()==id){
                itemList.remove(cartItem);
                myCart.setCartItem(itemList);
                save(myCart);
                return;
            }
        }
        System.err.println("id not found!");
    }

    @Override
    public List<Cart> searchByName(String searchName) {
        return null;
    }

    @Override
    public void addToCart(CartItem cartItem) {
        Cart carts = findById(userLogin.getUserId());
        if (carts!=null){
        for (CartItem cart:carts.getCartItem()) {
            if (cart.getProduct().getProductId()==cartItem.getProduct().getProductId()){
                cart.setQuantity(cart.getQuantity()+cartItem.getQuantity());
                save(carts);
                return;
            }
        }
        List<CartItem> cartItemList = carts.getCartItem();
        cartItemList.add(cartItem);
        carts.setCartItem(cartItemList);
        save(carts);
        }else {
            List<CartItem> cartItemList = new ArrayList<>();
            cartItemList.add(cartItem);
            Cart newCart= new Cart(userLogin,cartItemList);
            save(newCart);

        }

    }
    @Override
    public User getUserLogin() {
        return userLogin;
    }

    @Override
    public void updateCart(CartItem cartItem) {
        Cart mycart = findById(userLogin.getUserId());
        List<CartItem> itemList = mycart.getCartItem();
        for (CartItem Item:itemList) {
            if (Item.getProduct().getProductId()==cartItem.getProduct().getProductId()){
                Item.setQuantity(cartItem.getQuantity());
                save(mycart);
                return;
            }
        }
        System.err.println("Id not found");
    }

    @Override
    public void deleteAllCart() {
        Cart myCart=findById(userLogin.getUserId());
        List<CartItem> cartItemList = myCart.getCartItem();
        cartItemList.removeAll(cartItemList);
        save(myCart);
    }
}
