package my.edu.utar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class calculatePrintingChargeTest {

    private calculatePrintingCharge calculator;
    private applyDiscount mockDiscount;
    private printerAvailability mockPrinter;

    @Before
    public void setUp() {
        // Initialize the mocks
        mockPrinter = mock(printerAvailability.class);
        mockDiscount = mock(applyDiscount.class);
        
        // Inject mocks into the class under test
        calculator = new calculatePrintingCharge(mockPrinter, mockDiscount);
    }

    // ==========================================
    // 1. BASE CHARGE TESTS (From Partition Table 3)
    // ==========================================
    @Test
    @Parameters({
        "A4, Black & White, Single-sided, 10, 2, 4.00",    // Valid EP
        "A3, Colour, Double-sided, 5, 10, 70.00",          // Valid EP
        "A5, Black & White, Double-sided, 50, 5, 32.50",   // Valid EP
        "A4, Colour, Double-sided, 500, 1000, 375000.00"   // Valid BVA Maximum
    })
    public void testCalculateBaseCharge_ValidInputs(String size, String type, String side, int pages, int copies, double expected) {
        double actual = calculator.calculateBaseCharge(size, type, side, pages, copies);
        assertEquals(expected, actual, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters({
        "A4, Colour, Single-sided, 0, 1",        // Invalid Low BVA Pages
        "A3, Black & White, Single-sided, 1, 1001", // Invalid High BVA Copies
        "A6, Black & White, Single-sided, 10, 1",   // Invalid EP Paper Size
        "A4, Blue, Single-sided, 10, 1"             // Invalid EP Print Type
    })
    public void testCalculateBaseCharge_InvalidInputs(String size, String type, String side, int pages, int copies) {
        calculator.calculateBaseCharge(size, type, side, pages, copies);
    }

    // ==========================================
    // 2. OPTIONAL SERVICE TESTS (From Partition Table 5 & 6)
    // ==========================================
    @Test
    @Parameters({
        "none, false, false, 20, 0.00",         // No options
        "Staple, false, false, 20, 2.00",       // Single option
        "Comb, true, true, 50, 100.00",         // Multiple options
        "Spiral, true, false, 500000, 750008.00" // BVA Lamination Max (500*1000)
    })
    public void testCalculateOptionalCharge_ValidInputs(String binding, boolean lamination, boolean express, int totalPages, double expected) {
        double actual = calculator.calculateOptionalServiceCharge(binding, lamination, express, totalPages);
        assertEquals(expected, actual, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateOptionalCharge_InvalidBinding() {
        // Tests the exception when an invalid string is passed
        calculator.calculateOptionalServiceCharge("Hardcover", false, false, 10);
    }

    // ==========================================
    // 3. INTEGRATION / TOTAL CHARGE TESTS
    // ==========================================
    @Test
    public void testCalculateTotalCharge_PrinterAvailable_Success() {
        // Arrange
        customer dummyCustomer = new customer("C001", "John", "john@email.com", "012-3456789", "Student");
        printOrder dummyOrder = new printOrder(dummyCustomer, "Colour", "A3", "Double-sided", 50, 2, "Spiral", true, false);
        
        // Mock the printer to say YES
        when(mockPrinter.isPrinterAvailable("A3", "Colour")).thenReturn(true);
        
        // Mock the discount calculator to return a specific amount (RM 29.80 discount)
        // Note: Subtotal of this order is RM 298.00
        when(mockDiscount.calculateDiscount(eq("Student"), anyDouble(), eq(5))).thenReturn(29.80);

        // Act
        double finalCharge = calculator.calculateTotalCharge(dummyOrder, 5);

        // Assert
        assertEquals(268.20, finalCharge, 0.01); // 298.00 - 29.80
        
        // Verify interactions
        verify(mockPrinter, times(1)).isPrinterAvailable("A3", "Colour");
        verify(mockDiscount, times(1)).calculateDiscount("Student", 298.00, 5);
    }

    @Test(expected = IllegalStateException.class)
    public void testCalculateTotalCharge_PrinterUnavailable_ThrowsException() {
        // Arrange
        customer dummyCustomer = new customer("C002", "Jane", "jane@email.com", "012-3456789", "Regular");
        printOrder dummyOrder = new printOrder(dummyCustomer, "Black & White", "A4", "Single-sided", 10, 1, "none", false, false);
        
        // Mock the printer to say NO
        when(mockPrinter.isPrinterAvailable("A4", "Black & White")).thenReturn(false);

        // Act
        calculator.calculateTotalCharge(dummyOrder, 0);
        
        // (Execution stops here due to Exception, so we don't need Assert)
    }
}