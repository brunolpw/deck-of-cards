package com.brunolpw.deckofcards.dtos;

import jakarta.annotation.Nonnull;

public record DeckDto(
    @Nonnull
    String deckId,
    int remaining,
    boolean success,
    boolean shuffled
) {
}
