package com.alexander.app.jdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/my_joins_db";
        String username = "username";
        String password = "pass";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            //task 1
            String query1 = "SELECT first_name, last_name, phone_number, residence " +
                    "FROM Employees e " +
                    "JOIN PersonalInfo pi ON e.employee_id = pi.employee_id";
            executeQuery(connection, query1);
            //task2
            String query2 = "SELECT first_name, last_name, date_of_birth, phone_number " +
                    "FROM Employees e " +
                    "JOIN PersonalInfo pi ON e.employee_id = pi.employee_id " +
                    "WHERE pi.marital_status = 'Single'";
            executeQuery(connection, query2);
            //task3
            String query3 = "SELECT first_name, last_name, date_of_birth, phone_number " +
                    "FROM Employees e " +
                    "JOIN Positions pos ON e.employee_id = pos.employee_id " +
                    "JOIN PersonalInfo pi ON e.employee_id = pi.employee_id " +
                    "WHERE pos.position_name = 'Manager'";
            executeQuery(connection, query3);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData metaData = resultSet.getMetaData();

        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
