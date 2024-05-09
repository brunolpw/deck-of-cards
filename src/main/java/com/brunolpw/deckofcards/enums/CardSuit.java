package com.brunolpw.deckofcards.enums;

public enum CardSuit {
    HEARTS("H"), // ♥️
    DIAMONDS("D"), // ♦️
    CLUBS("C"), // ♣️
    SPADES("S"); // ♠

    private final String suit;

    CardSuit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
