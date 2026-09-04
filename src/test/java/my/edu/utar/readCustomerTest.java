package my.edu.utar;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class readCustomerTest {

    private readCustomer reader;

    @Before
    public void setUp() {
        reader = new readCustomer();
    }

    @Test
    public void testFindCustomer_ValidID() {
        // Ensure "customer.txt" exists with C001 data for this to pass
        customer result = reader.findCustomer("C001");
        
        assertNotNull("Customer should be found", result);
        assertEquals("C001", result.getCustomerID());
        assertEquals("Student", result.getCustomerType());
    }

    @Test
    public void testFindCustomer_InvalidID() {
        customer result = reader.findCustomer("INVALID_ID_999");
        assertNull("Customer should not be found", result);
    }
}