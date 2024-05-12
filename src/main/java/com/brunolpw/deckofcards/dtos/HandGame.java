package com.brunolpw.deckofcards.dtos;

import java.util.List;
import java.util.UUID;

public record HandGame(
        UUID handId,
        List<String> cards,
        int value
) {

}
