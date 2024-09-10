package io.hexlet;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {
            String sql1 = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (Statement statement = conn.createStatement()) {
                statement.execute(sql1);
            }

            String sql2 = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (PreparedStatement statement2 = conn.prepareStatement(sql2)) {
                statement2.setString(1, "jimmy");
                statement2.setString(2, "7845612");
                statement2.executeUpdate();

                statement2.setString(1, "tommy");
                statement2.setString(2, "5462136");
                statement2.executeUpdate();
            }

            String sql3 = "SELECT * FROM users";
            try (Statement statement3 = conn.createStatement()) {
                ResultSet resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("phone"));
                }
            }

            String sql4 = "DELETE FROM users WHERE username = ?";
            try (PreparedStatement statement4 = conn.prepareStatement(sql4)) {
                statement4.setString(1, "jimmy");
                statement4.executeUpdate();
            }

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
