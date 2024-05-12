package com.brunolpw.deckofcards.dtos;

import java.util.List;
import java.util.UUID;

public record GamePlay(
        UUID gameId,
        String deckId,
        List<HandGame> hands,
        List<HandGame> winner
) {

}
