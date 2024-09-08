package com.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.librarymanagement.exception.EntityNotFoundException;
import com.librarymanagement.model.Kitap;
import com.librarymanagement.repository.KitapRepo;

@Service
public class KitapService {

    @Autowired
    private KitapRepo kitapRepo;

    // POST - Yeni kitap ekleme işlemi
    public Kitap kitapEkle(Kitap kitap) {
        return kitapRepo.save(kitap);
    }

    // GET - Tüm kitapları listeleme işlemi
    public List<Kitap> tumKitaplariGetir() {
        return kitapRepo.findAll();
    }

    // GET - ID'ye göre kitap bulma işlemi
    public Kitap getKitapById(Long kitapId) {
        return kitapRepo.findById(kitapId)
                .orElseThrow(() -> new EntityNotFoundException("Kitap bulunamadı."));
    }

    // PUT - Mevcut bir kitabı güncelleme işlemi
    public Kitap kitapGuncelle(Long kitapId, Kitap kitap) {
        kitapRepo.findById(kitapId)
                .orElseThrow(() -> new EntityNotFoundException("Kitap bulunamadı."));
        return kitapRepo.save(kitap);
    }

    // DELETE - Kitap silme işlemi
    public void kitapSil(Long kitapId) {
        try {
            kitapRepo.deleteById(kitapId);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Kitap silinemedi çünkü başkası tarafından ödünç alınmış.");
        }
    }
}
