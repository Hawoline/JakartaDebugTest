package org.example.jakartadebugtest.daotest.dowork;

import org.example.jakartadebugtest.daotest.dowork.data.PersistException;
import org.example.jakartadebugtest.daotest.dowork.data.SqliteDaoFactory;
import org.example.jakartadebugtest.daotest.dowork.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SqliteDaoFactoryTest {
    private DaoFactory<Connection> daoFactory = new SqliteDaoFactory();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAll() throws SQLException {
        List<Group> groups;
        try (Connection connection = daoFactory.getContext()) {
            Dao<Group, Integer> groupDao = daoFactory.getGroupDao(connection);
            groups = groupDao.getAll();
        } catch (org.example.jakartadebugtest.daotest.dowork.data.PersistException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(groups);
        assertFalse(groups.isEmpty());
    }

    @Test
    void testCreate() throws PersistException {
    }
}