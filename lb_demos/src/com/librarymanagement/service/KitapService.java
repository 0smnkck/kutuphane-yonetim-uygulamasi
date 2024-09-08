package com.librarymanagement.service;

import java.util.List;

import com.librarymanagement.model.Kitap;
import com.librarymanagement.repo.KitapRepo;

public class KitapService {
    private KitapRepo kitapRepo;

    public KitapService(KitapRepo kitapRepo) {
        this.kitapRepo = kitapRepo;
    }

    public void kitapEkle(Kitap kitap) {
        kitapRepo.kitapEkle(kitap);
    }

    public List<Kitap> kitaplariListele() {
        return kitapRepo.kitaplariListele();
    }
}
