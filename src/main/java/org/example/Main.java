package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Database path: " + System.getProperty("user.dir") + "/src/main/resources/soldiers.db");
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/soldiers.db")) {
            if (conn != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        MainView.main(args);

    }
}
