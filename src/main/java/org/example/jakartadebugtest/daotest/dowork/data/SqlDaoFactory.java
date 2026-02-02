package org.example.jakartadebugtest.daotest.dowork.data;

import org.example.jakartadebugtest.daotest.dowork.domain.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDaoFactory implements DaoFactory<Connection> {
    private final String url = "jdbc:sqlite:C:" + File.separator +
            "Users" + File.separator +
            "Hawoline" + File.separator +
            "IdeaProjects" + File.separator +
            "JakartaDebugTest" + File.separator +
            "dowork_dao.sqlite";
    private String driver = "org.sqlite.JDBC";

    public SqlDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getContext() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Db not found: " + e);
        }
    }

    @Override
    public Dao<Student, Integer> getStudentDao(Connection context) {
        return null;
    }

    @Override
    public Dao<Group, Integer> getGroupDao(Connection context) {
        return new SqlGroupDao(context);
    }

    @Override
    public Dao getDao(Connection connection) {
        return null;
    }
}
