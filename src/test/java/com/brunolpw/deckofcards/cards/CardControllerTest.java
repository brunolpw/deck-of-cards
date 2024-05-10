package com.brunolpw.deckofcards.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import com.brunolpw.deckofcards.controllers.CardController;
import com.brunolpw.deckofcards.dtos.CardDto;
import com.brunolpw.deckofcards.models.Card;
import com.brunolpw.deckofcards.models.Hand;
import com.brunolpw.deckofcards.services.CardService;

@SpringBootTest
@ActiveProfiles("test")
public class CardControllerTest {

    @Test
    public void testCreateCard() throws Exception {
        String code = "AS";
        String value = "ACE";
        String suit = "SPADES";
        Hand hand = new Hand(UUID.randomUUID(), true, "sample_deck_123");

        CardDto cardDto = new CardDto(code, value, suit, hand);
        Card card = new Card(cardDto);

        CardService mockService = mock(CardService.class);

        Mockito.when(mockService.createCard(cardDto)).thenReturn(card);
        CardController controller = new CardController(mockService);

        ResponseEntity<Card> response = controller.create(cardDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(card, response.getBody());
    }
}
