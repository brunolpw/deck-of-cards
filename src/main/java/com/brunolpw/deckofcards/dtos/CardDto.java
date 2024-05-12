package com.brunolpw.deckofcards.dtos;

import jakarta.annotation.Nonnull;

public record CardDto(
    @Nonnull
    String code,
    String value,
    String suit
) {

}
