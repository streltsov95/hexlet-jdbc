package io.hexlet;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {
            String sql1 = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (Statement statement = conn.createStatement()) {
                statement.execute(sql1);
            }

            var dao = new UserDAO(conn);
            dao.save(new User("jimmy", "+78921255"));
            dao.save(new User("tommy", "+789454655"));

            String sql3 = "SELECT * FROM users";
            try (Statement statement3 = conn.createStatement()) {
                ResultSet resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.println(resultSet.getLong("id"));
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("phone"));
                }
            }
            try {
                var user = dao.find(2L);
                System.out.println(user);
                var user1 = dao.find(5L);
                System.out.println(user1);

                dao.delete(1L);

                dao.delete(6L);
            } catch (SQLException e) {
                System.out.println("user does not exist");
            }


            try (Statement statement3 = conn.createStatement()) {
                ResultSet resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.println(resultSet.getLong("id"));
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("phone"));
                }
            }
        }
    }
}
