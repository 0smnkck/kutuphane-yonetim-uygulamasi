package com.librarymanagement.exception;

public class EntityNotFoundException extends RuntimeException {
    
    // Bu alan, serileştirmenin düzgün çalışmasını sağlamak için eklenmiştir
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
