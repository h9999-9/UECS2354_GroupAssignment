package my.edu.utar;

public class printOrder {
    private customer customerDetails;
    private String printType; 
    private String paperSize; 
    private String printingSide; 
    private int numberOfPages;
    private int numberOfCopies;
    private String bindingOption; 
    private boolean laminationOption;
    private boolean expressPrinting;

    public printOrder(customer cust, String type, String size, String side, int pages, int copies, String binding, boolean lamination, boolean express) {
        this.customerDetails = cust;
        this.printType = type;
        this.paperSize = size;
        this.printingSide = side;
        this.numberOfPages = pages;
        this.numberOfCopies = copies;
        this.bindingOption = binding;
        this.laminationOption = lamination;
        this.expressPrinting = express;
    }

    // Standard Getters for all fields
    public customer getCustomerDetails() { return customerDetails; }
    public String getPrintType() { return printType; }
    public String getPaperSize() { return paperSize; }
    public String getPrintingSide() { return printingSide; }
    public int getNumberOfPages() { return numberOfPages; }
    public int getNumberOfCopies() { return numberOfCopies; }
    public String getBindingOption() { return bindingOption; }
    public boolean isLaminationOption() { return laminationOption; }
    public boolean isExpressPrinting() { return expressPrinting; }
}