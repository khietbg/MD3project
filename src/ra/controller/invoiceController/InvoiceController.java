package ra.controller.invoiceController;

import ra.model.entity.Invoice;
import ra.model.service.invoice.IInvoiceService;
import ra.model.service.invoice.InvoiceServiceIMPL;

import java.util.List;

public class InvoiceController {
    private static IInvoiceService iInvoiceService = new InvoiceServiceIMPL();
    public Invoice findById(int id){
        return iInvoiceService.findById(id);
    }
    public void save(Invoice invoice){
        iInvoiceService.save(invoice);
    }
    public void deleteInvoice(int id){
        iInvoiceService.delete(id);
    }
    public void saveInvoiceHistory(Invoice invoice){
        iInvoiceService.saveHistory(invoice);
    }
    public Invoice showInvoiceHistory(int id){
        return iInvoiceService.findInvoiceHistory(id);
    }
    public List<Invoice> showAllInvoiceHistory(){
        return iInvoiceService.showAllInvoiceHistory();
    }
}
