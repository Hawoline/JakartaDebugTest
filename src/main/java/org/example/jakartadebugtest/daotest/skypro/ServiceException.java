package org.example.jakartadebugtest.daotest.skypro;

public class ServiceException extends Throwable {
    public ServiceException(String errorGettingProductById, DaoException e) {
    }
}
