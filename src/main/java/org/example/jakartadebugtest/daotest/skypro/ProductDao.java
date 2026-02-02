package org.example.jakartadebugtest.daotest.skypro;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {
    Product findById(Long id) throws DaoException;

    List<Product> findAll() throws DaoException;

    List<Product> findByPriceRange(BigDecimal min, BigDecimal max) throws DaoException;

    Long save(Product product) throws DaoException;

    void update(Product product) throws DaoException;

    void delete(Long id) throws DaoException;
}

