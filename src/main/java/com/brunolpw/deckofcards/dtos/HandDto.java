package com.brunolpw.deckofcards.dtos;

import java.util.List;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public record HandDto(
        UUID handId,
        boolean success,
        @SerializedName("deck_id")
        String deckId,
        List<CardDto> cards,
        List<String> cardCodes
) {

}
