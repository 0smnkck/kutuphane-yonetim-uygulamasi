package com.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.librarymanagement.model.KitapAlim;
import com.librarymanagement.service.KitapAlimService;

@Controller
@RequestMapping("/digerIslemler")
public class DigerIslemlerController {

    @Autowired
    private KitapAlimService kitapAlimService;

    // Teslim edilmemiş kitapları listeleme sayfası
    @GetMapping("/teslimEt")
    public String kitapTeslimEtSayfasi(Model model) {
        List<KitapAlim> teslimEdilmemisKitaplar = kitapAlimService.oduncAlinanKitaplariGetir();
        model.addAttribute("teslimEdilmemisKitaplar", teslimEdilmemisKitaplar);
        return "kitap-teslim-et"; // "kitap-teslim-et.html" sayfasına yönlendirme
    }

    // Diğer işlemler ana sayfası
    @GetMapping
    public String digerIslemler() {
        return "diger-islemler"; // "diger-islemler.html" sayfasına yönlendirme
    }

    // Kitap ödünç alma işlemi
    @PostMapping("/oduncAl/{uyeId}/{kitapId}")
    public ResponseEntity<KitapAlim> kitapOduncAl(@PathVariable Long uyeId, @PathVariable Long kitapId) {
        try {
            KitapAlim yeniKitapAlim = kitapAlimService.kitapOduncAl(uyeId, kitapId);
            return ResponseEntity.ok(yeniKitapAlim);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Hata durumunda 500 Internal Server Error döner
        }
    }

    // Ödünç alınan kitapları listeleme
    @GetMapping("/oduncAlinanlar")
    public ResponseEntity<List<KitapAlim>> oduncAlinanKitaplariGetir() {
        List<KitapAlim> oduncAlinanKitaplar = kitapAlimService.oduncAlinanKitaplariGetir();
        return ResponseEntity.ok(oduncAlinanKitaplar);
    }

    // Kitap teslim işlemi
    @PutMapping("/teslim/{islemId}")
    public ResponseEntity<Void> kitapTeslimEt(@PathVariable Long islemId) {
        try {
            kitapAlimService.kitapTeslimEt(islemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Hata durumunda 500 Internal Server Error döner
        }
    }

    // Teslim edilen kitapları listeleme
    @GetMapping("/teslimEdilenler")
    public ResponseEntity<List<KitapAlim>> teslimEdilenKitaplariGetir() {
        List<KitapAlim> teslimEdilenKitaplar = kitapAlimService.teslimEdilenKitaplariGetir();
        return ResponseEntity.ok(teslimEdilenKitaplar);
    }
}
