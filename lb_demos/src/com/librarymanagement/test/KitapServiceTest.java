package com.librarymanagement.test;

import com.librarymanagement.model.Kitap;
import com.librarymanagement.repo.KitapRepo;
import com.librarymanagement.service.KitapService;

public class KitapServiceTest {
    public static void main(String[] args) {
        KitapRepo kitapRepo = new KitapRepo();
        KitapService kitapService = new KitapService(kitapRepo);

        // Yeni kitap ekleme testi
        Kitap kitap = new Kitap();
        kitap.setBaslik("Java Programlama");
        kitap.setYazar("Ali Demir");
        kitap.setYayinYili(2023);
        kitapService.kitapEkle(kitap);
        System.out.println("Yeni kitap eklendi.");

        // Kitapları listeleme testi
        for (Kitap k : kitapService.kitaplariListele()) {
            System.out.println(k.getBaslik() + " - " + k.getYazar());
        }
    }
}
