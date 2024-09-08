package com.librarymanagement.service;

import java.util.List;

import com.librarymanagement.model.KitapAlim;
import com.librarymanagement.repo.KitapAlimRepo;

public class KitapAlimService {
    private KitapAlimRepo kitapAlimRepo;

    public KitapAlimService(KitapAlimRepo kitapAlimRepo) {
        this.kitapAlimRepo = kitapAlimRepo;
    }

    public void kitapAlimEkle(KitapAlim kitapAlim) {
        kitapAlimRepo.kitapAlimEkle(kitapAlim);
    }

    public List<KitapAlim> kitapAlimlariniListele() {
        return kitapAlimRepo.kitapAlimlariListele();
    }
}
