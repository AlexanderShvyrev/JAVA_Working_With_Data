package com.alexander.app.jdbc2;

import java.math.BigDecimal;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/car_database";
        String username = "root";
        String password = "Ashvyrev0819";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Cars (brand, model, engine_volume, price, max_speed) VALUES (?, ?, ?, ?, ?)")) {
                insertStatement.setString(1, "Mercedes");
                insertStatement.setString(2, "c300");
                insertStatement.setDouble(3, 3.0);
                insertStatement.setBigDecimal(4, new BigDecimal("42000.00"));
                insertStatement.setInt(5, 180);
                insertStatement.executeUpdate();
            }

            try (Statement selectStatement = connection.createStatement()) {
                ResultSet resultSet = selectStatement.executeQuery("SELECT * FROM Cars");
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id") +
                            ", Brand: " + resultSet.getString("brand") +
                            ", Model: " + resultSet.getString("model") +
                            ", Engine Volume: " + resultSet.getDouble("engine_volume") +
                            ", Price: " + resultSet.getBigDecimal("price") +
                            ", Max Speed: " + resultSet.getInt("max_speed"));
                }
            }

            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE Cars SET max_speed = ? WHERE brand = ?")) {
                updateStatement.setInt(1, 200);
                updateStatement.setString(2, "Toyota");
                updateStatement.executeUpdate();
            }

            try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Cars WHERE brand = ?")) {
                deleteStatement.setString(1, "Mercedes");
                deleteStatement.executeUpdate();
            }

            try (Statement truncateStatement = connection.createStatement()) {
                truncateStatement.executeUpdate("TRUNCATE TABLE Cars");
            }

            try (Statement dropStatement = connection.createStatement()) {
                dropStatement.executeUpdate("DROP TABLE IF EXISTS Cars");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
