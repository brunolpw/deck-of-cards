package com.brunolpw.deckofcards.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import com.brunolpw.deckofcards.controllers.GameController;
import com.brunolpw.deckofcards.dtos.GamePlay;
import com.brunolpw.deckofcards.dtos.GameToPlay;
import com.brunolpw.deckofcards.dtos.HandGame;
import com.brunolpw.deckofcards.models.Game;
import com.brunolpw.deckofcards.services.GameService;

@SpringBootTest
@ActiveProfiles("test")
public class GameControllerTests {

    @Test
    public void testGetAllGamesSuccess() throws Exception {
        List<Game> expectedGames = Arrays.asList(new Game(), new Game());

        GameService mockService = Mockito.mock(GameService.class);

        Mockito.when(mockService.getAllGames()).thenReturn(expectedGames);
        GameController controller = new GameController(mockService);

        ResponseEntity<List<Game>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedGames, response.getBody());
    }
    
    @Test
    public void testGetAllGamesEmptyList() throws Exception {
        List<Game> expectedGames = Arrays.asList();

        GameService mockService = Mockito.mock(GameService.class);

        Mockito.when(mockService.getAllGames()).thenReturn(Collections.emptyList());
        GameController controller = new GameController(mockService);

        ResponseEntity<List<Game>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedGames, response.getBody());
    }

    @Test
    public void testPlayGameSuccess() throws Exception {
        GameToPlay play = new GameToPlay("existing_deck_id", 2, 3);
        GamePlay gamePlay = new GamePlay(UUID.randomUUID(), "existing_deck_id",
                List.of(
                        new HandGame(UUID.randomUUID(), List.of("2D", "2C", "3H"), 7),
                        new HandGame(UUID.randomUUID(), List.of("2D", "2C", "3H"), 7)
                ),
                Collections.emptyList());

        GameService mockService = Mockito.mock(GameService.class);

        Mockito.when(mockService.playGame(play)).thenReturn(gamePlay);
        GameController controller = new GameController(mockService);

        ResponseEntity<GamePlay> response = controller.playGame(play);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(gamePlay, response.getBody());
    }



}
