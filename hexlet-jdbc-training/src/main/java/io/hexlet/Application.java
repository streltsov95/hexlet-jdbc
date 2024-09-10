package io.hexlet;

import java.sql.*;

public class Application {
    /**
     * Нужно указывать базовое исключение,
     * потому что выполнение запросов может привести к исключениям
     */
    public static void main(String[] args) throws SQLException {
        /*
          Создаем соединение с базой в памяти
          База создается во время выполнения этой строки
          hexlet_test - имя базы данных
         */
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test");
        String sql1 = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
        // Для выполнения запроса создается объект Statement
        Statement statement = conn.createStatement();
        statement.execute(sql1);
        statement.close();

        String sql2 = "INSERT INTO users (username, phone) VALUES ('tommy', '123456789'), ('jimmy', '987456321')";
        // Для выполнения запроса создается объект Statement
        Statement statement2 = conn.createStatement();
        statement2.executeUpdate(sql2);

        String sql3 = "SELECT * FROM users";
        // Для выполнения запроса создается объект Statement
        Statement statement3 = conn.createStatement();

        // Создается указатель на набор данных в памяти СУБД
        ResultSet resultSet = statement3.executeQuery(sql3);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
            System.out.println(resultSet.getString("phone"));
        }
        statement3.close();

        // Закрываем соединение
        conn.close();
    }
}
