package com.brunolpw.deckofcards.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.brunolpw.deckofcards.dtos.CardDto;
import com.brunolpw.deckofcards.models.Card;

@SpringBootTest
@ActiveProfiles("test")
public class CardTests {

    @Test
    public void testEmptyConstructor() {
        Card card = new Card();
        
        assertNull(card.getCode());
        assertEquals(0, card.getValue());
        assertNull(card.getSuit());
    }

    @Test
    public void testConstructorWithParameters() {
        String code = "AS";
        int value = 1;
        String suit = "SPADES";

        Card card = new Card(code, value, suit);

        assertEquals(code, card.getCode());
        assertEquals(value, card.getValue());
        assertEquals(suit, card.getSuit());
    }

    @Test
    public void testConstructorWithCardDto() {
        String code = "KC";
        String value = "KING";
        String suit = "CLUBS";

        CardDto dto = new CardDto(code, value, suit);
        Card card = new Card(dto);

        assertEquals(code, card.getCode());
        assertEquals(13, card.getValue());
        assertEquals(suit, card.getSuit());
    }

    @Test
    public void testGetConvertedValue() {
        String code = "QC";
        String value = "QUEEN";
        String suit = "CLUBS";

        CardDto dto = new CardDto(code, value, suit);
        Card card = new Card(dto);

        int expectedValue = 12;

        assertEquals(expectedValue, card.getValue());
    }

    @Test
    public void testToString() {
        String code = "JS";
        int value = 11;
        String suit = "SPADES";
        Card card = new Card(code, value, suit);

        String expectedString = "Card [code=" + code + ", value=" + value + ", suit=" + suit + "]";

        assertEquals(expectedString, card.toString());
    }
}
