package com.brunolpw.deckofcards.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brunolpw.deckofcards.exceptions.DeckRemainingException;
import com.brunolpw.deckofcards.models.Deck;
import com.brunolpw.deckofcards.repositories.DeckRepository;
import com.brunolpw.deckofcards.utils.Utils;
import jakarta.transaction.Transactional;

@Service
public class DeckService {
    private final DeckOfCardsServiceClient _deckOfCardsServiceClient;
    private final DeckRepository _deckRepository;

    public DeckService(DeckOfCardsServiceClient deckOfCardsServiceClient, DeckRepository deckRepository) {
        _deckOfCardsServiceClient = deckOfCardsServiceClient;
        _deckRepository = deckRepository;
    }

    @Transactional
    public Deck createDeck() {
        var json = _deckOfCardsServiceClient.createDeck();

        if (json == null) {
            return null;
        }

        var deck = Utils.convertJsonToObject(json, Deck.class);

        _deckRepository.save(deck);

        return deck;
    }
    
    @Transactional
    public Deck createShuffledDeck() {
        var json = _deckOfCardsServiceClient.createShuffledDeck();

        if (json == null) {
            return null;
        }

        var deck = Utils.convertJsonToObject(json, Deck.class);

        _deckRepository.save(deck);

        return deck;
    }
    
    @Transactional
    public Deck getDeck(String deckId) {
        return _deckRepository.findById(deckId).orElse(null);
    }

    @Transactional
    public List<Deck> getAllDecks() {
        return _deckRepository.findAll();
    }
    
    @Transactional
    public Deck shufflDeck(String deckId) {
        var json = _deckOfCardsServiceClient.shuffleDeck(deckId);

        if (json == null) {
            return null;
        }

        var deck = Utils.convertJsonToObject(json, Deck.class);

        _deckRepository.save(deck);

        return deck;
    }
    
    @Transactional
    public Deck updateDeck(Deck deck) {
        return _deckRepository.save(deck);
    }

    @Transactional
    public Deck remainingCards(String deckId, int count) {
        var deck = getDeck(deckId);

        if (deck == null) {
            return null;
        }

        if (deck.getRemaining() < count) {
            throw new DeckRemainingException(deck.getRemaining());
        }

        return updateDeck(new Deck(deck.getDeckId(), deck.getRemaining() - count, deck.isSuccess(), deck.isShuffled()));
    }
}
