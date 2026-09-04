package my.edu.utar;

public class calculatePrintingCharge {

    private printerAvailability printerCheck;
    private applyDiscount discountCalculator;

    public calculatePrintingCharge(printerAvailability printerCheck, applyDiscount discountCalculator) {
        this.printerCheck = printerCheck;
        this.discountCalculator = discountCalculator;
    }

    public double calculateBaseCharge(String paperSize, String printType, String printSide, int pages, int copies) {
        // Fix for Group 1: Throwing exceptions for invalid boundaries
        if (pages < 1 || pages > 500) throw new IllegalArgumentException("Pages must be between 1 and 500");
        if (copies < 1 || copies > 1000) throw new IllegalArgumentException("Copies must be between 1 and 1000");

        double rate = 0.0;

        if (paperSize.equalsIgnoreCase("A4")) {
            if (printType.equalsIgnoreCase("Black & White")) rate = printSide.equalsIgnoreCase("Single-sided") ? 0.20 : 0.18;
            else if (printType.equalsIgnoreCase("Colour")) rate = printSide.equalsIgnoreCase("Single-sided") ? 0.80 : 0.75;
        } else if (paperSize.equalsIgnoreCase("A3")) {
            if (printType.equalsIgnoreCase("Black & White")) rate = printSide.equalsIgnoreCase("Single-sided") ? 0.40 : 0.35;
            else if (printType.equalsIgnoreCase("Colour")) rate = printSide.equalsIgnoreCase("Single-sided") ? 1.50 : 1.40;
        } else if (paperSize.equalsIgnoreCase("A5")) {
            if (printType.equalsIgnoreCase("Black & White")) rate = printSide.equalsIgnoreCase("Single-sided") ? 0.15 : 0.13;
            else if (printType.equalsIgnoreCase("Colour")) rate = printSide.equalsIgnoreCase("Single-sided") ? 0.60 : 0.55;
        }

        // Fix for Group 1: Throwing exception for invalid paper/print type
        if (rate == 0.0) throw new IllegalArgumentException("Invalid print options selected.");

        return rate * pages * copies;
    }

    public double calculateOptionalServiceCharge(String binding, boolean lamination, boolean express, int totalPages) {
        double totalOptional = 0.0;

        // Fix for Group 2 & 1: Actually calculating the charges and throwing exceptions for invalid binding
        if (binding.equalsIgnoreCase("Staple")) {
            totalOptional += 2.00;
        } else if (binding.equalsIgnoreCase("Comb")) {
            totalOptional += 5.00;
        } else if (binding.equalsIgnoreCase("Spiral")) {
            totalOptional += 8.00;
        } else if (!binding.equalsIgnoreCase("none") && !binding.isEmpty()) {
            throw new IllegalArgumentException("Invalid binding selection.");
        }

        if (lamination) totalOptional += (1.50 * totalPages);
        if (express) totalOptional += 20.00;

        return totalOptional;
    }

    public double calculateTotalCharge(printOrder order, int previousOrders) {
        if (!printerCheck.isPrinterAvailable(order.getPaperSize(), order.getPrintType())) {
            throw new IllegalStateException("Selected printer is currently unavailable.");
        }

        int totalPages = order.getNumberOfPages() * order.getNumberOfCopies();
        
        double base = calculateBaseCharge(order.getPaperSize(), order.getPrintType(), order.getPrintingSide(), order.getNumberOfPages(), order.getNumberOfCopies());
        
        // Fix for Group 3: Passing the correct variable name for binding
        double optional = calculateOptionalServiceCharge(order.getBindingOption(), order.isLaminationOption(), order.isExpressPrinting(), totalPages);
        
        double subtotal = base + optional;
        double discount = discountCalculator.calculateDiscount(order.getCustomerDetails().getCustomerType(), subtotal, previousOrders);

        return subtotal - discount;
    }
}