package com.brunolpw.deckofcards.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import com.brunolpw.deckofcards.controllers.CardController;
import com.brunolpw.deckofcards.models.Card;
import com.brunolpw.deckofcards.services.CardService;

@SpringBootTest
@ActiveProfiles("test")
public class CardControllerTest {

    @Test
    public void testGetAllCardsEmptyList() throws Exception {
        List<Card> cards = List.of();
        CardService mockService = mock(CardService.class);

        Mockito.when(mockService.getAll()).thenReturn(Collections.emptyList());
        CardController controller = new CardController(mockService);

        ResponseEntity<List<Card>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cards, response.getBody());
    }

}
