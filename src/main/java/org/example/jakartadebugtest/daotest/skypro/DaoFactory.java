package org.example.jakartadebugtest.daotest.skypro;

public class DaoFactory {
public static ProductDao createProductDao() {
return new JdbcProductDao();
}
}

