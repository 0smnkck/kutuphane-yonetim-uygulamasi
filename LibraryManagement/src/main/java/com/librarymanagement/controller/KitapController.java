package com.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.librarymanagement.model.Kitap;
import com.librarymanagement.service.KitapService;

@Controller
@RequestMapping("/kitap")
public class KitapController {

    @Autowired
    private KitapService kitapService;

    // GET - Kitap işlemleri sayfasını gösterme
    @GetMapping("/islemleri")
    public String kitapIslemleri(Model model) {
        List<Kitap> kitaplar = kitapService.tumKitaplariGetir();
        model.addAttribute("kitaplar", kitaplar);
        return "kitap-islemleri";
    }

    // GET - Yeni kitap ekleme formunu gösterme
    @GetMapping("/ekle")
    public String yeniKitapForm(Model model) {
        model.addAttribute("kitap", new Kitap());
        return "kitap-ekle";
    }

    // POST - Yeni kitap ekleme
    @PostMapping("/ekle")
    public String kitapEkle(@ModelAttribute Kitap kitap, RedirectAttributes redirectAttributes) {
        try {
            kitapService.kitapEkle(kitap);
            redirectAttributes.addFlashAttribute("successMessage", "Kitap başarıyla eklendi.");
            return "redirect:/kitap/islemleri";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Kitap eklenirken bir hata oluştu.");
            return "redirect:/kitap/ekle";
        }
    }

    // GET - Kitap güncelleme formunu gösterme
    @GetMapping("/guncelle/{id}")
    public String kitapGuncelleForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Kitap kitap = kitapService.getKitapById(id);
            model.addAttribute("kitap", kitap);
            return "kitap-guncelle";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Kitap bulunamadı.");
            return "redirect:/kitap/islemleri";
        }
    }

    // POST - Kitap güncelleme işlemi
    @PostMapping("/guncelle/{id}")
    public String kitapGuncelle(@PathVariable Long id, @ModelAttribute Kitap kitap, RedirectAttributes redirectAttributes) {
        try {
            kitapService.kitapGuncelle(id, kitap);
            redirectAttributes.addFlashAttribute("successMessage", "Kitap başarıyla güncellendi.");
            return "redirect:/kitap/islemleri";
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Kitap güncellenemedi, çünkü bir üye tarafından ödünç alınmış.");
            return "redirect:/kitap/guncelle/" + id;
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Kitap güncellenemedi. Lütfen tekrar deneyin.");
            return "redirect:/kitap/guncelle/" + id;
        }
    }

    // POST - Kitap silme işlemi
    @PostMapping("/sil/{id}")
    public String kitapSil(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            kitapService.kitapSil(id);
            redirectAttributes.addFlashAttribute("successMessage", "Kitap başarıyla silindi.");
            return "redirect:/kitap/islemleri";
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Kitap silinemedi, çünkü bir üye tarafından ödünç alınmış.");
            return "redirect:/kitap/islemleri";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Kitap silinemedi. Lütfen tekrar deneyin.");
            return "redirect:/kitap/islemleri";
        }
    }
}
