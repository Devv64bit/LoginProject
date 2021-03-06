package com.javaguides.javaswing.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

  public static void main(String[] args) throws ClassNotFoundException {
	// load and register JDBC driver for MySQL
	  Class.forName("com.mysql.jdbc.Driver"); 
    Connection dbConnection = null;

    try {
      String url = "jdbc:mysql://localhost:3306/test";
      Properties info = new Properties();
      info.put("user", "root");
      info.put("password", "test");

      dbConnection = DriverManager.getConnection(url, info);

      if (dbConnection != null) {
        System.out.println("Successfully connected to MySQL database test");
      }

    } catch (SQLException ex) {
      System.out.println("An error occurred while connecting MySQL databse");
      ex.printStackTrace();
    }

  }

}