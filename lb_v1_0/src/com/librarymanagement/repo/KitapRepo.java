package com.librarymanagement.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.librarymanagement.dbconnection.DatabaseConnection;
import com.librarymanagement.model.Kitap;

public class KitapRepo {

    public void kitapEkle(Kitap kitap) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Kitaplar (isbn, ad, yazar, yayinci, kategori, raf_no) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kitap.getIsbn());
            pstmt.setString(2, kitap.getAd());
            pstmt.setString(3, kitap.getYazar());
            pstmt.setString(4, kitap.getYayinci());
            pstmt.setString(5, kitap.getKategori());
            pstmt.setString(6, kitap.getRafNo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Kitap> tumKitaplariGetir() {
        List<Kitap> kitaplar = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Kitaplar";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Kitap kitap = new Kitap(
                    rs.getInt("kitap_id"),
                    rs.getString("isbn"),
                    rs.getString("ad"),
                    rs.getString("yazar"),
                    rs.getString("yayinci"),
                    rs.getString("kategori"),
                    rs.getString("raf_no")
                );
                kitaplar.add(kitap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kitaplar;
    }

    public void kitapGuncelle(Kitap kitap) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE Kitaplar SET isbn = ?, ad = ?, yazar = ?, yayinci = ?, kategori = ?, raf_no = ? WHERE kitap_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kitap.getIsbn());
            pstmt.setString(2, kitap.getAd());
            pstmt.setString(3, kitap.getYazar());
            pstmt.setString(4, kitap.getYayinci());
            pstmt.setString(5, kitap.getKategori());
            pstmt.setString(6, kitap.getRafNo());
            pstmt.setInt(7, kitap.getKitapId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kitapSil(int kitapId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Kitaplar WHERE kitap_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, kitapId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
