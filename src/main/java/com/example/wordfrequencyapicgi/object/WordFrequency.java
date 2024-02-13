package com.example.wordfrequencyapicgi.object;

// Objektsklass för att hålla ord och dess frekvens
public class WordFrequency {
    // Instansvariabler
    private String word;
    private int frequency;

    // Konstruktor
    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    // Getters och setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
