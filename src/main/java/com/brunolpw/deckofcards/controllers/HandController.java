package com.brunolpw.deckofcards.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brunolpw.deckofcards.models.Hand;
import com.brunolpw.deckofcards.services.HandService;

@RestController
@RequestMapping("/hands")
public class HandController {
    private final HandService _handService;

    public HandController(HandService handService) {
        this._handService = handService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Hand>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_handService.getAll());
    }

    @GetMapping("/getAllByGameId")
    public ResponseEntity<List<Hand>> getAllByGameId(@RequestParam UUID gameId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_handService.getAllByGameId(gameId));
    }
}
