package com.example.demo1.dao;

import com.example.demo1.model.Order;
import com.example.demo1.model.OrderInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class OrderDao {
    private static final String DB_URL = "jdbc:mysql://183.136.206.77:3306/yukino";
    private static final String DB_USER = "yukino";
    private static final String DB_PASSWORD = "yukino123456";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public Boolean insertOrder(String userId, String filmsId, String sessionid, String num, String price, String start, String end, String time) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String sql = "INSERT INTO order_info (userid, filmsId, sessionid, num, price, start, end, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, filmsId);
            stmt.setString(3, sessionid);
            stmt.setString(4, num);
            stmt.setString(5, price);
            stmt.setString(6, start);
            stmt.setString(7, end);
            stmt.setString(8, time);
            int i = stmt.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error saving user to the database", e);
        }
        return false;
    }

    public ObservableList<Order> loadOrder(String start, String end, String time, String filmsId) {
        ObservableList<Order> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM order_info";
        if (start != null) {
            sql += " WHERE start = '" + start + "'";
        }
        if (end != null) {
            sql += " AND end = '" + end + "'";
        }
        if (time != null) {
            sql += " AND time = '" + time + "'";
        }
        if (filmsId != null) {
            sql += " AND filmsId = '" + filmsId + "'";
        }
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserid(rs.getString("userid"));
                order.setFilmsId(rs.getInt("filmsId"));
                order.setSessionid(rs.getInt("sessionid"));
                order.setNum(rs.getString("num"));
                order.setPrice(rs.getInt("price"));
                order.setStart(rs.getString("start"));
                order.setEnd(rs.getString("end"));
                order.setTime(rs.getString("time"));
                data.add(order);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ObservableList<OrderInfo> loadOrderInfo() {
        ObservableList<OrderInfo> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM order_info LEFT JOIN films ON order_info.filmsId = films.FilmID";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId(rs.getInt("id"));
                orderInfo.setUserid(rs.getString("userid"));
                orderInfo.setFilmsId(rs.getInt("filmsId"));
                orderInfo.setNum(rs.getString("num"));
                orderInfo.setPrice(rs.getInt("price"));
                orderInfo.setStart(rs.getString("start"));
                orderInfo.setEnd(rs.getString("end"));
                orderInfo.setTime(rs.getString("time"));
                // Fixed: Set the title property correctly
                orderInfo.setTitle(rs.getString("title")); // Assuming there is a setTitle method in OrderInfo
                data.add(orderInfo);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ObservableList<OrderInfo> loadOrderInfo(String userName) {
        ObservableList<OrderInfo> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM order_info LEFT JOIN films ON order_info.filmsId = films.FilmID";
        if (userName != null) {
            sql += " WHERE userid = '" + userName + "'";
        }

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId(rs.getInt("id"));
                orderInfo.setUserid(rs.getString("userid"));
                orderInfo.setFilmsId(rs.getInt("filmsId"));
                orderInfo.setNum(rs.getString("num"));
                orderInfo.setPrice(rs.getInt("price"));
                orderInfo.setStart(rs.getString("start"));
                orderInfo.setEnd(rs.getString("end"));
                orderInfo.setTime(rs.getString("time"));
                orderInfo.setTitle(rs.getString("title")); // Assuming there is a setTitle method in OrderInfo
                data.add(orderInfo);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public void deleteOrderInfo(int id) {
        String sql = "DELETE FROM order_info WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error deleting order from the database", e);
        }
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM order_info WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error deleting order from the database", e);
        }
    }
}