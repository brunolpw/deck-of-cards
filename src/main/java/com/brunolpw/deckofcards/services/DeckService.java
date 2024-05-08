package com.brunolpw.deckofcards.services;

import java.util.List;

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

        if (json == null) {
            return null;
        }

        var deck = convertJsonToDeck(json);

        _deckRepository.save(deck);

        return deck;
    }
    
    @Transactional
    public List<Deck> getAllDecks() {
        return _deckRepository.findAll();
    }
    
    @Transactional
    public Deck shufflDeck(String deckId) {
        var json = _deckServiceClient.shuffleDeck(deckId);

        if (json == null) {
            return null;
        }
        
        var deck = convertJsonToDeck(json);

        _deckRepository.save(deck);

        return deck;
    }
    
    private Deck convertJsonToDeck(String json) {
        var gson = new Gson();
        var deck = gson.fromJson(json, Deck.class);
        
        return deck;
    }
}
