package com.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.librarymanagement.exception.EntityNotFoundException;
import com.librarymanagement.model.Uye;
import com.librarymanagement.service.UyeService;

@Controller
@RequestMapping("/uye")
public class UyeController {

    @Autowired
    private UyeService uyeService;

    // GET - Üye işlemleri sayfasını gösterme (listeleme, ekleme, güncelleme, silme)
    @GetMapping("/islemleri")
    public String uyeIslemleri(Model model) {
        try {
            List<Uye> uyeler = uyeService.tumUyeleriGetir(); // Tüm üyeleri alır ve model'e ekler.
            model.addAttribute("uyeler", uyeler);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Üyeler listelenirken bir hata oluştu.");
        }
        return "uye-islemleri";
    }

    // GET - Üye ekleme formunu gösterme
    @GetMapping("/ekle")
    public String uyeEkleForm(Model model) {
        model.addAttribute("uye", new Uye()); // Yeni bir Uye nesnesi model'e eklenir.
        return "uye-ekle";
    }

    // POST - Yeni üye ekleme
    @PostMapping("/ekle")
    public String uyeEkle(@ModelAttribute Uye uye, Model model) {
        try {
            uyeService.uyeEkle(uye); // Yeni üyeyi kaydeder.
            return "redirect:/uye/listele";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Üye eklenirken bir hata oluştu.");
            return "uye-ekle";
        }
    }

    // GET - Tüm üyeleri listeleme
    @GetMapping("/listele")
    public String tumUyeleriGetir(Model model) {
        return uyeIslemleri(model); // Üyeleri listelemek için "uyeIslemleri" metodunu tekrar kullanıyoruz.
    }

    // GET - Güncelleme formunu gösterme
    @GetMapping("/guncelle/{id}")
    public String uyeGuncelleForm(@PathVariable Long id, Model model) {
        try {
            Uye uye = uyeService.getUyeById(id); // Belirtilen ID'ye sahip üyeyi getirir.
            model.addAttribute("uye", uye);
            return "uye-guncelle";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "Üye bulunamadı: " + e.getMessage());
            return "redirect:/uye/listele";
        }
    }

    // POST - Üye güncelleme işlemi
    @PostMapping("/guncelle/{id}")
    public String uyeGuncelle(@PathVariable Long id, @ModelAttribute Uye uye, Model model) {
        try {
            uyeService.uyeGuncelle(id, uye); // ID ve yeni üye bilgileri ile günceller
            return "redirect:/uye/listele";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "Üye güncellenirken bir hata oluştu: " + e.getMessage());
            return "uye-guncelle";
        }
    }

    // GET - Üye silme işlemi
    @GetMapping("/sil/{id}")
    public String uyeSil(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // Yalnızca teslim edilmemiş kitapları kontrol ediyoruz
            if (uyeService.uyeKitapOduncAlmisMi(id)) {
                redirectAttributes.addFlashAttribute("errorMessage", "Bu üye ödünç kitap aldığı için şu anda silinemez!");
                return "redirect:/uye/islemleri";
            }
            uyeService.uyeSil(id); // Üyeyi silme işlemi
            return "redirect:/uye/listele";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Üye silinirken bir hata oluştu.");
            return "redirect:/uye/islemleri";
        }
    }
    
 // GET - Üye arama işlemi
    @GetMapping("/arama")
    public ResponseEntity<List<Uye>> uyeAra(@RequestParam("q") String query) {
        List<Uye> uyeler = uyeService.uyeIsmiIleAra(query);
        return ResponseEntity.ok(uyeler);
    }
}
