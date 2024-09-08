package com.librarymanagement.test;

import java.util.Date;

import com.librarymanagement.model.KitapAlim;
import com.librarymanagement.repo.KitapAlimRepo;
import com.librarymanagement.service.KitapAlimService;

public class KitapAlimServiceTest {
    public static void main(String[] args) {
        KitapAlimRepo kitapAlimRepo = new KitapAlimRepo();
        KitapAlimService kitapAlimService = new KitapAlimService(kitapAlimRepo);

        // Yeni kitap ödünç alma işlemi ekleme testi
        KitapAlim kitapAlim = new KitapAlim();
        kitapAlim.setUyeId(1);
        kitapAlim.setKitapId(2);
        kitapAlim.setAlimTarihi(new Date());
        kitapAlim.setIadeTarihi(null); // Henüz iade edilmedi
        kitapAlimService.kitapAlimEkle(kitapAlim);
        System.out.println("Yeni kitap ödünç alma işlemi eklendi.");

        // Ödünç alma işlemlerini listeleme testi
        for (KitapAlim ka : kitapAlimService.kitapAlimlariniListele()) {
            System.out.println("Üye ID: " + ka.getUyeId() + ", Kitap ID: " + ka.getKitapId());
        }
    }
}
