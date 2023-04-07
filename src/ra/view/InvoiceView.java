package ra.view;

import ra.controller.invoiceController.InvoiceController;
import ra.controller.user.UserController;
import ra.model.entity.Cart;
import ra.model.entity.CartItem;
import ra.model.entity.Invoice;
import ra.model.entity.User;
import ra.model.service.cart.CartServiceIMPL;

import java.util.List;

import static ra.config.Config.scanner;

public class InvoiceView {
    private static InvoiceController invoiceController = new InvoiceController();
    private  static User userlogin = new UserView().getUserLogin();
    public static void showInvoice(){
      Invoice invoice = invoiceController.findById(userlogin.getUserId());
        List<CartItem> cartItemList = invoice.getInvoiceCart();
        float Total = 0;
        for (CartItem item:cartItemList) {
            Total+= item.getProduct().getProductPrice()*item.getQuantity();
        }
        System.out.printf("Customer: %s\n"+
                        "Address: %s\n"+
                        "Phone: %s\n",
                invoice.getInvoiceUser().getName(),
                invoice.getInvoiceUser().getAddress(),
                invoice.getInvoiceUser().getPhone());
        for (int i = 0; i < cartItemList.size(); i++) {
            System.out.println("Product "+(i+1)+": "+cartItemList.get(i).getProduct().getProductName()+",  "+"Price: "+cartItemList.get(i).getProduct().getProductPrice()+",  "+"Quantity: "+ cartItemList.get(i).getQuantity());
        }
        System.out.println("Total Invoice: "+ Total);

    }
    public void showInvoiceMenu(){
        boolean check = true;
        while (check){
        System.out.println("-------------------YOUR INVOICE-----------------");
        System.out.println("1. Show Invoice");
        System.out.println("2. Exit");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
              showInvoice();
              break;
            case 2:
                check = false;
                break;
            default:
                System.err.println("err");
                break;
        }
    }
    }
    public void createInvoice(){
        Invoice invoice = new Invoice();
        invoice.setInvoiceUser(userlogin);
        Cart mycart = new CartServiceIMPL().findById(userlogin.getUserId());
        List<CartItem> cartItems = mycart.getCartItem();
        invoice.setInvoiceCart(cartItems);
        invoice.setInvoiceStatus("delivery");
        invoice.setFeedback("");
        invoiceController.save(invoice);
    }
}
