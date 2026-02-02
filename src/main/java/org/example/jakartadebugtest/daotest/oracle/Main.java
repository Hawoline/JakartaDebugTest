package org.example.jakartadebugtest.daotest.oracle;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        DAOFactory cloudscapeFactory = DAOFactory.getDAOFactory(DAOFactory.CLOUDSCAPE);

        CustomerDAO custDAO = cloudscapeFactory.getCustomerDAO();

        int newCustNo = custDAO.insertCustomer();

        Customer cust = custDAO.findCustomer();

        cust.setAddress();
        cust.setEmail();
        custDAO.updateCustomer();

        custDAO.deleteCustomer();
        Customer criteria = new Customer();
        criteria.setCity("New York");
        Collection<Customer> customersList =
                custDAO.selectCustomersTO();
    }
}
