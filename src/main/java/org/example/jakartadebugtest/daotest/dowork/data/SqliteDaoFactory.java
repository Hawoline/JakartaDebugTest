package org.example.jakartadebugtest.daotest.dowork.data;

import org.example.jakartadebugtest.daotest.dowork.domain.*;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SqliteDaoFactory implements DaoFactory<Connection> {
    private Map<Class<? extends Identified<? extends Serializable>>, DaoCreator<Connection>> creators = new HashMap<>();
    private final String url = "jdbc:sqlite:C:" + File.separator +
            "Users" + File.separator +
            "Hawoline" + File.separator +
            "IdeaProjects" + File.separator +
            "JakartaDebugTest" + File.separator +
            "dowork_dao.sqlite";
    private String driver = "org.sqlite.JDBC";

    public SqliteDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        creators.put(Group.class, new DaoCreator<Connection>() {
            @Override
            public Dao create(Connection connection) {
                return new SqliteGroupDao(connection);
            }
        });
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
        return new SqliteGroupDao(context);
    }

    @Override
    public Dao<Identified<Serializable>, Serializable> getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator<Connection> creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found");
        }
        return creator.create(connection);
    }


}
