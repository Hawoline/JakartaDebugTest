package org.example.jakartadebugtest.daotest.skypro;

import java.io.File;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class HikariDataSource {
    private HikariConfig hikariConfig;
    private final String driver = "org.sqlite.JDBC";

    private final String url = "jdbc:sqlite:C:" + File.separator +
            "Users" + File.separator +
            "Hawoline" + File.separator +
            "IdeaProjects" + File.separator +
            "JakartaDebugTest" + File.separator +
            "dowork_dao.sqlite";
    public HikariDataSource(HikariConfig hikariConfig) {
        this.hikariConfig = hikariConfig;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Db not found: " + e);
        }
    }
}
