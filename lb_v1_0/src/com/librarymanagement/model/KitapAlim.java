package com.librarymanagement.model;

import java.time.LocalDate;

public class KitapAlim {
    private int islemId;
    private int uyeId;
    private int kitapId;
    private LocalDate alimTarihi;
    private LocalDate planlananTeslimTarihi;
    private LocalDate gercekTeslimTarihi;
    private int durum;

    public KitapAlim(int islemId, int uyeId, int kitapId, LocalDate alimTarihi, LocalDate planlananTeslimTarihi, LocalDate gercekTeslimTarihi, int durum) {
        this.setIslemId(islemId);
        this.setUyeId(uyeId);
        this.setKitapId(kitapId);
        this.setAlimTarihi(alimTarihi);
        this.setPlanlananTeslimTarihi(planlananTeslimTarihi);
        this.setGercekTeslimTarihi(gercekTeslimTarihi);
        this.setDurum(durum);
    }
 // Getter ve Setter metodları

	public int getIslemId() {
		return islemId;
	}

	public void setIslemId(int islemId) {
		this.islemId = islemId;
	}

	public int getUyeId() {
		return uyeId;
	}

	public void setUyeId(int uyeId) {
		this.uyeId = uyeId;
	}

	public int getKitapId() {
		return kitapId;
	}

	public void setKitapId(int kitapId) {
		this.kitapId = kitapId;
	}

	public LocalDate getAlimTarihi() {
		return alimTarihi;
	}

	public void setAlimTarihi(LocalDate alimTarihi) {
		this.alimTarihi = alimTarihi;
	}

	public LocalDate getPlanlananTeslimTarihi() {
		return planlananTeslimTarihi;
	}

	public void setPlanlananTeslimTarihi(LocalDate planlananTeslimTarihi) {
		this.planlananTeslimTarihi = planlananTeslimTarihi;
	}

	public LocalDate getGercekTeslimTarihi() {
		return gercekTeslimTarihi;
	}

	public void setGercekTeslimTarihi(LocalDate gercekTeslimTarihi) {
		this.gercekTeslimTarihi = gercekTeslimTarihi;
	}

	public int getDurum() {
		return durum;
	}

	public void setDurum(int durum) {
		this.durum = durum;
	}
	
	 @Override
	    public String toString() {
	        String durumStr = "";
	        switch(durum) {
	            case 1:
	                durumStr = "Teslim Alındı";
	                break;
	            case 2:
	                durumStr = "Teslim Edildi";
	                break;
	            case 3:
	                durumStr = "İşlem İptal Edildi";
	                break;
	        }
	        return "İşlem ID: " + islemId + ", Üye ID: " + uyeId + ", Kitap ID: " + kitapId + ", Alım Tarihi: " + alimTarihi +
	                ", Planlanan Teslim Tarihi: " + planlananTeslimTarihi + ", Gerçek Teslim Tarihi: " +
	                (gercekTeslimTarihi != null ? gercekTeslimTarihi : "Henüz Teslim Edilmedi") + ", Durum: " + durumStr;
	    }

    
}
