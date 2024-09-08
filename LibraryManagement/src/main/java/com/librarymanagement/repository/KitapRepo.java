package com.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.model.Kitap;

@Repository // veri erişim anotasyonu
public interface KitapRepo extends JpaRepository<Kitap, Long> {

    // Eğer kitapId'ler listede yoksa kitapları bulur
    List<Kitap> findByKitapIdNotIn(List<Long> kitapIds);

    // Eğer kitapId listesi boş veya null ise, tüm kitapları döner
    default List<Kitap> findAllIfEmpty(List<Long> kitapIds) {
        if (kitapIds == null || kitapIds.isEmpty()) {
            return findAll(); // Tüm kitapları döner
        }
        return findByKitapIdNotIn(kitapIds);
    }
}
