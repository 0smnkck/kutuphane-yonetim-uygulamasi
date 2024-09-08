package com.librarymanagement.service;

import java.util.List;

import com.librarymanagement.model.Uye;
import com.librarymanagement.repo.UyeRepo;

public class UyeService {
    private UyeRepo uyeRepo;

    public UyeService(UyeRepo uyeRepo) {
        this.uyeRepo = uyeRepo;
    }

    public void uyeEkle(Uye uye) {
        uyeRepo.uyeEkle(uye);
    }

    public List<Uye> uyeleriListele() {
        return uyeRepo.uyeleriListele();
    }
}
