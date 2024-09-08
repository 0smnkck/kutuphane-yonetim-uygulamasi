package com.librarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Ana sayfa yönlendirmesi için kullanılan Spring MVC controller sınıfı
@Controller
public class HomeController {

    // Kök URL ("/") ziyaret edildiğinde "index" şablonuna yönlendirir
    @GetMapping("/")
    public String home() {
        return "index"; // src/main/resources/templates/index.html sayfasına yönlendirir
    }
}
