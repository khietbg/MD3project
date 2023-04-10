package ra.view;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import ra.controller.invoiceController.InvoiceController;
import ra.controller.user.UserController;
import ra.model.entity.*;
import ra.model.service.cart.CartServiceIMPL;

import java.util.List;

import static ra.config.Config.scanner;

public class InvoiceView {
    private static InvoiceController invoiceController = new InvoiceController();
    private static User userlogin = new UserView().getUserLogin();

    public static void showInvoice() {
        Invoice invoice = invoiceController.findById(userlogin.getUserId());
        List<CartItem> cartItemList = invoice.getInvoiceCart();
        float Total = 0;
        for (CartItem item : cartItemList) {
            Total += item.getProduct().getProductPrice() * item.getQuantity();
        }
        System.out.printf("Customer: %s\n" +
                        "Address: %s\n" +
                        "Phone: %s\n",
                invoice.getInvoiceUser().getName(),
                invoice.getInvoiceUser().getAddress(),
                invoice.getInvoiceUser().getPhone());
        for (int i = 0; i < cartItemList.size(); i++) {
            System.out.println("Product " + (i + 1) + ": " + cartItemList.get(i).getProduct().getProductName() + ",  " + "Price: " + cartItemList.get(i).getProduct().getProductPrice() + ",  " + "Quantity: " + cartItemList.get(i).getQuantity());
        }
        System.out.println("Total Invoice: " + Total);
    }

    public void showInvoiceMenu() {
        boolean check = true;
        while (check) {
            System.out.println("-------------------YOUR INVOICE-----------------");
            System.out.println("1. Show Invoice");
            System.out.println("2. Pay ");
            System.out.println("3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showInvoice();
                    break;
                case 2:
                    showInvoice();
                    pay();
                case 3:
                    check = false;
                    break;
                default:
                    System.err.println("err");
                    break;
            }
        }
    }
    public void payment(){
        boolean check = true;
        while (check)
        System.out.println("----------------PAYMENT---------------");
        System.out.println("1. Pay");
        System.out.println("2. Exit");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
                pay();
                System.out.println(" Thankyou So Much, See you again !");
                break;
            case 2:
                check = false;
                break;
            default:
                System.err.println("error");
                break;
        }
    }
    public void createInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceUser(userlogin);
        Cart mycart = new CartServiceIMPL().findById(userlogin.getUserId());
        List<CartItem> cartItems = mycart.getCartItem();
        invoice.setInvoiceCart(cartItems);
        invoice.setInvoiceStatus("delivery");
        invoice.setFeedback("");
        invoiceController.save(invoice);
    }
    public void pay(){
        Invoice invoice = invoiceController.findById(userlogin.getUserId());
        System.out.println("Enter the customer feedback: ");
        String feedback = scanner.nextLine();
        Invoice invoiceHistory = new Invoice(invoice.getInvoiceUser(),invoice.getInvoiceCart(),"completed",feedback);
        System.out.println("invoice history-->"+ invoiceHistory);
        invoiceController.saveInvoiceHistory(invoiceHistory);
        new CartView().deleteAllCart();
        invoiceController.deleteInvoice(userlogin.getUserId());
        System.out.println("thank you very much, see you again! ");
        new Main().viewUser();
    }
    public void showInvoiceHistory(){
        Invoice invoice = invoiceController.showInvoiceHistory(userlogin.getUserId());
        List<CartItem> cartItems = invoice.getInvoiceCart();
        float Total = 0;
        for (CartItem item : cartItems) {
            Total += item.getProduct().getProductPrice() * item.getQuantity();
        }
        System.out.printf("Customer: %s\n" +
                        "Address: %s\n" +
                        "Phone: %s\n",
                invoice.getInvoiceUser().getName(),
                invoice.getInvoiceUser().getAddress(),
                invoice.getInvoiceUser().getPhone());
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println("Product " + (i + 1) + ": " + cartItems.get(i).getProduct().getProductName() + ",  " + "Price: " + cartItems.get(i).getProduct().getProductPrice() + ",  " + "Quantity: " + cartItems.get(i).getQuantity());
        }
        System.out.println("Total Invoice: " + Total);
    }
    public void invoiceManagement(){
        boolean check = true;
        while (check) {
            System.out.println("*******************INVOICE MANAGEMENT**************");
            System.out.println("1. Show Invoice History");
            System.out.println("2. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showAllInvoice();
                    break;
                case 2:
                    check = false;
                    break;
                default:
                    System.err.println("error");
                    break;
            }
        }
    }
    public void showAllInvoice(){
        List<Invoice> invoiceList = invoiceController.showAllInvoiceHistory();
        for (Invoice invoice:invoiceList) {
            List<CartItem> cartItems = invoice.getInvoiceCart();
            float Total = 0;
            for (CartItem item : cartItems) {
                Total += item.getProduct().getProductPrice() * item.getQuantity();
            }
            System.out.printf("Customer: %s\n" +
                            "Address: %s\n" +
                            "Phone: %s\n",
                    invoice.getInvoiceUser().getName(),
                    invoice.getInvoiceUser().getAddress(),
                    invoice.getInvoiceUser().getPhone());
            for (int i = 0; i < cartItems.size(); i++) {
                System.out.println("Product " + (i + 1) + ": " + cartItems.get(i).getProduct().getProductName() + ",  " + "Price: " + cartItems.get(i).getProduct().getProductPrice() + ",  " + "Quantity: " + cartItems.get(i).getQuantity());
            }
            System.out.println("Feedback: "+ invoice.getFeedback());
            System.out.println("Invoice Status: "+ invoice.getInvoiceStatus());
            System.out.println("Total Invoice: " + Total);
            System.out.println("-----------------------------------------");
        }
    }
}
