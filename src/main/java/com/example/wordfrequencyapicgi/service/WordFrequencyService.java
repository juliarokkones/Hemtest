package com.example.wordfrequencyapicgi.service;

import com.example.wordfrequencyapicgi.object.WordFrequency;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

// Service-klass som bestämmer hur ord ska räknas
@Service
public class WordFrequencyService {
    // Metod för att räkna ord som kallas på av controller-klassen
    // Använder sig av andra metoder för att dela upp texten i ord, räkna antalet ord och sortera ord
    public ResponseEntity<List<WordFrequency>> countWordFrequencies(String text) {
        List<String> words = tokenizeText(text);
        Map<String, Integer> wordCounts = countWords(words);
        List<WordFrequency> wordFrequencies = sortWordFrequencies(wordCounts);
        return ResponseEntity.ok(wordFrequencies);
    }

    // Metod för att dela upp texten i ord, ta bort onödiga tecken,
    // konvertera till gemener och filtrera bort tomma strängar
    private List<String> tokenizeText(String text) {
        return Arrays.stream(text.split("\\s+"))
                .map(word -> word.replaceAll("\\W", "").toLowerCase())
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
    }

    // Metod för att räkna antalet ord
    private Map<String, Integer> countWords(List<String> words) {
        return words.stream()
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
    }

    // Metod för att sortera ord efter frekvens
    // Returnerar de 10 mest frekventa orden i en lista
    // tillsammans med frekvensen, med den högsta frekvensen först
    private List<WordFrequency> sortWordFrequencies(Map<String, Integer> wordCounts) {
        return wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

}
