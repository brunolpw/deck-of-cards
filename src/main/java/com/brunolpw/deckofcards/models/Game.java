package com.brunolpw.deckofcards.models;

import java.util.List;
import java.util.UUID;

import com.brunolpw.deckofcards.dtos.GameDto;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID gameId;

    @Nonnull
    private String deckId;

    @OneToMany(mappedBy = "game")
    private List<Hand> hands;

    public Game() {
    }

    public Game(UUID gameId, String deckId) {
        this.gameId = gameId;
        this.deckId = deckId;
    }

    public Game(GameDto dto) {
        this.gameId = dto.gameId();
        this.deckId = dto.deckId();
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getDeckId() {
        return deckId;
    }

    @Override
    public String toString() {
        return "Game [gameId=" + gameId + ", deckId=" + deckId + "]";
    }
}
