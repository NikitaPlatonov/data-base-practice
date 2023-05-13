package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        Connection connectionToDataBase = DBConnector.getConnection();
/*        String quary = "INSERT INTO userslogin(name,login,password) values(?,?,?)";
        //Получаем соединение от нашего Singleton-класс коннектора
        Connection connectionToDataBase = DBConnector.getConnection();
        PreparedStatement preparedStatement = connectionToDataBase.prepareStatement(quary);
        System.out.println("Привет! Ты можешь зарегистрировать в нашем приложении!");
        System.out.println("Введи имя, которое будет отображаться на сайте");

        String inputUserName = scanner.nextLine();
        preparedStatement.setString(1,inputUserName);

        System.out.println("Отлично! Теперь логин!");
        String inputUserLogin = scanner.nextLine();
        preparedStatement.setString(2, inputUserLogin);

        System.out.println("Теперь давай установим пароль!");
        String inputUserPassword = scanner.nextLine();
        preparedStatement.setString(3,inputUserPassword);

        preparedStatement.execute();

        System.out.println("Теперь ты можешь попробовать авторизоваться!");
        Statement statement = connectionToDataBase.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT login from userslogin");
        */
        int id = 0;
        boolean loginCheck = false;

        while (true) {
            System.out.println("Введите ваш логин");
            String inputLogin = scanner.nextLine();

            Statement statement = connectionToDataBase.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from userslogin");

            while (resultSet.next()) {
                String login = resultSet.getString(3);
                if (login.equals(inputLogin)) {
                    id = resultSet.getInt(1);
                    loginCheck = true;
                    break;
                }
            }

            if (loginCheck) {
                break;
            } else {
                System.out.println("Неверный логин. Попробуйте снова.");
            }
        }

        System.out.println("Отлично! Теперь введите пароль");
        String query = "SELECT password from userslogin where id=?;";
        PreparedStatement preparedStatement = connectionToDataBase.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultPassword = preparedStatement.executeQuery();
        //TODO перешли на первый результат запроса !!!
        resultPassword.next();
        String passwordUser = resultPassword.getString(1);
        int count = -1;
        while (true){
            count++;
            System.out.println("Введите пароль");
            String input = scanner.nextLine();
            if(count == 3){
                break;
            }
            if(passwordUser.equals(input)){
                System.out.println("Вы успешно авторизовались");
                break;
            } else {
                System.out.println("Неверный пароль");
            }
        }
    }
}