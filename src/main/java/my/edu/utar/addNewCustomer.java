package my.edu.utar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class addNewCustomer {
    
    private String filePath = "customer.txt";

    public boolean addCustomer(customer newCustomer) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String customerRecord = String.format("%s,%s,%s,%s,%s", 
                newCustomer.getCustomerID(),
                newCustomer.getCustomerName(),
                newCustomer.getEmail(),
                newCustomer.getPhoneNumber(),
                newCustomer.getCustomerType());
            
            bw.write(customerRecord);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to customer file: " + e.getMessage());
            return false;
        }
    }
}