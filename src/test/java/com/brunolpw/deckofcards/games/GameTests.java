package com.brunolpw.deckofcards.games;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.brunolpw.deckofcards.dtos.GameDto;
import com.brunolpw.deckofcards.models.Game;

@SpringBootTest
@ActiveProfiles("test")
public class GameTests {
    @Test
    public void testGettersAndSetters() {
        UUID expectedGameId = UUID.randomUUID();
        String expectedDeckId = "test_deck_id";

        Game game = new Game(expectedGameId, expectedDeckId);

        assertEquals(expectedGameId, game.getGameId());
        assertEquals(expectedDeckId, game.getDeckId());
    }

    @Test
    public void testConstructorWithGameDto() {
        UUID expectedGameId = UUID.randomUUID();
        String expectedDeckId = "test_deck_id";

        GameDto dto = new GameDto(expectedGameId, expectedDeckId);
        Game game = new Game(dto);

        assertEquals(expectedGameId, game.getGameId());
        assertEquals(expectedDeckId, game.getDeckId());
    }

    @Test
    public void testToString() {
        UUID gameId = UUID.randomUUID();
        String deckId = "test_deck_id";

        Game game = new Game(gameId, deckId);
        String expectedString = "Game [gameId=" + gameId + ", deckId=" + deckId + "]";

        assertEquals(expectedString, game.toString());
    }

}
