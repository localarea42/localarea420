package com.awsconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StoreDepartment {

    public static void main(String[] args) throws Exception {
    	
        String url = "jdbc:mysql://localhost:3306/departments";
        String username = "admin";
        String password = "Admin123";

        // Create a Department object
        Department department = new Department(101, "Engineering");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(url, username, password);

            // Prepared statement for insertion
            String sql = "INSERT INTO department (id, name) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);

            // Set prepared statement parameters
            statement.setInt(1, department.getId());
            statement.setString(2, department.getName());

            // Execute statement
            statement.executeUpdate();

            System.out.println("Department inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources in reverse order
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

