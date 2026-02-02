package org.example.jakartadebugtest.daotest.dowork.data;

import java.sql.SQLException;

public class PersistException extends Throwable {
    public PersistException(String s) {
        super(s);
    }

    public PersistException(SQLException e) {
        super(e);
    }
}
