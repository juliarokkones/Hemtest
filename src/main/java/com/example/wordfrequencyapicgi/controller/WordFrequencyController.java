package com.example.wordfrequencyapicgi.controller;

import com.example.wordfrequencyapicgi.object.WordFrequency;
import com.example.wordfrequencyapicgi.service.WordFrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// REST-ful api för att räkna ord i inmatad text
@RestController
public class WordFrequencyController {

    // Instansera service-klassen
    @Autowired
    public WordFrequencyService wordFrequencyService;

    // Konstruktor
    @Autowired
    public WordFrequencyController(WordFrequencyService wordFrequencyService) {
        this.wordFrequencyService = wordFrequencyService;
    }

    //Post-metod för att räkna ord som tar in en sträng som parameter från inmatad text
    @PostMapping("/count")
    public ResponseEntity<List<WordFrequency>> countWordFrequencies(@RequestBody String text) {
        return wordFrequencyService.countWordFrequencies(text);
    }
}
