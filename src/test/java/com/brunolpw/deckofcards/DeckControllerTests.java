package com.brunolpw.deckofcards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.brunolpw.deckofcards.controllers.DeckController;
import com.brunolpw.deckofcards.dtos.DeckDto;
import com.brunolpw.deckofcards.models.Deck;
import com.brunolpw.deckofcards.services.DeckService;

@SpringBootTest
@ActiveProfiles("test")
public class DeckControllerTests {
    
    @Test
	public void testGetDeckSuccess() throws Exception {
		Deck mockDeck = new Deck("new_deck_id", 52, true, false);

		DeckService mockService = Mockito.mock(DeckService.class);
		Mockito.when(mockService.getNewDeck()).thenReturn(mockDeck);
		DeckController controller = new DeckController(mockService);

		ResponseEntity<Deck> response = controller.getDeck();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(mockDeck, response.getBody());
	}
	
	@Test
	public void testShuffleDeckSuccess() throws Exception {
		String deckId = "shuffle_test_deck";
		Deck mockShuffledDeck = new Deck("exemple_shuffle_test_deck", 52, true, true);

		DeckService mockService = Mockito.mock(DeckService.class);
		Mockito.when(mockService.shufflDeck(deckId)).thenReturn(mockShuffledDeck);
		DeckController controller = new DeckController(mockService);

		DeckDto deckDto = new DeckDto(deckId, 0, false, false);

		ResponseEntity<Deck> response = controller.shuffleDeck(deckDto);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().isShuffled());
	}
	
	@Test
	public void testGetAllDecksEmptyList() throws Exception {
		List<Deck> emptyList = Collections.emptyList();
		
		DeckService mockService = Mockito.mock(DeckService.class);
		Mockito.when(mockService.getAllDecks()).thenReturn(emptyList);
		DeckController controller = new DeckController(mockService);
		
		ResponseEntity<List<Deck>> response = controller.getAllDecks();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().isEmpty());
	}


}
