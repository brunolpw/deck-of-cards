package com.brunolpw.deckofcards.models;

import com.google.gson.annotations.SerializedName;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "decks")
public class Deck {
    @Id
    @SerializedName("deck_id")
    private String deckId;
    private int remaining;
    private boolean success;
    private boolean shuffled;
    
    public Deck() {
    }

    public Deck(int remaining, boolean success, boolean shuffled) {
        this.remaining = remaining;
        this.success = success;
        this.shuffled = shuffled;
    }

    public Deck(String deckId, int remaining, boolean success, boolean shuffled) {
        this.deckId = deckId;
        this.remaining = remaining;
        this.success = success;
        this.shuffled = shuffled;
    }

    public String getDeckId() {
        return deckId;
    }

    public int getRemaining() {
        return remaining;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    @Override
    public String toString() {
        return "Deck [deckId=" + deckId + ", remainingCards=" + remaining + ", success=" + success + ", shuffled="
                + shuffled + "]";
    }
}
