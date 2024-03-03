/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final String severName = "localhost\\SQLEXPRESS";
    private final String databaseName = "QuanLyBanGiay";
    private final String jdbcUrl = "jdbc:sqlserver://" + severName + ":1433;databaseName=" + databaseName + ";encrypt=false;trustServerCertificate=false";
    private final String username = "sa";
    private final String password = "12345";
    private Connection connection;

    public Connection getConnect() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(jdbcUrl, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DBConnection test = new DBConnection();
        System.out.println(test.getConnect());
    }
}
