package service;

import entity.Invoice;
import entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents the order's processor. It contains methods helping order being correctly processed.
 *
 * @author JB
 */

public class OrderProcessor {
    InvoiceService invoiceService = new InvoiceService();
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    /**
     * This method adds order to the order's list, uses generateInvoice method from InvoiceService and prints info about order time.
     *
     * @param order Order which is being processed.
     */
    public void processOrder(Order order) {
        orders.add(order);
        Invoice invoice = new Invoice(order.getOrderId(), order.getCustomer(), order.getProductList(), order.getOrderTotalPrice());
        invoiceService.generateInvoice(invoice);
        if (!orders.isEmpty()) {
            System.out.println("Order time: " + order.getOrderTimeFormatted());
            System.out.println("Delivery planned for: " + order.deliveryOrderTime() + "\n");
        }
        invoiceService.saveInvoicesToFile("invoice_OnlineShopSystem.txt");
        System.out.println();
    }
}
