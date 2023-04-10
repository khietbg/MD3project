package ra.model.service.invoice;

import ra.config.Config;
import ra.model.entity.Cart;
import ra.model.entity.Invoice;
import ra.model.entity.User;
import ra.model.service.cart.CartServiceIMPL;
import ra.model.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class InvoiceServiceIMPL implements IInvoiceService{
    private static List<Invoice> invoiceList = new Config<Invoice>().readFromFile(Config.PATH_INVOICE);
    private static List<Invoice> invoiceHistory = new Config<Invoice>().readFromFile(Config.PATH_INVOICE_HISTORY);
    User userlogin = new UserService().getCurrentLogin();
    Cart cartInvoice = new CartServiceIMPL().findById(userlogin.getUserId());
    @Override
    public List<Invoice> findAll() {
        return invoiceList;
    }

    @Override
    public void save(Invoice invoice) {
        invoiceList.add(invoice);
        new Config<Invoice>().writeToFile(Config.PATH_INVOICE,invoiceList);

    }

    @Override
    public Invoice findById(int id) {
        for (Invoice invoice:invoiceList) {
           if (invoice.getInvoiceUser().getUserId()==id){
               return invoice;
           }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (Invoice invoice:invoiceList) {
         if ( invoice.getInvoiceUser().getUserId()==id){
             invoiceList.remove(invoice);
             break;
         }
        }
        new Config<Invoice>().writeToFile(Config.PATH_INVOICE,invoiceList);
    }

    @Override
    public List<Invoice> searchByName(String searchName){
        return null;
    }
    public  void saveHistory(Invoice invoice){
        invoiceHistory.add(invoice);
        new Config<Invoice>().writeToFile(Config.PATH_INVOICE_HISTORY,invoiceHistory);
    }
    public Invoice findInvoiceHistory(int id){
        List<Invoice> invoices = new Config<Invoice>().readFromFile(Config.PATH_INVOICE_HISTORY);
        for (Invoice inv:invoices) {
            if (inv.getInvoiceUser().getUserId()==id){
               return inv;
            }
        }
        return null;
    }

    @Override
    public List<Invoice> showAllInvoiceHistory() {
        List<Invoice> invoices = new Config<Invoice>().readFromFile(Config.PATH_INVOICE_HISTORY);
        return invoices;
    }

}
