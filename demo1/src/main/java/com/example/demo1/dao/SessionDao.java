package com.example.demo1.dao;

import com.example.demo1.model.SessionZ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SessionDao {
    private static final String DB_URL = "jdbc:mysql://183.136.206.77:3306/yukino";
    private static final String DB_USER = "yukino";
    private static final String DB_PASSWORD = "yukino123456";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public int saveSession(Integer filmsId, String start, String end, String time) {
        try {
            String sql = "insert into session(filmsId,start,end,num,time) values(?,?,?,?,?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, filmsId);
                pstmt.setString(2, start);
                pstmt.setString(3, end);
                pstmt.setInt(4, 50);
                pstmt.setString(5, time);
                return pstmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ObservableList<SessionZ> loadSession() {
        ObservableList<SessionZ> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Session";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                SessionZ sessionZ = new SessionZ(rs.getInt("id"), rs.getString("filmsId"), rs.getString("start"), rs.getString("end"), rs.getString("num"), rs.getString("time"));
                data.add(sessionZ);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ObservableList<SessionZ> loadSession(String filmsId, String date, String start, String end) {
        ObservableList<SessionZ> data = FXCollections.observableArrayList();
        // It is better to use PreparedStatement to avoid SQL injection
        StringBuilder sql = new StringBuilder("SELECT * FROM Session WHERE 1=1");
        if (filmsId != null) {
            sql.append(" AND filmsId = ?");
        }
        if (date != null) {
            sql.append(" AND time = ?");
        }
        if (start != null) {
            sql.append(" AND start >= ?");
        }
        if (end != null) {
            sql.append(" AND end <= ?");
        }

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            int index = 1;
            if (filmsId != null) {
                pstmt.setString(index++, filmsId);
            }
            if (date != null) {
                pstmt.setString(index++, date);
            }
            if (start != null) {
                pstmt.setString(index++, start);
            }
            if (end != null) {
                pstmt.setString(index++, end);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SessionZ sessionZ = new SessionZ(rs.getInt("id"), rs.getString("filmsId"), rs.getString("start"), rs.getString("end"), rs.getString("num"), rs.getString("time"));
                data.add(sessionZ);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public void deleteSession(int sessionId) {
        String sql = "DELETE FROM Session WHERE id = ?";  // 使用正确的SQL删除语句
        try (Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sessionId);  // 为SQL语句设置参数
            pstmt.executeUpdate();       // 执行更新操作，这将删除记录
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}