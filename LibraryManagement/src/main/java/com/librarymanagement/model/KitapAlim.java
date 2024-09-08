package com.librarymanagement.model;
/*
 Entity: Bu sınıfın bir veritabanı tablosu olarak işaretlenmesini sağlar.
GeneratedValue: Birincil anahtarın (ID) nasıl oluşturulacağını belirtir.
Id: Sınıftaki bir alanın birincil anahtar olduğunu belirtir.
JoinColumn: Bir sütunun başka bir tabloya nasıl bağlanacağını belirtir.
ManyToOne: Birçok işlem bir üyeye veya kitaba ait olabilir.
EnumType, Enumerated: Bir enum türünün veritabanında nasıl saklanacağını belirtir.
 */
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Entity
public class KitapAlim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long islemId; // int yerine Long olarak güncellendi

    @ManyToOne
    @JoinColumn(name = "uye_id", nullable = false)
    @NotNull(message = "Üye boş olamaz")
    private Uye uye;

    @ManyToOne
    @JoinColumn(name = "kitap_id", nullable = false)
    @NotNull(message = "Kitap boş olamaz")
    private Kitap kitap;

    @NotNull(message = "Alım tarihi boş olamaz")
    private LocalDate alimTarihi;

    @NotNull(message = "Planlanan teslim tarihi boş olamaz")
    private LocalDate planlananTeslimTarihi;

    private LocalDate gercekTeslimTarihi;

    @Enumerated(EnumType.STRING)
    private Durum durum; // int yerine Enum olarak güncellendi
    
    public enum Durum {
        TESLIM_ALINDI,
        TESLIM_EDILDI,
        IPTAL_EDILDI
    }

    // Boş constructor (JPA tarafından gereklidir)
    public KitapAlim() {}

    public KitapAlim(Long islemId, Uye uye, Kitap kitap, LocalDate alimTarihi, LocalDate planlananTeslimTarihi, LocalDate gercekTeslimTarihi, Durum durum) {
        this.islemId = islemId;
        this.uye = uye;
        this.kitap = kitap;
        this.alimTarihi = alimTarihi;
        this.planlananTeslimTarihi = planlananTeslimTarihi;
        this.gercekTeslimTarihi = gercekTeslimTarihi;
        this.durum = durum;
    }

    // Getter ve Setter metodları

    public Long getIslemId() {
        return islemId;
    }

    public void setIslemId(Long islemId) {
        this.islemId = islemId;
    }

    public Uye getUye() {
        return uye;
    }

    public void setUye(Uye uye) {
        this.uye = uye;
    }

    public Kitap getKitap() {
        return kitap;
    }

    public void setKitap(Kitap kitap) {
        this.kitap = kitap;
    }

    public LocalDate getAlimTarihi() {
        return alimTarihi;
    }

    public void setAlimTarihi(LocalDate alimTarihi) {
        this.alimTarihi = alimTarihi;
    }

    public LocalDate getPlanlananTeslimTarihi() {
        return planlananTeslimTarihi;
    }

    public void setPlanlananTeslimTarihi(LocalDate planlananTeslimTarihi) {
        this.planlananTeslimTarihi = planlananTeslimTarihi;
    }

    public LocalDate getGercekTeslimTarihi() {
        return gercekTeslimTarihi;
    }

    public void setGercekTeslimTarihi(LocalDate gercekTeslimTarihi) {
        this.gercekTeslimTarihi = gercekTeslimTarihi;
    }

    public Durum getDurum() {
        return durum;
    }

    public void setDurum(Durum durum) {
        this.durum = durum;
    }

    @Override
    public String toString() {
        return "İşlem ID: " + islemId + ", Üye: " + uye.getAd() + " " + uye.getSoyad() + ", Kitap: " + kitap.getAd() +
               ", Alım Tarihi: " + alimTarihi + ", Planlanan Teslim Tarihi: " + planlananTeslimTarihi +
               ", Gerçek Teslim Tarihi: " + (gercekTeslimTarihi != null ? gercekTeslimTarihi : "Henüz Teslim Edilmedi") +
               ", Durum: " + durum;
    }
}
