package org.example.jakartadebugtest.daotest.oracle;

public abstract class DAOFactory {

  public static final int CLOUDSCAPE = 1;
  public static final int ORACLE = 2;
  public static final int SYBASE = 3;

  public abstract CustomerDAO getCustomerDAO();
  public abstract AccountDAO getAccountDAO();
  public abstract OrderDAO getOrderDAO();

  public static DAOFactory getDAOFactory(
      int whichFactory) {

      return switch (whichFactory) {
          case CLOUDSCAPE -> new CloudscapeDAOFactory();
          case ORACLE -> new OracleDAOFactory();
          case SYBASE -> new SybaseDAOFactory();
          default -> null;
      };
  }
}