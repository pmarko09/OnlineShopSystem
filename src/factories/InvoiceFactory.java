package factories;

import entity.Invoice;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the list of invoices.
 *
 * @author JB
 */

public class InvoiceFactory {
    private List<Invoice> invoices;

    public InvoiceFactory() {
        this.invoices = new ArrayList<>();
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}
