package com.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.exception.EntityNotFoundException;
import com.librarymanagement.model.Uye;
import com.librarymanagement.repository.KitapAlimRepo;
import com.librarymanagement.repository.UyeRepo;

@Service
public class UyeService {

    @Autowired
    private UyeRepo uyeRepo;

    @Autowired
    private KitapAlimRepo kitapAlimRepo;

    // POST - Yeni üye ekleme işlemi
    public Uye uyeEkle(Uye uye) {
        return uyeRepo.save(uye);
    }

    // GET - Tüm üyeleri listeleme işlemi
    public List<Uye> tumUyeleriGetir() {
        return uyeRepo.findAll();
    }

    // GET - ID'ye göre üye bulma işlemi
    public Uye getUyeById(Long uyeId) {
        return uyeRepo.findById(uyeId)
                .orElseThrow(() -> new EntityNotFoundException("Üye bulunamadı."));
    }

    // PUT - Mevcut bir üyeyi güncelleme işlemi
    public Uye uyeGuncelle(Long uyeId, Uye uye) {
        Uye mevcutUye = uyeRepo.findById(uyeId)
                .orElseThrow(() -> new EntityNotFoundException("Güncellenmek istenen üye bulunamadı."));
        mevcutUye.setAd(uye.getAd());
        mevcutUye.setSoyad(uye.getSoyad());
        mevcutUye.setTelefon(uye.getTelefon());
        mevcutUye.setEmail(uye.getEmail());
        return uyeRepo.save(mevcutUye);
    }

    // DELETE - Üye silme işlemi
    public void uyeSil(Long uyeId) {
        if (uyeKitapOduncAlmisMi(uyeId)) {
            throw new RuntimeException("Bu üye kitap ödünç aldığı için silinemez.");
        }
        uyeRepo.deleteById(uyeId);
    }

    // Üyenin kitap ödünç alıp almadığını kontrol etme işlemi
    public boolean uyeKitapOduncAlmisMi(Long uyeId) {
        // Sadece teslim edilmemiş kitapları kontrol et (Gerçek teslim tarihi null olan kitaplar)
        return kitapAlimRepo.existsByUye_UyeIdAndGercekTeslimTarihiIsNull(uyeId);
    }

    public List<Uye> uyeIsmiIleAra(String ad) {
        return uyeRepo.findByAdContainingIgnoreCase(ad); // İsme göre üye arama
    }
}
