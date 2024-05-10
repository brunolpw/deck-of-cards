package com.brunolpw.deckofcards.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.brunolpw.deckofcards.dtos.CardDto;
import com.brunolpw.deckofcards.models.Card;
import com.brunolpw.deckofcards.models.Hand;

@SpringBootTest
@ActiveProfiles("test")
public class CardTests {

    @Test
    public void testEmptyConstructor() {
        Card card = new Card();
        
        assertNull(card.getCode());
        assertEquals(0, card.getValue());
        assertNull(card.getSuit());
        assertNull(card.getHand());
    }

    @Test
    public void testConstructorWithParameters() {
        String code = "AS";
        int value = 1;
        String suit = "SPADES";
        Hand hand = new Hand(UUID.randomUUID(), true, "sample_deck_123");

        Card card = new Card(code, value, suit, hand);

        assertEquals(code, card.getCode());
        assertEquals(value, card.getValue());
        assertEquals(suit, card.getSuit());
        assertEquals(hand, card.getHand());
    }

    @Test
    public void testConstructorWithCardDtoAndNullHand() {
        String code = "KC";
        String value = "KING";
        String suit = "CLUBS";
        Hand hand = null;

        CardDto dto = new CardDto(code, value, suit, hand);
        Card card = new Card(dto);

        assertEquals(code, card.getCode());
        assertEquals(13, card.getValue());
        assertEquals(suit, card.getSuit());
        assertNull(card.getHand());
    }

    @Test
    public void testConstructorWithCardDtoAndHand() {
        String code = "KC";
        String value = "KING";
        String suit = "CLUBS";
        Hand hand = new Hand(UUID.randomUUID(), true, "sample_deck_123");

        CardDto dto = new CardDto(code, value, suit, hand);
        Card card = new Card(dto);

        assertEquals(code, card.getCode());
        assertEquals(13, card.getValue());
        assertEquals(suit, card.getSuit());
        assertNotNull(card.getHand());
    }

    @Test
    public void testGetConvertedValue() {
        String code = "QC";
        String value = "QUEEN";
        String suit = "CLUBS";
        Hand hand = null;

        CardDto dto = new CardDto(code, value, suit, hand);
        Card card = new Card(dto);

        int expectedValue = 12;

        assertEquals(expectedValue, card.getValue());
    }

    @Test
    public void testToString() {
        String code = "JS";
        int value = 11;
        String suit = "SPADES";
        Hand hand = new Hand(UUID.randomUUID(), true, "sample_deck_123");
        Card card = new Card(code, value, suit, hand);

        String expectedString = "Card [code=" + code + ", value=" + value + ", suit=" + suit + ", hand=" + hand + "]";
        
        assertEquals(expectedString, card.toString());
    }
}
