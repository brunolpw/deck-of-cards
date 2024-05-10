package com.brunolpw.deckofcards.models;

import java.util.UUID;

import com.brunolpw.deckofcards.dtos.HandDto;
import com.google.gson.annotations.SerializedName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hands")
public class Hand {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID handId;
    
    private boolean success;
    
    @SerializedName("deck_id")
    private String deckId;
    
    public Hand() {
    }
    
    public Hand(boolean success, String deckId) {
        this.success = success;
        this.deckId = deckId;
    }

    public Hand(UUID handId, boolean success, String deckId) {
        this.handId = handId;
        this.success = success;
        this.deckId = deckId;
    }

    public Hand(HandDto dto) {
        this.handId = dto.handId();
        this.success = dto.success();
        this.deckId = dto.deckId();
    }

    public UUID getHandId() {
        return handId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getDeckId() {
        return deckId;
    }

    @Override
    public String toString() {
        return "Hand [handId=" + handId + ", success=" + success + ", deckId=" + deckId + "]";
    }
}
