package my.edu.utar;

public class calculatePrintingCharge {
    private printerAvailability printerCheck;
    private applyDiscount discountCalculator;

    public calculatePrintingCharge(printerAvailability printerCheck, applyDiscount discountCalculator) {
        this.printerCheck = printerCheck;
        this.discountCalculator = discountCalculator;
    }

    public double calculateTotalCharge(printOrder order) {
        // 1. Check printer availability (return early if false)
        // 2. Call calculateBaseCharge()
        // 3. Call calculateOptionalServiceCharge()
        // 4. Get subtotal and call discountCalculator.calculateDiscount()
        // 5. Apply discount and return final total (rounded to 2 decimals)
        return 0.0;
    }

    public double calculateBaseCharge(String paperSize, String printType, String printingSide, int pages, int copies) {
        // TODO: Implement Table 2 logic
        return 0.0; 
    }

    public double calculateOptionalServiceCharge(String binding, boolean lamination, boolean express, int totalPages) {
        // TODO: Implement Table 3 logic
        return 0.0;
    }
}
