package my.edu.utar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class applyDiscountTest {

    private applyDiscount discountCalculator;

    @Before
    public void setUp() {
        discountCalculator = new applyDiscount();
    }

    @Test
    @Parameters({
        // Existing Customers
        "Student, 150.00, 10, 15.00",    // 10% base
        "Student, 350.00, 10, 50.75",    // 10% base -> 5% volume (RM350 * 0.9 * 0.95 = 299.25; discount = 50.75)
        "Student, 350.00, 25, 65.71",    // 10% base -> 5% volume -> 5% loyalty (RM350 * 0.9 * 0.95 * 0.95 = 284.29; disc = 65.71)
        
        "Corporate, 150.00, 10, 22.50",  // 15% base
        "Corporate, 350.00, 10, 67.38",  // 15% base -> 5% volume (RM350 * 0.85 * 0.95 = 282.625; disc = 67.38)
        "Corporate, 350.00, 25, 81.50",  // 15% base -> 5% vol -> 5% loyalty (RM350 * 0.85 * 0.95 * 0.95 = 268.50; disc = 81.50)
        
        "Regular, 150.00, 10, 0.00",     // 0% discount
        "Regular, 350.00, 10, 17.50",    // 5% volume
        "Regular, 350.00, 25, 34.13",    // 5% volume -> 5% loyalty (RM350 * 0.95 * 0.95 = 315.875; disc = 34.13)
        
        // New Customers (0 previous orders)
        "Student, 350.00, 0, 50.75",     // 10% base -> 5% volume
        "Corporate, 350.00, 0, 67.38",   // 15% base -> 5% volume
        "Regular, 350.00, 0, 17.50"      // 5% volume
    })
    public void testCalculateDiscount_ValidInputs(String customerType, double subtotal, int previousOrders, double expectedDiscount) {
        double actualDiscount = discountCalculator.calculateDiscount(customerType, subtotal, previousOrders);
        assertEquals(expectedDiscount, actualDiscount, 0.01);
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateDiscount_InvalidCustomerNull() {
        // Test null customer type (Invalid Partition)
        discountCalculator.calculateDiscount(null, 350.00, 25);
    }
}