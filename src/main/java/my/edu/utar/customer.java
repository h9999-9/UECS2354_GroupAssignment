package my.edu.utar;

public class customer {
    private String customerID;
    private String name;
    private String email;
    private String phoneNumber;
    private String customerType; // e.g., "Student", "Corporate", "Existing", "Regular"

    public customer(String customerID, String name, String email, String phoneNumber, String customerType) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
    }

    public String getCustomerID() { return customerID; }
    public String getCustomerName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getCustomerType() { return customerType; }
}
