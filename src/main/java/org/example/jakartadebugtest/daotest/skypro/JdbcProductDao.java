package org.example.jakartadebugtest.daotest.skypro;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    @Override
    public Product findById(Long id) throws DaoException {
        String sql = "SELECT * FROM products WHERE id = ?";


        ResultSet resultSet = null;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToProduct(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding product by ID: " + id, e);
        } finally {
            DatabaseUtil.close(resultSet);
        }
    }

    @Override
    public List<Product> findAll() throws DaoException {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                products.add(mapResultSetToProduct(resultSet));
            }

            return products;
        } catch (SQLException e) {
            throw new DaoException("Error finding all products", e);
        }
    }

    @Override
    public List<Product> findByPriceRange(BigDecimal min, BigDecimal max) throws DaoException {
        String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ?";

        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setBigDecimal(1, min);
            preparedStatement.setBigDecimal(2, max);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(mapResultSetToProduct(resultSet));
            }

            return products;
        } catch (SQLException e) {
            throw new DaoException("Error finding products in price range", e);
        } finally {
            DatabaseUtil.close(resultSet);
        }
    }

    @Override
    public Long save(Product product) throws DaoException {
        String sql = "INSERT INTO products (name, description, price, stock) VALUES (?, ?, ?, ?)";

        ResultSet resultSet = null;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setInt(4, product.getStock());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DaoException("Creating product failed, no rows affected.");
            }

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                throw new DaoException("Creating product failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new DaoException("Error saving product", e);
        } finally {
            DatabaseUtil.close(resultSet);
        }
    }

    @Override
    public void update(Product product) throws DaoException {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock = ? WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setInt(4, product.getStock());
            preparedStatement.setLong(5, product.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DaoException("Updating product failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating product", e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DaoException("Deleting product failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DaoException("Error deleting product", e);
        }
    }

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setStock(resultSet.getInt("stock"));
        return product;
    }
}

