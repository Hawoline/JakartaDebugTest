package org.example.jakartadebugtest.daotest.dowork.domain;


import java.sql.Connection;

public interface DaoFactory {

    Connection getConnection();

    Dao<Student, Integer> getStudentDao(Connection connection);

    Dao<Group, Integer> getGroupDao(Connection connection);
}