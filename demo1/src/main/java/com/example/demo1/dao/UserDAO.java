package com.example.demo1.dao;

import java.sql.*;

public class UserDAO {
    private static final String DB_URL = "jdbc:mysql://183.136.206.77:3306/yukino";
    private static final String DB_USER = "yukino";
    private static final String DB_PASSWORD = "yukino123456";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }


    public Boolean login(String name, String password, String userType) {
        String sql = "SELECT * FROM users WHERE Username = ? AND Password = ? AND userType = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 注意：这里我们使用的是setXXX方法设置参数，而不是直接执行查询
            stmt.setString(1, name);
            stmt.setString(2, password); // 注意：这里假定密码是明文存储的，实际中应使用哈希值进行比较
            stmt.setString(3, userType);

            // 执行查询并获取结果集
            try (ResultSet rs = stmt.executeQuery()) {
                // 尝试获取结果集的第一条记录
                if (rs.next()) {
                    // 如果存在记录，表示登录成功
                    return true;
                } else {
                    // 如果不存在记录，表示登录失败
                    return false;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            // 只捕获SQLException，因为ClassNotFoundException应该在getConnection()方法中处理
            throw new RuntimeException("Error executing query to the database", e);
        }
    }

    public Boolean saveUser(String userType, String passWord, String userName) {
        String exits = "SELECT * FROM users WHERE Username = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(exits)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error executing query to the database", e);
        }
        String sql = "INSERT INTO users (UserType, Password, Username) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userType);
            stmt.setString(2, passWord);
            stmt.setString(3, userName);
            int i = stmt.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error saving user to the database", e);
        }
        return false;
    }
}
