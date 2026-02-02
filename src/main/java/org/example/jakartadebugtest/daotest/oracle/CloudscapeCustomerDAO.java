package org.example.jakartadebugtest.daotest.oracle;

import javax.sql.RowSet;
import java.util.Collection;
import java.util.List;

public class CloudscapeCustomerDAO implements CustomerDAO {
    @Override
    public int insertCustomer() {
        return 0;
    }

    @Override
    public boolean deleteCustomer() {
        return false;
    }

    @Override
    public Customer findCustomer() {
        return null;
    }

    @Override
    public boolean updateCustomer() {
        return false;
    }

    @Override
    public RowSet selectCustomersRS() {
        return null;
    }

    @Override
    public Collection selectCustomersTO() {
        return List.of();
    }
}
