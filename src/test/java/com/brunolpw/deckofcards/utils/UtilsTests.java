package com.brunolpw.deckofcards.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.brunolpw.deckofcards.models.Deck;
import com.google.gson.JsonSyntaxException;

@SpringBootTest
public class UtilsTests {
    
    @Test
    public void testConvertJsonToObjectSuccess() {
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
    public void testConvertJsonToObjectError() {
        String invalidJson = "{invalid_json_format}";
        Class<Deck> targetClass = Deck.class;

        assertThrows(JsonSyntaxException.class, () -> Utils.convertJsonToObject(invalidJson, targetClass));
    }

    @Test
    public void testConvertCardValueValid() {
        String value = "QUEEN";
        int expectedValue = 12;

        int convertedValue = Utils.convertCardValue(value);

        assertEquals(expectedValue, convertedValue);
    }

    // @Test
    // public void testConvertCardValueInvalid() {
    //     String invalidValue = "invalid_card_value";

    //     // Escolha o comportamento esperado para valor inválido:
    //     // Option 1: Retorna valor padrão (0)
    //     // assertEquals(0, Utils.convertCardValue(invalidValue));

    //     // Option 2: Lança exceção (ajuste a exceção esperada)
    //     // expected = IllegalArgumentException.class;
    //     assertThrows(IllegalArgumentException.class, () -> Utils.convertCardValue(invalidValue));
    // }
}
