package com.example.demo1.controller;

import com.example.demo1.model.Cinema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CinemaController {
    // 数据库连接信息，根据实际情况进行调整
    private static final String DB_URL = "jdbc:mysql://183.136.206.77:3306/yukino";
    private static final String DB_USER = "yukino";
    private static final String DB_PASSWORD = "yukino123456";

    // 获取数据库连接
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // 从数据库加载电影院列表
    public ObservableList<Cinema> loadCinemas() {
        ObservableList<Cinema> cinemas = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Cinema"; // 确保表名与你的数据库匹配
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // 假设Cinema类有一个相应的构造函数，根据你的数据库字段调整参数
                Cinema cinema = new Cinema(rs.getInt("cinema_id"), rs.getString("cinema_name"));
                cinemas.add(cinema);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cinemas;
    }

    // 添加一个新的电影院到数据库
    public void addCinema(String name) {
        String sql = "INSERT INTO Cinema (cinema_name) VALUES (?)"; // 确保字段名与你的数据库匹配
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // 假设有删除电影院的需求，这里是删除方法的示例
    public void deleteCinema(int cinemaID) {
        String sql = "DELETE FROM Cinema WHERE cinema_id = ?"; // 确保字段名与你的数据库匹配
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cinemaID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("A cinema was deleted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
