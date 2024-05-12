package com.brunolpw.deckofcards.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunolpw.deckofcards.models.Deck;
import com.brunolpw.deckofcards.services.DeckService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/decks")
public class DeckController {
    private final DeckService _deckService;

    public DeckController(DeckService deckService) {
        this._deckService = deckService;
    }

    @GetMapping("/new")
    public ResponseEntity<Deck> createDeck() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(_deckService.createDeck());
    }

    @GetMapping("/newShuffled")
    public ResponseEntity<Deck> createShuffledDeck() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(_deckService.createShuffledDeck());
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<Deck>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_deckService.getAllDecks());
    }
    
    @PutMapping("/shuffle")
    public ResponseEntity<Deck> shuffleDeck(@RequestBody String deckId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_deckService.shufflDeck(deckId));
    }
}
