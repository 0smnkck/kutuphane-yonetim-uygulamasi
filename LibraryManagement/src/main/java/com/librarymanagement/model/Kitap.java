package com.librarymanagement.model;
/*
 Bu Kitap sınıfı, kütüphanede bulunan kitapların bilgilerini içeren bir veritabanı tablosunu temsil eder. 
 JPA anotasyonları (@Entity, @Id, @GeneratedValue) kullanılarak bu sınıf veritabanı tablosuna eşlenir.
 Spring Boot uygulaması bu sınıfı kullanarak veritabanı işlemlerini gerçekleştirir. 
 Sınıf, kitapların temel bilgilerini (ISBN, ad, yazar, yayıncı, kategori, raf numarası) saklar ve veritabanında bu bilgileri yönetir. 
 kitapId alanı, daha büyük değerleri desteklemek için Long olarak ayarlanmıştır.
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity // Bu sınıfın bir veritabanı tablosuna karşılık geldiğini belirtir.
public class Kitap {

    @Id // Sınıftaki bu alanın birincil anahtar (primary key) olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak oluşturulacağını belirtir.
    private Long kitapId; // int yerine Long olarak güncellendi

    @NotNull(message = "ISBN boş olamaz")
    @Pattern(regexp = "^[0-9]{13}$", message = "ISBN 13 karakter olmalıdır")
    private String isbn;

    @NotNull(message = "Kitap adı boş olamaz")
    @NotEmpty(message = "Kitap adı boş olamaz")
    @Size(max = 100, message = "Kitap adı en fazla 100 karakter olabilir")
    private String ad;

    @NotNull(message = "Yazar adı boş olamaz")
    @Size(max = 100, message = "Yazar adı en fazla 100 karakter olabilir")
    private String yazar;

    @NotNull(message = "Yayınevi boş olamaz")
    @Size(max = 100, message = "Yayınevi en fazla 100 karakter olabilir")
    private String yayinci;

    @NotNull(message = "Kategori boş olamaz")
    private String kategori; // İsteğe bağlı olarak Enum yapılabilir.

    @Size(max = 10, message = "Raf numarası en fazla 10 karakter olabilir")
    private String rafNo;

    // Boş constructor (JPA tarafından gereklidir)
    public Kitap() {
    }

    public Kitap(Long kitapId, String isbn, String ad, String yazar, String yayinci, String kategori, String rafNo) { // kitapId için int yerine Long kullanıldı
        this.kitapId = kitapId;
        this.isbn = isbn;
        this.ad = ad;
        this.yazar = yazar;
        this.yayinci = yayinci;
        this.kategori = kategori;
        this.rafNo = rafNo;
    }

    // Getter ve Setter metodları
    public Long getKitapId() { // int yerine Long olarak güncellendi
        return kitapId;
    }

    public void setKitapId(Long kitapId) { // int yerine Long olarak güncellendi
        this.kitapId = kitapId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getYayinci() {
        return yayinci;
    }

    public void setYayinci(String yayinci) {
        this.yayinci = yayinci;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getRafNo() {
        return rafNo;
    }

    public void setRafNo(String rafNo) {
        this.rafNo = rafNo;
    }

    @Override
    public String toString() {
        return "Kitap ID: " + kitapId + ", ISBN: " + isbn + ", Ad: " + ad + ", Yazar: " + yazar + ", Yayıncı: " + yayinci + ", Kategori: " + kategori + ", Raf No: " + rafNo;
    }
}
