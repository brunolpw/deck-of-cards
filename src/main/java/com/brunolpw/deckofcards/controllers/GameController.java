package com.brunolpw.deckofcards.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunolpw.deckofcards.dtos.GamePlay;
import com.brunolpw.deckofcards.dtos.GameToPlay;
import com.brunolpw.deckofcards.models.Game;
import com.brunolpw.deckofcards.services.GameService;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService _gameService;
    
    public GameController(GameService gameService) {
        this._gameService = gameService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Game>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_gameService.getAllGames());
    }

    @PostMapping("/new")
    public ResponseEntity<GamePlay> playGame(@RequestBody GameToPlay play) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_gameService.playGame(play));
    }
    
}
