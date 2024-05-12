package com.brunolpw.deckofcards.dtos;

import java.util.UUID;

public record GameDto(
    UUID gameId,
    String deckId
) {

}
