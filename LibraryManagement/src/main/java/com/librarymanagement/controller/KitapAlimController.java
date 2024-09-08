package com.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.librarymanagement.model.Kitap;
import com.librarymanagement.model.KitapAlim;
import com.librarymanagement.service.KitapAlimService;

@Controller
@RequestMapping("/kitapAlim")
public class KitapAlimController {

    @Autowired
    private KitapAlimService kitapAlimService;

    // GET - Kitap Teslim Et sayfasını açar ve ödünç alınan kitapları listeler
    @GetMapping("/teslimEt")
    public String kitapTeslimEtSayfasi(Model model) {
        List<KitapAlim> oduncAlinanKitaplar = kitapAlimService.oduncAlinanKitaplariGetir();
        model.addAttribute("oduncAlinanKitaplar", oduncAlinanKitaplar);
        return "kitap-teslim-et";
    }

    // GET - Kitap Ödünç Al sayfasını açar ve mevcut (ödünç verilmemiş) kitapları listeler
    @GetMapping("/oduncAl")
    public String kitapOduncAlSayfasi(Model model) {
        List<Kitap> mevcutKitaplar = kitapAlimService.bosKitaplariGetir();
        model.addAttribute("mevcutKitaplar", mevcutKitaplar);
        return "kitap-odunc-al";
    }

    // POST - Kitap ödünç alma işlemi
    @PostMapping("/oduncAl/{uyeId}/{kitapId}")
    public String kitapOduncAl(@PathVariable Long uyeId, @PathVariable Long kitapId, RedirectAttributes redirectAttributes) {
        try {
            kitapAlimService.kitapOduncAl(uyeId, kitapId);
            redirectAttributes.addFlashAttribute("successMessage", "Kitap başarıyla ödünç alındı.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Kitap ödünç alınırken bir hata oluştu.");
        }
        return "redirect:/kitapAlim/oduncAl";
    }

    // GET - Tüm kitap alım işlemlerini listeler (API endpoint)
    @GetMapping
    public ResponseEntity<List<KitapAlim>> tumIslemleriGetir() {
        List<KitapAlim> kitapAlimlar = kitapAlimService.tumIslemleriGetir();
        return ResponseEntity.ok(kitapAlimlar);
    }

    // GET - Ödünç alınan kitapları listeler (API endpoint)
    @GetMapping("/oduncAlinanlar")
    public ResponseEntity<List<KitapAlim>> oduncAlinanKitaplariGetir() {
        List<KitapAlim> oduncAlinanKitaplar = kitapAlimService.oduncAlinanKitaplariGetir();
        return ResponseEntity.ok(oduncAlinanKitaplar);
    }

    // PUT - Kitap teslim işlemi (API endpoint)
    @PutMapping("/teslim/{islemId}")
    public ResponseEntity<Void> kitapTeslimEt(@PathVariable Long islemId) {
        try {
            kitapAlimService.kitapTeslimEt(islemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Hata durumunda 500 statü kodu döner
        }
    }
    
    // GET - Teslim edilen kitapları listeler (API endpoint)
    @GetMapping("/teslimEdilenler")
    public ResponseEntity<List<KitapAlim>> teslimEdilenKitaplariGetir() {
        List<KitapAlim> teslimEdilenKitaplar = kitapAlimService.teslimEdilenKitaplariGetir();
        return ResponseEntity.ok(teslimEdilenKitaplar);
    }

    // DELETE - Belirli bir kitap alım işlemini siler (API endpoint)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> kitapAlimSil(@PathVariable Long id) {
        try {
            kitapAlimService.kitapAlimSil(id);
            return ResponseEntity.noContent().build(); // Başarılı silme işlemi
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Hata durumunda 500 statü kodu döner
        }
    }
}
