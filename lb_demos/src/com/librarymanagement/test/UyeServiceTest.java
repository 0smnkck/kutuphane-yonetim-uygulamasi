package com.librarymanagement.test;

import com.librarymanagement.model.Uye;
import com.librarymanagement.repo.UyeRepo;
import com.librarymanagement.service.UyeService;

public class UyeServiceTest {
    public static void main(String[] args) {
        UyeRepo uyeRepo = new UyeRepo();
        UyeService uyeService = new UyeService(uyeRepo);

        // Yeni üye ekleme testi
        Uye uye = new Uye();
        uye.setIsim("Mehmet Kaya");
        uye.setEmail("mehmet.kaya@example.com");
        uye.setTelefon("05554443322");
        uyeService.uyeEkle(uye);
        System.out.println("Yeni üye eklendi.");

        // Üyeleri listeleme testi
        for (Uye u : uyeService.uyeleriListele()) {
            System.out.println(u.getIsim() + " - " + u.getEmail());
        }
    }
}
