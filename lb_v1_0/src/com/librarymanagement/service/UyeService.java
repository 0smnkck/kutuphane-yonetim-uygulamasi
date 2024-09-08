package com.librarymanagement.service;

import java.util.List;

import com.librarymanagement.model.Uye;
import com.librarymanagement.repo.UyeRepo;

public class UyeService {

    private UyeRepo uyeRepo;

    public UyeService() {
        this.uyeRepo = new UyeRepo();
    }

    public void uyeEkle(Uye uye) {
        uyeRepo.uyeEkle(uye);
    }

    public List<Uye> tumUyeleriGetir() {
        return uyeRepo.tumUyeleriGetir();
    }

    public void uyeGuncelle(Uye uye) {
        uyeRepo.uyeGuncelle(uye);
    }

    public void uyeSil(int uyeId) {
        uyeRepo.uyeSil(uyeId);
    }
}
