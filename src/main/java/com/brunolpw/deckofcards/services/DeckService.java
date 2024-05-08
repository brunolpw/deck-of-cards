package com.brunolpw.deckofcards.services;

import org.springframework.stereotype.Service;

import com.brunolpw.deckofcards.models.Deck;
import com.brunolpw.deckofcards.repositories.DeckRepository;
import com.google.gson.Gson;

import jakarta.transaction.Transactional;

@Service
public class DeckService {
    private final DeckServiceClient _deckServiceClient;
    private final DeckRepository _deckRepository;

    public DeckService(DeckServiceClient deckServiceClient, DeckRepository deckRepository) {
        _deckServiceClient = deckServiceClient;
        _deckRepository = deckRepository;
    }

    @Transactional
    public Deck getNewDeck() {
        var json = _deckServiceClient.getNewDeck();

        if (json == null) { return null; }

        var gson = new Gson();
        var deck = gson.fromJson(json, Deck.class);

        _deckRepository.save(deck);

        return deck;
    }
}
