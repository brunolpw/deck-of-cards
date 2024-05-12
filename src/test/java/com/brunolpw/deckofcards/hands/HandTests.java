package com.brunolpw.deckofcards.hands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.brunolpw.deckofcards.dtos.CardDto;
import com.brunolpw.deckofcards.dtos.HandDto;
import com.brunolpw.deckofcards.models.Game;
import com.brunolpw.deckofcards.models.Hand;

@SpringBootTest
@ActiveProfiles("test")
public class HandTests {

    @Test
    public void testConstructorWithUuidAndData() {
        UUID expectedHandId = UUID.randomUUID();
        boolean expectedSuccess = true;
        String expectedDeckId = "existing_deck_id";

        Hand hand = new Hand(expectedHandId, expectedSuccess, expectedDeckId, null);

        assertEquals(expectedHandId, hand.getHandId());
        assertEquals(expectedSuccess, hand.isSuccess());
        assertEquals(expectedDeckId, hand.getDeckId());
    }

    @Test
    public void testConstructorWithHandDto() {
        UUID expectedHandId = UUID.randomUUID();
        boolean expectedSuccess = true;
        String expectedDeckId = "existing_deck_id";
        List<CardDto> expectedCards = new ArrayList<CardDto>();

        HandDto dto = new HandDto(expectedHandId, expectedSuccess, expectedDeckId, expectedCards, null);
        Hand hand = new Hand(dto);

        assertEquals(expectedHandId, hand.getHandId());
        assertEquals(expectedSuccess, hand.isSuccess());
        assertEquals(expectedDeckId, hand.getDeckId());
    }

    @Test
    public void testGetters() {
        UUID handId = UUID.randomUUID();
        boolean success = true;
        String deckId = "existing_deck_id";

        Hand hand = new Hand(handId, success, deckId, null);

        assertEquals(handId, hand.getHandId());
        assertEquals(success, hand.isSuccess());
        assertEquals(deckId, hand.getDeckId());
    }

    @Test
    public void testToString() {
        UUID handId = UUID.randomUUID();
        boolean success = true;
        String deckId = "existing_deck_id";
        List<String> cardCodes = List.of("2S", "QH", "KD", "AC");
        Game game = new Game();

        Hand hand = new Hand(handId, success, deckId, game);
        hand.setCardCodes(cardCodes);

        String expectedString = "Hand [handId=" + handId + ", success=" + success + ", deckId=" + deckId + ", cardCodes=" + cardCodes + ", game=" + game + "]";
        assertEquals(expectedString, hand.toString());
    }
}
