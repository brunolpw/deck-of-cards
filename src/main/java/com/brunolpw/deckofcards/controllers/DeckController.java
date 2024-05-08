package com.brunolpw.deckofcards.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunolpw.deckofcards.models.Deck;
import com.brunolpw.deckofcards.services.DeckService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/deck")
public class DeckController {
    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping
    public ResponseEntity<Deck> getDeck() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(deckService.getNewDeck());
    }
    
}
