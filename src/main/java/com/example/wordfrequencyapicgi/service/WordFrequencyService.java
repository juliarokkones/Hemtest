package com.example.wordfrequencyapicgi.service;

import com.example.wordfrequencyapicgi.object.WordFrequency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

// Service-klass som bestämmer hur ord ska räknas
@Service
public class WordFrequencyService {

    // Metod för att räkna ord som kallas på av controller-klassen
    public ResponseEntity<List<WordFrequency>> countWordFrequencies(String text) {
        List<WordFrequency> wordFrequencies = calculateWordFrequencies(text);
        return new ResponseEntity<>(wordFrequencies, HttpStatus.OK);
    }

    // Metod för att räkna ord
    private List<WordFrequency> calculateWordFrequencies(String text) {
        List<String> words = tokenizeText(text);
        Map<String, Integer> wordCounts = countWords(words);
        return sortWordFrequencies(wordCounts);
    }

    // Metod för att dela upp texten i ord, ta bort onödiga tecken, konvertera till gemener
    // och filtrera bort tomma strängar
    private List<String> tokenizeText(String text) {
        return Arrays.asList(text.split("\\s+"))
                .stream()
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
    // Returnerar de 10 mest frekventa orden i en lista tillsammans med frekvensen
    // med den högsta frekvensen först
    private List<WordFrequency> sortWordFrequencies(Map<String, Integer> wordCounts) {
        return wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
