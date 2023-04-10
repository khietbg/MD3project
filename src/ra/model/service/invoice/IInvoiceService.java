package ra.model.service.invoice;

import ra.model.entity.Invoice;
import ra.model.service.IGenericService;

import java.util.List;

public interface IInvoiceService extends IGenericService<Invoice> {
    void saveHistory(Invoice invoice);
    Invoice findInvoiceHistory(int id);
    List<Invoice> showAllInvoiceHistory();
}
