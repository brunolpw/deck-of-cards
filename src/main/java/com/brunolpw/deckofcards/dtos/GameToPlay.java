package com.brunolpw.deckofcards.dtos;

public record GameToPlay(
        String deckId,
        int countHands,
        int countCards
) {
}
