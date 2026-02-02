package org.example.jakartadebugtest.daotest.skypro;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService() {
        this.productDao = DaoFactory.createProductDao();
    }

    public Product getProductById(Long id) throws ServiceException {
        try {
            return productDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Error getting product by ID", e);
        }
    }

    public List<Product> getDiscountedProducts(BigDecimal maxPrice) throws ServiceException {
        try {
            return productDao.findByPriceRange(BigDecimal.ZERO, maxPrice);
        } catch (DaoException e) {
            throw new ServiceException("Error getting discounted products", e);
        }
    }

    public Long createProduct(Product product) throws ServiceException {
        try {
            return productDao.save(product);
        } catch (DaoException e) {
            throw new ServiceException("Error creating product", e);
        }
    }
}

