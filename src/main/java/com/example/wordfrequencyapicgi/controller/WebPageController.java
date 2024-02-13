package com.example.wordfrequencyapicgi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Controllerklass för att hämta webbsidan wordFrequency.html
@Controller
public class WebPageController {
    // Instansvariabel för att lagra applikationsnamnet
    @Value("my-application")
    String appName;

    // Metod för att hämta startsidan wordFrequency.html
    @GetMapping("/wordFrequency")
    public String hämtaStartsida(Model model) {
        model.addAttribute("appName", appName);
        return "wordFrequency"; }
}
