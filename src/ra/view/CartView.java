package ra.view;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import ra.controller.cart.CartController;
import ra.controller.product.ProductController;
import ra.model.entity.CartItem;
import ra.model.entity.Product;
import ra.model.entity.User;

import static ra.config.Config.scanner;

public class CartView {
    private static CartController cartController = new CartController();
    private static ProductController productController = new ProductController();
    User userLogin = new UserView().getUserLogin();

    public void showCart() {
        if (cartController.getListCart().size()!=0){
            float total = 0;
            for (CartItem cartItem : cartController.getListCart()) {
                System.out.printf("Product ID: %d \n" +
                        "Product Catalog: %s \n"+
                        "Product Name: %s\n" +
                        "Price: %.1f \n" +
                        "Quantity: %d \n" ,cartItem.getProduct().getProductId(),cartItem.getProduct().getProductCatalog().getName(), cartItem.getProduct().getProductName(), cartItem.getProduct().getProductPrice(), cartItem.getQuantity());
                total += cartItem.getProduct().getProductPrice() * cartItem.getQuantity();
                System.out.println("---------------------------------------");
            }
            System.out.println();
            System.out.println("total: " + total + " $\n");
        }else {
            System.err.println("Cart is empty, please add to cart");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            new UserView().proFileView();
        }

    }

    public void addToCart() {
        if (userLogin.getPhone()!=null&&userLogin.getAddress()!=null){
            new ProductView().ShowProduct();
            System.out.println("Enter product id");
            int id = Integer.parseInt(scanner.nextLine());
            Product product = productController.findById(id);
            if (product != null) {
                System.out.println("Enter the quantity:");
                int quantity = Integer.parseInt(scanner.nextLine());
                cartController.addToCart(new CartItem(product, quantity));
                System.out.println("add to cart success");
            } else {
                System.err.println(" id not found:");
                addToCart();
            }
        }else {
            System.err.println("Please update your account to buy!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            new UserView().proFileView();
        }

    }

    public void deleteItem() {
        System.out.println("Enter the id to delete");
        int id = Integer.parseInt(scanner.nextLine());
        cartController.delete(id);
        System.out.println("delete success!");
        showCart();
    }

    public void updateCart() {
        System.out.println("Enter the id to update:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the quantity to update");
        int quantity = Integer.parseInt(scanner.nextLine());
        CartItem cartItem = new CartItem(productController.findById(id), quantity);
        cartController.updateCart(cartItem);
        showCart();
    }
    public void deleteAllCart(){
        cartController.deleteAllCart();
    }
}
