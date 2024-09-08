package com.librarymanagement.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.librarymanagement.dbconnection.DatabaseConnection;
import com.librarymanagement.model.KitapAlim;

public class KitapAlimRepo {

    public void kitapAlimEkle(KitapAlim kitapAlim) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO KitapAlimlari (uye_id, kitap_id, alim_tarihi, planlanan_teslim_tarihi, gercek_teslim_tarihi, durum) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, kitapAlim.getUyeId());
            pstmt.setInt(2, kitapAlim.getKitapId());
            pstmt.setDate(3, Date.valueOf(kitapAlim.getAlimTarihi()));
            pstmt.setDate(4, Date.valueOf(kitapAlim.getPlanlananTeslimTarihi()));
            pstmt.setDate(5, kitapAlim.getGercekTeslimTarihi() != null ? Date.valueOf(kitapAlim.getGercekTeslimTarihi()) : null);
            pstmt.setInt(6, kitapAlim.getDurum());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<KitapAlim> tumIslemleriGetir() {
        List<KitapAlim> islemler = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM KitapAlimlari";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                KitapAlim kitapAlim = new KitapAlim(
                    rs.getInt("islem_id"),
                    rs.getInt("uye_id"),
                    rs.getInt("kitap_id"),
                    rs.getDate("alim_tarihi").toLocalDate(),
                    rs.getDate("planlanan_teslim_tarihi").toLocalDate(),
                    rs.getDate("gercek_teslim_tarihi") != null ? rs.getDate("gercek_teslim_tarihi").toLocalDate() : null,
                    rs.getInt("durum")
                );
                islemler.add(kitapAlim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return islemler;
    }

    public void kitapTeslimEt(int uyeId, int kitapId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE KitapAlimlari SET gercek_teslim_tarihi = NOW(), durum = 2 WHERE uye_id = ? AND kitap_id = ? AND durum = 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uyeId);
            pstmt.setInt(2, kitapId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<KitapAlim> teslimEdilenKitaplariGetir() {
        List<KitapAlim> teslimEdilenler = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM KitapAlimlari WHERE durum = 2";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                KitapAlim kitapAlim = new KitapAlim(
                    rs.getInt("islem_id"),
                    rs.getInt("uye_id"),
                    rs.getInt("kitap_id"),
                    rs.getDate("alim_tarihi").toLocalDate(),
                    rs.getDate("planlanan_teslim_tarihi").toLocalDate(),
                    rs.getDate("gercek_teslim_tarihi") != null ? rs.getDate("gercek_teslim_tarihi").toLocalDate() : null,
                    rs.getInt("durum")
                );
                teslimEdilenler.add(kitapAlim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teslimEdilenler;
    }
}
