package service;

import entity.Cart;
import entity.Invoice;
import factories.InvoiceFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * This class represents methods related to invoices.
 *
 * @author JB
 */

public class InvoiceService {

    private InvoiceFactory invoiceFactory = new InvoiceFactory();
    private Cart cart = new Cart();

    /**
     * This method generates the invoice.
     * Checks if invoice is not null and if the Customer is not null.
     * Checks if there is any product added to the invoice.
     * If there are, generates the invoice with order and customer details. If not then does not proceed with the invoice generation.
     *
     * @param invoice Invoice object on which details invoice is to be generated.
     * @throws IllegalArgumentException If either the invoice or customer in the order is null.
     */

    public void generateInvoice(Invoice invoice) {
        try {
            if (invoice == null || invoice.getCustomer() == null) {
                throw new IllegalArgumentException("The order is empty or the customer data are not added. Please check and try again.");
            }
            printInvoice(invoice);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method prints invoice details (customer details, products details, price).
     * Checks if there is at least one product added on the invoice.
     *
     * @param invoice Invoice object which details are printed.
     */

    private void printInvoice(Invoice invoice) {
        try {
            System.out.println("\nGenerating invoice for order ID:" + invoice.getId() + "...");
            Thread.sleep(5000);
            System.out.println("Invoice generated successfully!" + "\n");
            System.out.println("Customer: " + invoice.getCustomer().getFirstName() + " " + invoice.getCustomer().getLastName());
            System.out.println("Email: " + invoice.getCustomer().getEmail());
            System.out.println("Address: " + invoice.getCustomer().getCity() + ", " + invoice.getCustomer().getStreet() + "\n");
            System.out.println("Products:");
            if (!invoice.getProducts().isEmpty() || invoice.getProducts() == null) {
                invoice.getProducts().forEach(product -> System.out.println(product.getName() + " (" + product.getPrice() + "zł)," + " quantity: " + product.getProductQuantity()));
                BigDecimal orderTotalPriceRounded = new BigDecimal(invoice.getPrice()).setScale(2, RoundingMode.HALF_UP);
                System.out.println("Total price: " + orderTotalPriceRounded + "zł." + "\n");
                applyDiscount(invoice);
                invoiceFactory.addInvoice(invoice);
            } else {
                System.out.println("There are no products in the basket. Invoice will not be generated.");
            }
        } catch (Exception e) {
            System.out.println("Error during generating the invoice: " + e.getMessage());
        }
    }

    /**
     * This method applies discounts.
     * If the price on the invoice exceeds 10_000 it deducts 5% of the price.
     * If the price on the invoice exceeds 50_000 it deducts 10% of the price.
     *
     * @param invoice Represents invoice on which price discount is applied.
     */

    private void applyDiscount(Invoice invoice) {
        if (invoice.getPrice() >= 10_000 && invoice.getPrice() < 50_000) {
            double orderTotalPriceAfterSmallDiscount = invoice.getPrice() * 0.95;
            BigDecimal orderTotalPriceAfterSmallDiscountRound = new BigDecimal(orderTotalPriceAfterSmallDiscount).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Final price after 5% discount (5% discount applies for orders for more than 10.000zł): " + orderTotalPriceAfterSmallDiscountRound + "zł.\n");
        }
        if (invoice.getPrice() > 50_000) {
            double orderTotalPriceAfterBigDiscount = invoice.getPrice() * 0.9;
            BigDecimal orderTotalPriceAfterBigDiscountRound = new BigDecimal(orderTotalPriceAfterBigDiscount).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Final price after 10% discount (10% discount applies for orders for more than 50.000zł): " + orderTotalPriceAfterBigDiscountRound + "zł.\n");
        }
    }

    /**
     * Method saves invoice to provided file.
     *
     * @param fileName Represents file to which invoice is saved.
     */

    public void saveInvoicesToFile(String fileName) {
        List<Invoice> invoices = invoiceFactory.getInvoices();
        try (FileWriter fw = new FileWriter(fileName, true);
             PrintWriter writer = new PrintWriter(fw)) {
            for (Invoice invoice : invoices) {
                writer.println(invoice);
            }
//            System.out.println("Invoices saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
