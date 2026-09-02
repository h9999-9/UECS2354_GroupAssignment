package my.edu.utar;

public class printOrder {
    private customer customerDetails;
    private String printType; // "Black & White" or "Colour"
    private String paperSize; // "A3", "A4", "A5"
    private String printingSide; // "Single-sided" or "Double-sided"
    private int numberOfPages;
    private int numberOfCopies;
    private String bindingOption; // "Staple", "Comb", "Spiral", or "None"
    private boolean laminationOption;
    private boolean expressPrinting;

    // Charge tracking
    private double baseCharge;
    private double additionalServiceCharges;
    private double discountAmount;
    private double totalCharge;

    private String orderStatus;
    private String paymentStatus;

    // TODO: Add constructor, getters, and setters to record and retrieve details
    public void createOrder(customer cust, String type, String size, String side, int pages, int copies, String binding, boolean lamination, boolean express) {
        // Implementation here
    }
}
