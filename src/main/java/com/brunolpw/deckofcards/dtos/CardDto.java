package com.brunolpw.deckofcards.dtos;

import com.brunolpw.deckofcards.models.Hand;

import jakarta.annotation.Nonnull;

public record CardDto(
    @Nonnull
    String code,
    String value,
    String suit,
    Hand hand
) {

}
