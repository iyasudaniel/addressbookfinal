package com.iyasu.addressbookfinal.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySql {
  private static final Logger logger = Logger.getLogger(MySql.class.getName());
  
  private final String connection;
  
  /**
   * @param connection The full path to the file holding the database
   */
  public MySql(String connection) {
    this.connection = connection;
  }

  /**
   * Connect to the given database
   * @return the Connection object
   */
  public Connection connect() {
    // MySQL connection string
    String url = "jdbc:mysql:" + connection;
    try {
      Connection conn = DriverManager.getConnection(url);
      DatabaseMetaData meta = conn.getMetaData();
      logger.info("The driver name is " + meta.getDriverName());
      logger.info("Database: " + url);
      return conn;
    } catch (SQLException e) {
      logger.log(Level.WARNING, "Failed to connect to sqlite.", e);
      throw new RuntimeException("Unable to create connection.", e);
    }
  }


}
