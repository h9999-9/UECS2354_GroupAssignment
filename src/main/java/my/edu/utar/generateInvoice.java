package my.edu.utar;

public class generateInvoice {
    
    public void displayInvoice(printOrder order, double subtotal, double totalDiscount, double finalTotal) {
        System.out.println("========================================");
        System.out.println("          PRINTMASTER INVOICE           ");
        System.out.println("========================================");
        System.out.println("Customer ID    : " + order.getCustomerDetails().getCustomerID());
        System.out.println("Customer Name  : " + order.getCustomerDetails().getCustomerName());
        System.out.println("Customer Type  : " + order.getCustomerDetails().getCustomerType());
        System.out.println("----------------------------------------");
        System.out.println("Order Details:");
        System.out.println("Paper Size     : " + order.getPaperSize());
        System.out.println("Print Type     : " + order.getPrintType());
        System.out.println("Pages & Copies : " + order.getNumberOfPages() + " pages x " + order.getNumberOfCopies() + " copies");
        System.out.println("Binding        : " + order.getBindingOption());
        System.out.println("Lamination     : " + (order.isLaminationOption() ? "Yes" : "No"));
        System.out.println("Express        : " + (order.isExpressPrinting() ? "Yes" : "No"));
        System.out.println("----------------------------------------");
        System.out.printf("Subtotal       : RM %.2f%n", subtotal);
        System.out.printf("Discount       : -RM %.2f%n", totalDiscount);
        System.out.println("----------------------------------------");
        System.out.printf("FINAL TOTAL    : RM %.2f%n", finalTotal);
        System.out.println("========================================");
    }
}