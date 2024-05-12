package com.brunolpw.deckofcards.models;

import java.util.List;
import java.util.UUID;

import com.brunolpw.deckofcards.dtos.HandDto;
import com.google.gson.annotations.SerializedName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    private List<String> cardCodes;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Hand() {
    }

    public Hand(UUID handId, boolean success, String deckId, Game game) {
        this.handId = handId;
        this.success = success;
        this.deckId = deckId;
        this.game = game;
    }

    public Hand(HandDto dto) {
        this.handId = dto.handId();
        this.success = dto.success();
        this.deckId = dto.deckId();
        this.cardCodes = dto.cardCodes();
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

    public List<String> getCardCodes() {
        return cardCodes;
    }

    public void setCardCodes(List<String> cardCodes) {
        this.cardCodes = cardCodes;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Hand [handId=" + handId + ", success=" + success + ", deckId=" + deckId + ", cardCodes=" + cardCodes + ", game=" + game + "]";
    }
    
}
