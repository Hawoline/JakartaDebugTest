package org.example.jakartadebugtest.daotest.dowork.data;

import org.example.jakartadebugtest.daotest.dowork.domain.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlGroupDao extends JdbcDao<Group, Integer> {
    protected SqlGroupDao(Connection connection) {
        super(connection);
    }

    private class PersistGroup extends Group {

        public PersistGroup(int id, String department, int number) {
            super(id, department, number);
        }

        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public List<Group> parseResultSet(ResultSet resultSet) {
        List<Group> groups = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Group group = new PersistGroup(
                        resultSet.getInt("id"),
                        resultSet.getString("department"),
                        resultSet.getInt("number")
                );
                groups.add(group);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, Group group) {
    }

    @Override
    protected String getCreateQuery() {
        return "";
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, Group group) {

    }

    @Override
    protected String getUpdateQuery() {
        return "";
    }

    @Override
    protected String getDeleteQuery() {
        return "";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM \"Group\" ";
    }

    @Override
    public Group create() {
        return null;
    }
}
