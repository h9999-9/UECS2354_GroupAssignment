package my.edu.utar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readCustomer {
    
    private String filePath = "customer.txt";

    public customer findCustomer(String targetID) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming format: ID,Name,Email,Phone,Type
                String[] details = line.split(",");
                if (details.length == 5 && details[0].trim().equalsIgnoreCase(targetID)) {
                    return new customer(details[0].trim(), details[1].trim(), 
                                        details[2].trim(), details[3].trim(), details[4].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
        return null; // Customer not found
    }
}