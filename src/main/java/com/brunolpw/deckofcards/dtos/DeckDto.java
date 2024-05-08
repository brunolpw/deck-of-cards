package com.brunolpw.deckofcards.dtos;

import jakarta.annotation.Nonnull;

public record DeckDto(
    @Nonnull
    String deckId,
    int remainingCards,
    boolean success,
    boolean shuffled
) {
}
