package com.librarymanagement.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.librarymanagement.model.Kitap;
import com.librarymanagement.model.KitapAlim;
import com.librarymanagement.model.Uye;
import com.librarymanagement.service.KitapAlimService;
import com.librarymanagement.service.KitapService;
import com.librarymanagement.service.UyeService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KitapService kitapService = new KitapService();
        UyeService uyeService = new UyeService();
        KitapAlimService kitapAlimService = new KitapAlimService();

        while (true) {
            System.out.println("1- Üye İşlemleri");
            System.out.println("2- Kitap İşlemleri");
            System.out.println("3- Diğer İşlemler");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    uyeIslemleri(scanner, uyeService);
                    break;
                case 2:
                    kitapIslemleri(scanner, kitapService);
                    break;
                case 3:
                    digerIslemler(scanner, kitapAlimService);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void uyeIslemleri(Scanner scanner, UyeService uyeService) {
        System.out.println("1- Üye Ekle");
        System.out.println("2- Üye Listele");
        System.out.println("3- Üye Güncelle");
        System.out.println("4- Üye Sil");
        System.out.print("Seçiminiz: ");
        int secim = scanner.nextInt();

        switch (secim) {
            case 1:
                System.out.print("Ad: ");
                String ad = scanner.next();
                System.out.print("Soyad: ");
                String soyad = scanner.next();
                System.out.print("Telefon: ");
                String telefon = scanner.next();
                System.out.print("Email: ");
                String email = scanner.next();
                uyeService.uyeEkle(new Uye(0, ad, soyad, telefon, email));
                System.out.println("Üye eklendi.");
                break;
            case 2:
                List<Uye> uyeler = uyeService.tumUyeleriGetir();
                for (Uye uye : uyeler) {
                    System.out.println(uye);
                }
                break;
            case 3:
                System.out.print("Güncellenecek Üye ID: ");
                int uyeId = scanner.nextInt();
                System.out.print("Ad: ");
                ad = scanner.next();
                System.out.print("Soyad: ");
                soyad = scanner.next();
                System.out.print("Telefon: ");
                telefon = scanner.next();
                System.out.print("Email: ");
                email = scanner.next();
                uyeService.uyeGuncelle(new Uye(uyeId, ad, soyad, telefon, email));
                System.out.println("Üye güncellendi.");
                break;
            case 4:
                System.out.print("Silinecek Üye ID: ");
                uyeId = scanner.nextInt();
                uyeService.uyeSil(uyeId);
                System.out.println("Üye silindi.");
                break;
            default:
                System.out.println("Geçersiz seçim!");
        }
    }

    private static void kitapIslemleri(Scanner scanner, KitapService kitapService) {
        System.out.println("1- Kitap Ekle");
        System.out.println("2- Kitap Listele");
        System.out.println("3- Kitap Güncelle");
        System.out.println("4- Kitap Sil");
        System.out.print("Seçiminiz: ");
        int secim = scanner.nextInt();

        switch (secim) {
            case 1:
                System.out.print("ISBN: ");
                String isbn = scanner.next();
                System.out.print("Ad: ");
                String ad = scanner.next();
                System.out.print("Yazar: ");
                String yazar = scanner.next();
                System.out.print("Yayinci: ");
                String yayinci = scanner.next();
                System.out.print("Kategori: ");
                String kategori = scanner.next();
                System.out.print("Raf No: ");
                String rafNo = scanner.next();
                kitapService.kitapEkle(new Kitap(0, isbn, ad, yazar, yayinci, kategori, rafNo));
                System.out.println("Kitap eklendi.");
                break;
            case 2:
                List<Kitap> kitaplar = kitapService.tumKitaplariGetir();
                for (Kitap kitap : kitaplar) {
                    System.out.println(kitap);
                }
                break;
            case 3:
                System.out.print("Güncellenecek Kitap ID: ");
                int kitapId = scanner.nextInt();
                System.out.print("ISBN: ");
                isbn = scanner.next();
                System.out.print("Ad: ");
                ad = scanner.next();
                System.out.print("Yazar: ");
                yazar = scanner.next();
                System.out.print("Yayinci: ");
                yayinci = scanner.next();
                System.out.print("Kategori: ");
                kategori = scanner.next();
                System.out.print("Raf No: ");
                rafNo = scanner.next();
                kitapService.kitapGuncelle(new Kitap(kitapId, isbn, ad, yazar, yayinci, kategori, rafNo));
                System.out.println("Kitap güncellendi.");
                break;
            case 4:
                System.out.print("Silinecek Kitap ID: ");
                kitapId = scanner.nextInt();
                kitapService.kitapSil(kitapId);
                System.out.println("Kitap silindi.");
                break;
            default:
                System.out.println("Geçersiz seçim!");
        }
    }

    private static void digerIslemler(Scanner scanner, KitapAlimService kitapAlimService) {
        System.out.println("1- Kitap Ödünç Al");
        System.out.println("2- Ödünç Alınan Kitapları Listele");
        System.out.println("3- Kitap Teslim Et");
        System.out.println("4- Teslim Edilen Kitapları Listele");
        System.out.print("Seçiminiz: ");
        int secim = scanner.nextInt();

        switch (secim) {
            case 1:
                System.out.print("Üye ID: ");
                int uyeId = scanner.nextInt();
                System.out.print("Kitap ID: ");
                int kitapId = scanner.nextInt();
                System.out.print("Alım Tarihi (YYYY-MM-DD): ");
                String alimTarihiStr = scanner.next();
                System.out.print("Planlanan Teslim Tarihi (YYYY-MM-DD): ");
                String planlananTeslimTarihiStr = scanner.next();
                LocalDate alimTarihi = LocalDate.parse(alimTarihiStr);
                LocalDate planlananTeslimTarihi = LocalDate.parse(planlananTeslimTarihiStr);
                kitapAlimService.kitapAlimEkle(new KitapAlim(0, uyeId, kitapId, alimTarihi, planlananTeslimTarihi, null, 1));
                System.out.println("Kitap ödünç alındı.");
                break;
            case 2:
                List<KitapAlim> alinanKitaplar = kitapAlimService.tumIslemleriGetir();
                for (KitapAlim kitapAlim : alinanKitaplar) {
                    System.out.println(kitapAlim);
                }
                break;
            case 3:
                System.out.print("Üye ID: ");
                uyeId = scanner.nextInt();
                System.out.print("Kitap ID: ");
                kitapId = scanner.nextInt();
                kitapAlimService.kitapTeslimEt(uyeId, kitapId);
                System.out.println("Kitap teslim edildi.");
                break;
            case 4:
                List<KitapAlim> teslimEdilenKitaplar = kitapAlimService.teslimEdilenKitaplariGetir();
                for (KitapAlim kitapAlim : teslimEdilenKitaplar) {
                    System.out.println(kitapAlim);
                }
                break;
            default:
                System.out.println("Geçersiz seçim!");
        }
    }
}
