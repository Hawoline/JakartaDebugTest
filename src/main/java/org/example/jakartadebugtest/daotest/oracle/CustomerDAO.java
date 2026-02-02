package org.example.jakartadebugtest.daotest.oracle;

import javax.sql.RowSet;
import java.util.Collection;

// Interface that all CustomerDAOs must support
public interface CustomerDAO {
    int insertCustomer();

    boolean deleteCustomer();

    Customer findCustomer();

    boolean updateCustomer();

    RowSet selectCustomersRS();

    Collection selectCustomersTO();
}