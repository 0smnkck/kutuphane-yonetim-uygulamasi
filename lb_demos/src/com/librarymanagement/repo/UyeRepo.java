package com.librarymanagement.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.librarymanagement.dbconnection.DatabaseConnection;
import com.librarymanagement.model.Uye;

public class UyeRepo {

    public void uyeEkle(Uye uye) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Uyeler (ad, soyad, telefon, email) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uye.getAd());
            pstmt.setString(2, uye.getSoyad());
            pstmt.setString(3, uye.getTelefon());
            pstmt.setString(4, uye.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Uye> tumUyeleriGetir() {
        List<Uye> uyeler = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Uyeler";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Uye uye = new Uye(
                    rs.getInt("uye_id"),
                    rs.getString("ad"),
                    rs.getString("soyad"),
                    rs.getString("telefon"),
                    rs.getString("email")
                );
                uyeler.add(uye);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uyeler;
    }

    public void uyeGuncelle(Uye uye) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE Uyeler SET ad = ?, soyad = ?, telefon = ?, email = ? WHERE uye_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uye.getAd());
            pstmt.setString(2, uye.getSoyad());
            pstmt.setString(3, uye.getTelefon());
            pstmt.setString(4, uye.getEmail());
            pstmt.setInt(5, uye.getUyeId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uyeSil(int uyeId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Uyeler WHERE uye_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uyeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
