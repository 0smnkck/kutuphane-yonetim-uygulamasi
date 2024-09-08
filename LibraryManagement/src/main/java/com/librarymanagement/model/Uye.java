package com.librarymanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Uye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uyeId;

    @NotNull(message = "Ad boş olamaz")
    @NotEmpty(message = "Ad boş olamaz")
    private String ad;

    @NotNull(message = "Soyad boş olamaz")
    @NotEmpty(message = "Soyad boş olamaz")
    private String soyad;

    @Pattern(regexp = "^[0-9]{10}$", message = "Telefon numarası 10 haneli olmalıdır")
    private String telefon;

    @Email(message = "Geçerli bir email giriniz")
    private String email;

    public Uye() {
    }

    public Uye(Long uyeId, String ad, String soyad, String telefon, String email) {
        this.uyeId = uyeId;
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.email = email;
    }

    public Long getUyeId() {
        return uyeId;
    }

    public void setUyeId(Long uyeId) {
        this.uyeId = uyeId;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Üye ID: " + uyeId + ", Ad: " + ad + ", Soyad: " + soyad + ", Telefon: " + telefon + ", Email: " + email;
    }
}
