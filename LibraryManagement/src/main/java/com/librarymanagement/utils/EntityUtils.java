package com.librarymanagement.utils;

import org.springframework.data.jpa.repository.JpaRepository;

public class EntityUtils {
    
    public static <T> T findEntityById(JpaRepository<T, Long> repo, Long id, String entityName) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException(entityName + " bulunamadı."));
    }
}
