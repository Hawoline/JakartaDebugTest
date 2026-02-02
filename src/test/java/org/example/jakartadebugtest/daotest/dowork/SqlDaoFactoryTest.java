package org.example.jakartadebugtest.daotest.dowork;

import org.example.jakartadebugtest.daotest.dowork.data.SqlDaoFactory;
import org.example.jakartadebugtest.daotest.dowork.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SqlDaoFactoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAll() throws SQLException {
        DaoFactory daoFactory = new SqlDaoFactory();
        List<Group> groups;
        try (Connection connection = daoFactory.getConnection()) {
            Dao<Group, Integer> groupDao = daoFactory.getGroupDao(connection);
            groups = groupDao.getAll();
        } catch (org.example.jakartadebugtest.daotest.dowork.data.PersistException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(groups);
        assertFalse(groups.isEmpty());
    }
}