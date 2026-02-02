package org.example.jakartadebugtest.daotest.oracle;

public class Customer implements java.io.Serializable {
    private int CustomerNumber;
    private String name;
    private String streetAddress;
    private String city;

    public int getCustomerNumber() {
        return CustomerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        CustomerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress() {

    }

    public void setEmail() {

    }
}
