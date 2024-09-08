package com.librarymanagement.service;

import java.util.List;

import com.librarymanagement.model.Kitap;
import com.librarymanagement.repo.KitapRepo;

public class KitapService {

    private KitapRepo kitapRepo;

    public KitapService() {
        this.kitapRepo = new KitapRepo();
    }

    public void kitapEkle(Kitap kitap) {
        kitapRepo.kitapEkle(kitap);
    }

    public List<Kitap> tumKitaplariGetir() {
        return kitapRepo.tumKitaplariGetir();
    }

    public void kitapGuncelle(Kitap kitap) {
        kitapRepo.kitapGuncelle(kitap);
    }

    public void kitapSil(int kitapId) {
        kitapRepo.kitapSil(kitapId);
    }
}
