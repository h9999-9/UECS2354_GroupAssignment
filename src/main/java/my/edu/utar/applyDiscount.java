package my.edu.utar;

public class applyDiscount {
    public double calculateDiscount(String customerType, double subtotal, int previousOrders) {
        double currentTotal = subtotal;

        if (customerType.equalsIgnoreCase("Student")) {
            currentTotal *= 0.90; // 10% off
        } else if (customerType.equalsIgnoreCase("Corporate")) {
            currentTotal *= 0.85; // 15% off
        }

        if (subtotal > 300.00) {
            currentTotal *= 0.95; // Additional 5% off
        }

        if (previousOrders > 20) {
            currentTotal *= 0.95; // Additional 5% off
        }

        return subtotal - currentTotal; 
    }
}