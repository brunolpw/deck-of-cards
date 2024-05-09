package com.brunolpw.deckofcards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.brunolpw.deckofcards.models.Deck;

@SpringBootTest
@ActiveProfiles("test")
public class DeckTests {
    
    @Test
	public void testEmptyConstructor() {
		Deck deck = new Deck();
		assertEquals(deck.getRemaining(), 0);
		assertFalse(deck.isSuccess());
		assertFalse(deck.isShuffled());
	}

	@Test
	public void testGetDeckId() {
		String expectedId = "abc123";
		Deck deck = new Deck(expectedId, 10, true, true);
		assertEquals(expectedId, deck.getDeckId());
	}

	@Test
	public void testToString() {
		Deck deck = new Deck("def456", 52, true, false);
		String expectedString = "Deck [deckId=def456, remainingCards=52, success=true, shuffled=false]";
		assertEquals(expectedString, deck.toString());
	}
}
