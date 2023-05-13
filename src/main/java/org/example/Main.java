package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Получаем соединение от нашего Singleton-класс коннектора
        Connection connectionToDataBase = DBConnector.getConnection();
        Statement statement = connectionToDataBase.createStatement();
        //запрос
        String query = "select * from users";
        //данные
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            //выводим
            System.out.print(resultSet.getInt("id") + " ");
            System.out.print(resultSet.getString("name") + " ");
            System.out.print(resultSet.getInt("age") + " ");
            System.out.print(resultSet.getString("email") + " ");
            System.out.println();
        }
    }
}