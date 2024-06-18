package com.example.demo1.controller;

import com.example.demo1.model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class FilmController {
    private static final String DB_URL = "jdbc:mysql://183.136.206.77:3306/yukino";
    private static final String DB_USER = "yukino";
    private static final String DB_PASSWORD = "yukino123456";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public ObservableList<Film> loadFilms() {
        ObservableList<Film> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM films";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Film film = new Film(rs.getInt("FilmID"), rs.getString("title"), rs.getString("genre"), rs.getString("image"), rs.getString("Description"), rs.getString("director"), rs.getString("duration"), rs.getString("price"));
                data.add(film);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public void addFilm(String title, String genre, String desc, String text, String duration, String director, String price) {
        String sql = "INSERT INTO films (title, genre, Description, image,duration, director,price) VALUES (?, ?, ?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, genre);
            pstmt.setString(3, desc);
            pstmt.setString(4, text);
            pstmt.setString(5, duration);
            pstmt.setString(6, director);
            pstmt.setString(7, price);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteFilm(int filmID) {
        String sql = "DELETE FROM films WHERE FilmID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, filmID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("A film was deleted successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
