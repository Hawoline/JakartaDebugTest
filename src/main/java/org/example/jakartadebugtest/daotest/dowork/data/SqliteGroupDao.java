package org.example.jakartadebugtest.daotest.dowork.data;

import org.example.jakartadebugtest.daotest.dowork.domain.Group;
import org.example.jakartadebugtest.daotest.dowork.domain.Identified;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteGroupDao extends JdbcDao<Group, Integer> {
    protected SqliteGroupDao(Connection connection) {
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
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, Group group) throws PersistException {
        try {
            preparedStatement.setInt(1, group.getNumber());
            preparedStatement.setString(2, group.getDepartment());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO 'Group' (number, department) \n" +
                "VALUES (?, ?);";
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, Group group) throws PersistException {
        try {
            preparedStatement.setInt(1, group.getNumber());
            preparedStatement.setString(2, group.getDepartment());
            preparedStatement.setInt(3, group.getId());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE 'Group' SET number= ?, department = ? WHERE id= ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM 'Group' WHERE id= ?;";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM 'Group' ";
    }

    @Override
    public Group create() throws PersistException {
        Group g = new Group();
        return persist(g);
    }
}
