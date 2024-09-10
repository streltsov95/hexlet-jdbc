package io.hexlet;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {
            String sql1 = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (Statement statement = conn.createStatement()) {
                statement.execute(sql1);
            }

            String sql2 = "INSERT INTO users (username, phone) VALUES ('tommy', '123456789'), ('jimmy', '987456321')";
            try (Statement statement2 = conn.createStatement()) {
                statement2.executeUpdate(sql2);
            }

            String sql3 = "SELECT * FROM users";
            try (Statement statement3 = conn.createStatement()) {
                ResultSet resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("phone"));
                }
            }
        }
    }
}
