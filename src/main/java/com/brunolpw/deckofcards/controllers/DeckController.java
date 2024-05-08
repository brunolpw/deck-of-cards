package com.brunolpw.deckofcards.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunolpw.deckofcards.dtos.DeckDto;
import com.brunolpw.deckofcards.models.Deck;
import com.brunolpw.deckofcards.services.DeckService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/deck")
public class DeckController {
    private final DeckService _deckService;

    public DeckController(DeckService deckService) {
        this._deckService = deckService;
    }

    @GetMapping
    public ResponseEntity<Deck> getDeck() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_deckService.getNewDeck());
    }

    @GetMapping("/allDecks")
    public ResponseEntity<List<Deck>> getAllDecks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_deckService.getAllDecks());
    }
    

    @GetMapping("/shuffle")
    public ResponseEntity<Deck> shuffleDeck(DeckDto deckDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_deckService.shufflDeck(deckDto.deckId()));
    }
    
}
