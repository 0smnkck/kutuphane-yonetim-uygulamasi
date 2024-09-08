package com.librarymanagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.model.Kitap;
import com.librarymanagement.model.KitapAlim;
import com.librarymanagement.model.Uye;
import com.librarymanagement.repository.KitapAlimRepo;
import com.librarymanagement.repository.KitapRepo;
import com.librarymanagement.repository.UyeRepo;

@Service
public class KitapAlimService {

    @Autowired
    private KitapAlimRepo kitapAlimRepo;

    @Autowired
    private KitapRepo kitapRepo;
    
    @Autowired
    private UyeRepo uyeRepo;

    // Tüm kitap alım işlemlerini getir
    public List<KitapAlim> tumIslemleriGetir() {
        return kitapAlimRepo.findAll();
    }

    // Ödünç alınan kitapları getir
    public List<KitapAlim> oduncAlinanKitaplariGetir() {
        return kitapAlimRepo.findByDurum(KitapAlim.Durum.TESLIM_ALINDI);
    }

    // Kitap teslim işlemi
    public void kitapTeslimEt(Long islemId) {
        KitapAlim alim = kitapAlimRepo.findById(islemId)
                .orElseThrow(() -> new RuntimeException("İşlem bulunamadı"));
        if (alim.getDurum() == KitapAlim.Durum.TESLIM_ALINDI) {
            alim.setGercekTeslimTarihi(LocalDate.now());
            alim.setDurum(KitapAlim.Durum.TESLIM_EDILDI);
            kitapAlimRepo.save(alim);
        } else {
            throw new RuntimeException("Kitap zaten teslim edilmiş veya işlem geçersiz.");
        }
    }

    // Teslim edilen kitapları getir
    public List<KitapAlim> teslimEdilenKitaplariGetir() {
        return kitapAlimRepo.findByDurum(KitapAlim.Durum.TESLIM_EDILDI);
    }

    // Kitap ödünç alma işlemi
    public KitapAlim kitapOduncAl(Long uyeId, Long kitapId) {
        // findById metodunu uyeRepo instance'ı üzerinden çağırıyoruz
        Uye uye = uyeRepo.findById(uyeId)
                    .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));

        Kitap kitap = kitapRepo.findById(kitapId)
                    .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        KitapAlim yeniAlim = new KitapAlim();
        yeniAlim.setUye(uye);
        yeniAlim.setKitap(kitap);
        yeniAlim.setAlimTarihi(LocalDate.now());
        yeniAlim.setPlanlananTeslimTarihi(LocalDate.now().plusWeeks(4));
        yeniAlim.setDurum(KitapAlim.Durum.TESLIM_ALINDI);

        return kitapAlimRepo.save(yeniAlim);
    }
    
    // Boştaki kitapları getir (ödünç alınmamış kitaplar)
    public List<Kitap> bosKitaplariGetir() {
        List<Long> oduncAlinanKitapIds = kitapAlimRepo.findByDurum(KitapAlim.Durum.TESLIM_ALINDI)
                                                        .stream()
                                                        .map(kitapAlim -> kitapAlim.getKitap().getKitapId())
                                                        .collect(Collectors.toList());
        if (oduncAlinanKitapIds.isEmpty()) {
            return kitapRepo.findAll();
        } else {
            return kitapRepo.findByKitapIdNotIn(oduncAlinanKitapIds);
        }
    }

    // Kitap alım işlemini sil
    public void kitapAlimSil(Long id) {
        kitapAlimRepo.deleteById(id);
    }
}
