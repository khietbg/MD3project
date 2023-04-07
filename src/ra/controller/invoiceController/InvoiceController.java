package ra.controller.invoiceController;

import ra.model.entity.Invoice;
import ra.model.service.invoice.IInvoiceService;
import ra.model.service.invoice.InvoiceServiceIMPL;

public class InvoiceController {
    private static IInvoiceService iInvoiceService = new InvoiceServiceIMPL();
    public Invoice findById(int id){
        return iInvoiceService.findById(id);
    }
    public void save(Invoice invoice){
        iInvoiceService.save(invoice);
    }
}
