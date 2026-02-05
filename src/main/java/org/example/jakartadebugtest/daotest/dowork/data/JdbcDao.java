package org.example.jakartadebugtest.daotest.dowork.data;

import org.example.jakartadebugtest.daotest.dowork.domain.Dao;
import org.example.jakartadebugtest.daotest.dowork.domain.Identified;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class JdbcDao<T extends Identified<PK>, PK extends Integer> implements Dao<T, PK> {
    private Connection connection;

    protected JdbcDao(Connection connection) {
        this.connection = connection;
    }

    public abstract String getSelectQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet);

    @Override
    public T persist(T object) throws PersistException {
        if (object.getId() == null) {
            throw new PersistException("object already exists");
        }
        T persistInstance;
        String createQuery = getCreateQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            prepareStatementForInsert(preparedStatement, object);
            int count = preparedStatement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more than one record: " + count);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String selectQuery = getSelectQuery() + " where id = last_insert_id();";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> list = parseResultSet(resultSet);
            if (list == null || list.size() != 1) {
                throw new PersistException("Exception on findByPK new persist data.");
            }
            persistInstance = list.getFirst();
        } catch (SQLException e) {
            throw new PersistException(e);
        }

        return persistInstance;
    }

    protected abstract void prepareStatementForInsert(PreparedStatement preparedStatement, T t) throws PersistException;

    protected abstract String getCreateQuery();

    @Override
    public T getByPK(Integer id) throws PersistException {
        List<T> list;
        String query = getSelectQuery();
        query += " where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                list = parseResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }

        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }

        return list.getFirst();
    }

    @Override
    public void update(T object) throws PersistException {
        String query = getUpdateQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            prepareStatementForUpdate(preparedStatement, object);
            int count = preparedStatement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more than one record: " + count);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    protected abstract void prepareStatementForUpdate(PreparedStatement preparedStatement, T t) throws PersistException;

    protected abstract String getUpdateQuery();

    @Override
    public void delete(T object) throws PersistException {
        String query = getDeleteQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, object.getId());
            int count =  preparedStatement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more than one record: " + count);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }


    protected abstract String getDeleteQuery();

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String query = getSelectQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            list = parseResultSet(resultSet);
            return list;
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
