package com.brunolpw.deckofcards.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DeckOfCardsController {

    @GetMapping
    public ResponseEntity<String> getApp() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Deck of cards");
    }

}
