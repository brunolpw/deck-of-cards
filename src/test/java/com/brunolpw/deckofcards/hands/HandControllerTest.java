package com.brunolpw.deckofcards.hands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import com.brunolpw.deckofcards.controllers.HandController;
import com.brunolpw.deckofcards.models.Hand;
import com.brunolpw.deckofcards.services.HandService;

@SpringBootTest
@ActiveProfiles("test")
public class HandControllerTest {

    @Test
    public void testGetHandsSuccess() throws Exception {
        String deckId = "existing_deck_id";
        int count = 2;
        Hand expectedHand = new Hand(true, deckId);

        HandService mockService = Mockito.mock(HandService.class);
        
        Mockito.when(mockService.createHand(deckId, count)).thenReturn(expectedHand);
        HandController controller = new HandController(mockService);

        ResponseEntity<Hand> response = controller.createHand(deckId, count);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedHand, response.getBody());
    }
}
