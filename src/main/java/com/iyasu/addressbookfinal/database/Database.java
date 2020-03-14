package com.iyasu.addressbookfinal.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database implements AutoCloseable {
  private static final Logger logger = Logger.getLogger(Database.class.getName());

  private final Connection conn;

  public Database(Connection conn) {
    this.conn = conn;
  }

  @Override
  public void close() {
    try {
      conn.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Create a new table in the database
   *
   */
  public void createNewTable(String table, List<String> fields) {
    // SQL statement for creating a new table
    String sql = "CREATE TABLE IF NOT EXISTS " + table + " ( id integer PRIMARY KEY";
    for (String field : fields) {
      sql += ", " + field + " TEXT";
    }
    sql += ");";

    try (Statement stmt = conn.createStatement()) {
      // create a new table
      stmt.execute(sql);
    } catch (SQLException e) {
      logger.log(Level.WARNING, "Failed to create table.", e);
      throw new RuntimeException("Unable to create table: " + sql, e);
    }
  }

  /**
   * Insert a new row into the table
   *
   * @param name
   * @param capacity
   */
  public int insert(String table, List<Pair<String, String>> values) {
    int id = nextId(table);
    String sql = "INSERT INTO " + table + " ( id";
    String placeholders = "?";
    for (Pair<String, String> pair : values) {
      sql += ", " + pair.first;
      placeholders += ", ?";
    }
    sql += ") VALUES(" + placeholders + ")";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      for (int i = 0; i < values.size(); ++i) {
        pstmt.setString(i + 2,  values.get(i).second);
      }
      pstmt.executeUpdate();
    } catch (SQLException e) {
      logger.warning(String.format("Error storing: %s [%s]: %s", values, sql, e.getMessage()));
    }
    return id;
  }
  
  public List<String> get(String table, int id) {
    String sql = "SELECT * FROM " + table + " WHERE id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      ResultSet rs  = pstmt.executeQuery();
      if (!rs.next()) { // Nothing to read
        return null;
      }
      
      int count = rs.getMetaData().getColumnCount();
      List<String> data = new ArrayList<>();
      for (int col = 1; col <= count; ++col) {
        data.add(rs.getString(col));
      }
      return data;
    } catch (SQLException e) {
      logger.warning(String.format("Error looking up: %s, %d: %s%n", table, id, e.getMessage()));
    }
    return null;
  }
  
  public List<List<String>> getAll(String table) {
    String sql = "SELECT * FROM " + table;
    List<List<String>> data = new ArrayList<>();
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      ResultSet rs  = pstmt.executeQuery();
      int count = rs.getMetaData().getColumnCount();
      while (rs.next()) {
        List<String> row = new ArrayList<>();
        for (int col = 1; col <= count; ++col) {
          row.add(rs.getString(col));
        }
        data.add(row);
      }
    } catch (SQLException e) {
      logger.warning(String.format("Error looking up: %s: %s%n", table, e.getMessage()));
    }
    return data;
  }

  /**
   * Insert a new row into the table
   *
   * @param name
   * @param capacity
   */
  public void update(String table, int id, List<Pair<String, String>> values) {
    String sql = "UPDATE " + table + " SET ";
    String comma = "";
    for (Pair<String, String> pair : values) {
      sql += comma + pair.first + " = ?";
      comma = ", ";
    }
    sql += "WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      for (int i = 0; i < values.size(); ++i) {
        pstmt.setString(i + 1,  values.get(i).second);
      }
      pstmt.setInt(values.size() + 1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      logger.warning("Error updating: " + values + ": " + e.getMessage());
    }
  }

  /**
   * Insert a new row into the table
   *
   * @param name
   * @param capacity
   */
  public void remove(String table, int id) {
    String sql = "DELETE FROM " + table + " WHERE id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      logger.warning("Error deleting: " + id + ": " + e.getMessage());
    }
  }
  
  private int nextId(String table) {
    try (PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) FROM " + table)) {
      ResultSet rs  = pstmt.executeQuery();
      if (rs.next()) {
        return 1 + rs.getInt(1);
      }
    } catch (SQLException e) {
      logger.warning(String.format("Error looking up: %s: %s%n", table, e.getMessage()));
    }
    return 1;
  }

}
