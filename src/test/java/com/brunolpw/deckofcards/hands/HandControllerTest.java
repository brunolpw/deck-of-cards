package com.brunolpw.deckofcards.hands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
    public void testGetAllHandsEmptyList() throws Exception {
        List<Hand> hands = List.of();
        
        HandService mockService = Mockito.mock(HandService.class);

        Mockito.when(mockService.getAll()).thenReturn(Collections.emptyList());
        HandController controller = new HandController(mockService);

        ResponseEntity<List<Hand>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(hands, response.getBody());
    }
    
    @Test
    public void testGetAllHandsByGameIdSuccess() throws Exception {
        UUID expectedGameId = UUID.randomUUID();
        List<Hand> expectedHands = List.of(new Hand(), new Hand());
    
        HandService mockService = Mockito.mock(HandService.class);

        Mockito.when(mockService.getAllByGameId(expectedGameId)).thenReturn(expectedHands);
        HandController controller = new HandController(mockService);

        ResponseEntity<List<Hand>> response = controller.getAllByGameId(expectedGameId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedHands, response.getBody());
    }
    

}
