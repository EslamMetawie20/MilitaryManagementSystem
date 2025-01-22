package org.example.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.SoldierRow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/soldiers.db";

    // الاتصال بقاعدة البيانات
    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            return null;
        }
    }

    // إنشاء الجدول لو مش موجود
    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS soldiers (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                national_id TEXT NOT NULL UNIQUE,
                address TEXT,
                date_of_birth TEXT,
                weapon TEXT,
                phone TEXT,
                relatives TEXT,
                punishment TEXT,
                grant TEXT,
                military_number TEXT NOT NULL UNIQUE,
                barcode_path TEXT
            );
        """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // حفظ بيانات الجندي
    public static void saveSoldier(SoldierRow soldier) {
        String sql = """
            INSERT INTO soldiers (name, national_id, address, date_of_birth, weapon, phone, relatives, punishment, grant, military_number, barcode_path)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = connect();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soldier.nameProperty().get());
            pstmt.setString(2, soldier.idProperty().get());
            pstmt.setString(3, soldier.addressProperty().get());
            pstmt.setString(4, soldier.dateOfBirthProperty().get());
            pstmt.setString(5, soldier.weaponProperty().get());
            pstmt.setString(6, soldier.phoneProperty().get());
            pstmt.setString(7, soldier.relativesProperty().get());
            pstmt.setString(8, soldier.punishmentProperty().get());
            pstmt.setString(9, soldier.grantProperty().get());
            pstmt.setString(10, soldier.militaryNumberProperty().get());
            pstmt.setString(11, soldier.getBarcode());
            pstmt.executeUpdate();
            System.out.println("Soldier saved successfully!");
        } catch (SQLException e) {
            System.out.println("Error saving soldier: " + e.getMessage());
        }
    }

    // تحميل بيانات الجنود
    public static ObservableList<SoldierRow> loadSoldiers() {
        ObservableList<SoldierRow> soldiers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM soldiers";

        try (Connection conn = connect();
             var stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                soldiers.add(new SoldierRow(
                        rs.getString("name"),
                        rs.getString("national_id"),
                        rs.getString("address"),
                        rs.getString("date_of_birth"),
                        rs.getString("weapon"),
                        rs.getString("phone"),
                        rs.getString("relatives"),
                        rs.getString("punishment"),
                        rs.getString("grant"),
                        rs.getString("military_number"),
                        rs.getString("barcode_path")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error loading soldiers: " + e.getMessage());
        }
        return soldiers;
    }

    // حذف جندي
    public static void deleteSoldier(String nationalId) {
        String sql = "DELETE FROM soldiers WHERE national_id = ?";

        try (Connection conn = connect();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nationalId);
            pstmt.executeUpdate();
            System.out.println("Soldier deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting soldier: " + e.getMessage());
        }
    }

    // تحديث بيانات جندي
    public static void updateSoldier(SoldierRow soldier) {
        String sql = """
            UPDATE soldiers
            SET name = ?, address = ?, date_of_birth = ?, weapon = ?, phone = ?, relatives = ?, punishment = ?, grant = ?, military_number = ?, barcode_path = ?
            WHERE national_id = ?;
        """;

        try (Connection conn = connect();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soldier.nameProperty().get());
            pstmt.setString(2, soldier.addressProperty().get());
            pstmt.setString(3, soldier.dateOfBirthProperty().get());
            pstmt.setString(4, soldier.weaponProperty().get());
            pstmt.setString(5, soldier.phoneProperty().get());
            pstmt.setString(6, soldier.relativesProperty().get());
            pstmt.setString(7, soldier.punishmentProperty().get());
            pstmt.setString(8, soldier.grantProperty().get());
            pstmt.setString(9, soldier.militaryNumberProperty().get());
            pstmt.setString(10, soldier.getBarcode());
            pstmt.setString(11, soldier.idProperty().get());
            pstmt.executeUpdate();
            System.out.println("Soldier updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating soldier: " + e.getMessage());
        }
    }
}
