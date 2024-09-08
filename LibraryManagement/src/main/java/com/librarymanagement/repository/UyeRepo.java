package com.librarymanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.librarymanagement.model.Uye;

@Repository
public interface UyeRepo extends JpaRepository<Uye, Long> {

    // Kullanıcı ismine göre arama yapar, büyük/küçük harf duyarsız
    List<Uye> findByAdContainingIgnoreCase(String ad);
}
