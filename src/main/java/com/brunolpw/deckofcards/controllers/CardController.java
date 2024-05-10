package com.brunolpw.deckofcards.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunolpw.deckofcards.dtos.CardDto;
import com.brunolpw.deckofcards.models.Card;
import com.brunolpw.deckofcards.services.CardService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService _cardService;

    public CardController(CardService cardService) {
        _cardService = cardService;
    }

    @PostMapping("/new")
    public ResponseEntity<Card> create(@RequestBody CardDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_cardService.createCard(dto));
    }
}