package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydbtest";
        String username = "rootNikita";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, username, password); Statement statement = connection.createStatement()) {
            if(!connection.isClosed()){
                System.out.println("Соединение с БД установлено");
                //создание новых данных
                statement.execute("INSERT INTO animal (anim_name, anim_desc) VALUES ('name', 'random');");
                //обновление данных
                statement.executeUpdate("UPDATE animal SET anim_name='pig' WHERE id=1;");
                //получение данных
                ResultSet resultSet = statement.executeQuery("SELECT * FROM animal;");

                //пакетированный запрос
                statement.addBatch("INSERT INTO animal (anim_name, anim_desc) VALUES ('cow', 'random');");
                statement.addBatch("UPDATE animal SET anim_name='NEW_COW' WHERE id=3;");

                statement.executeBatch();
                //очистить Batch от старых запросов
                statement.clearBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}