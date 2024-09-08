package com.librarymanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.librarymanagement.model.KitapAlim;

@Repository
public interface KitapAlimRepo extends JpaRepository<KitapAlim, Long> {

    // Üyenin teslim etmediği kitapları kontrol eden metod
	boolean existsByUye_UyeIdAndGercekTeslimTarihiIsNull(Long uyeId);

    // Belirli bir kitap ID'sine ve duruma göre kontrol eden metod
    boolean existsByKitap_KitapIdAndDurum(Long kitapId, KitapAlim.Durum durum);

    // Duruma göre kitap alım işlemlerini listeleyen metod
    List<KitapAlim> findByDurum(KitapAlim.Durum durum);

    // Üye ID, Kitap ID ve Duruma göre kitap alım işlemlerini listeleyen metod
    List<KitapAlim> findByUye_UyeIdAndKitap_KitapIdAndDurum(Long uyeId, Long kitapId, KitapAlim.Durum durum);
}
