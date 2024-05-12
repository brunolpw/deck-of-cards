package com.brunolpw.deckofcards.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunolpw.deckofcards.models.Card;
import com.brunolpw.deckofcards.services.CardService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService _cardService;

    public CardController(CardService cardService) {
        _cardService = cardService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Card>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_cardService.getAll());
    }
}