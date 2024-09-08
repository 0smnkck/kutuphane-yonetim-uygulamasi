package com.librarymanagement.service;

import java.util.List;

import com.librarymanagement.model.KitapAlim;
import com.librarymanagement.repo.KitapAlimRepo;

public class KitapAlimService {

    private KitapAlimRepo kitapAlimRepo;

    public KitapAlimService() {
        this.kitapAlimRepo = new KitapAlimRepo();
    }

    public void kitapAlimEkle(KitapAlim kitapAlim) {
        kitapAlimRepo.kitapAlimEkle(kitapAlim);
    }

    public List<KitapAlim> tumIslemleriGetir() {
        return kitapAlimRepo.tumIslemleriGetir();
    }

    public void kitapTeslimEt(int uyeId, int kitapId) {
        kitapAlimRepo.kitapTeslimEt(uyeId, kitapId);
    }

    public List<KitapAlim> teslimEdilenKitaplariGetir() {
        return kitapAlimRepo.teslimEdilenKitaplariGetir();
    }
}
