package com.librarymanagement.model;

public class Uye {
    private int uyeId;
    private String ad;
    private String soyad;
    private String telefon;
    private String email;

    public Uye(int uyeId, String ad, String soyad, String telefon, String email) {
        this.setUyeId(uyeId);
        this.setAd(ad);
        this.setSoyad(soyad);
        this.setTelefon(telefon);
        this.setEmail(email);
    }
    // Getter ve Setter metodları

	public int getUyeId() {
		return uyeId;
	}

	public void setUyeId(int uyeId) {
		this.uyeId = uyeId;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 @Override
	    public String toString() {
	        return "Üye ID: " + uyeId + ", Ad: " + ad + ", Soyad: " + soyad + ", Telefon: " + telefon + ", Email: " + email;
	    }

  
}
