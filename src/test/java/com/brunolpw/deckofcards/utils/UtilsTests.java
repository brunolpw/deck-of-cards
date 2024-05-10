package com.brunolpw.deckofcards.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.brunolpw.deckofcards.models.Deck;
import com.google.gson.JsonSyntaxException;

@SpringBootTest
@ActiveProfiles("test")
public class UtilsTests {
    
    @Test
    public void testConvertJsonToObjectSuccess() throws Exception {
        String json = """
                    {
                    "success": true,
                    "deck_id": "3p40paa87x90",
                    "shuffled": true,
                    "remaining": 52
                }
                """;
        Class<Deck> targetClass = Deck.class;

        Deck deck = Utils.convertJsonToObject(json, targetClass);

        assertNotNull(deck);
        assertTrue(deck instanceof Deck);
    }
    
    @Test
    public void testConvertJsonToObjectError() throws Exception {
        String invalidJson = "{invalid_json_format}";
        Class<Deck> targetClass = Deck.class;

        assertThrows(JsonSyntaxException.class, () -> Utils.convertJsonToObject(invalidJson, targetClass));
    }

    @Test
    public void testConvertCardValueValid() throws Exception {
        int aceValue = Utils.convertCardValue("ACE");
        int twoValue = Utils.convertCardValue("2");
        int queenValue = Utils.convertCardValue("QUEEN");

        assertEquals(1, aceValue);
        assertEquals(2, twoValue);
        assertEquals(12, queenValue);
    }
}
