package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static Connection connection;
    static String url = "jdbc:mysql://localhost:3306/mydbtest";
    static String username = "rootNikita";
    static String password = "root";

    private DBConnector() {}
    public static Connection getConnection() {
        try {
            if(connection == null){
                connection = DriverManager.getConnection(url,username,password);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось установить соединение с БД");
        }
        return connection;
    }
}
