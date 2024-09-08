package com.librarymanagement.model;

public class Kitap {
    private int kitapId;
    private String isbn;
    private String ad;
    private String yazar;
    private String yayinci;
    private String kategori;
    private String rafNo;

    public Kitap(int kitapId, String isbn, String ad, String yazar, String yayinci, String kategori, String rafNo) {
        this.setKitapId(kitapId);
        this.setIsbn(isbn);
        this.setAd(ad);
        this.setYazar(yazar);
        this.setYayinci(yayinci);
        this.setKategori(kategori);
        this.setRafNo(rafNo);
    }

    // Getter ve Setter metodları
    
	public int getKitapId() {
		return kitapId;
	}

	public void setKitapId(int kitapId) {
		this.kitapId = kitapId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getYazar() {
		return yazar;
	}

	public void setYazar(String yazar) {
		this.yazar = yazar;
	}

	public String getYayinci() {
		return yayinci;
	}

	public void setYayinci(String yayinci) {
		this.yayinci = yayinci;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getRafNo() {
		return rafNo;
	}

	public void setRafNo(String rafNo) {
		this.rafNo = rafNo;
	}
	
	 @Override
	    public String toString() {
	        return "Kitap ID: " + kitapId + ", ISBN: " + isbn + ", Ad: " + ad + ", Yazar: " + yazar + ", Yayıncı: " + yayinci + ", Kategori: " + kategori + ", Raf No: " + rafNo;
	    }
   
}
