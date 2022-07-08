package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/kata_pp";
    private static final String USERNAME = "root";
    private static final String USERPASSWORD = "admin";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean closeConnect() {
        try {
            getConnection().close();
            return connection.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
