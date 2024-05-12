package com.brunolpw.deckofcards.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brunolpw.deckofcards.dtos.CardDto;
import com.brunolpw.deckofcards.dtos.GameToPlay;
import com.brunolpw.deckofcards.dtos.HandDto;
import com.brunolpw.deckofcards.dtos.HandGame;
import com.brunolpw.deckofcards.models.Game;
import com.brunolpw.deckofcards.models.Hand;
import com.brunolpw.deckofcards.repositories.HandRepository;
import com.brunolpw.deckofcards.utils.Utils;

import jakarta.transaction.Transactional;

@Service
public class HandService {
    private final DeckOfCardsServiceClient _deckOfCardsServiceClient;
    private final HandRepository _handRepository;
    private final DeckService _deckService;
    private final CardService _cardService;

    public HandService(DeckOfCardsServiceClient deckOfCardsServiceClient, HandRepository handRepository,
            DeckService deckService, CardService cardService) {
        _deckOfCardsServiceClient = deckOfCardsServiceClient;
        _handRepository = handRepository;
        _deckService = deckService;
        _cardService = cardService;
    }
    
    @Transactional
    public List<Hand> getAll() {
        return _handRepository.findAll();
    }

    @Transactional
    public List<Hand> getAllByGameId(UUID gameId) {
        return _handRepository.findAllByGameId(gameId);
    }

    protected Hand createHand(String deckId, int count, Game game) {
        var json = _deckOfCardsServiceClient.createHand(deckId, count);

        if (json == null) {
            return null;
        }

        if (_deckService.remainingCards(deckId, count) == null) {
            return null;
        }

        var dto = Utils.convertJsonToObject(json, HandDto.class);
        var hand = saveHand(dto, game);

        dto.cards().forEach(card -> {
            _cardService.createCard(new CardDto(card.code(), card.value(), card.suit()));
        });

        return hand;
    }

    protected HandGame getHandWithCardsById(UUID handId) {
        var hand = _handRepository.findById(handId).orElse(null);

        if (hand == null) {
            return null;
        }

        var totalValue = hand.getCardCodes().stream()
                .map(c -> _cardService.getCard(c))
                .mapToInt(c -> c.getValue())
                .sum();

        return new HandGame(handId, hand.getCardCodes(), totalValue);
    }
    
    protected List<Hand> updateWithGame(GameToPlay play, Game game) {
        var hands = new ArrayList<Hand>();

        for (int i = 0; i < play.countHands(); i++) {
            hands.add(createHand(game.getDeckId(), play.countCards(), game));
        }

        return hands;
    } 

    private Hand saveHand(HandDto dto, Game game) {
        var hand = new Hand(dto);
        var codes = dto.cards().stream().map(c -> c.code()).toList();
        hand.setCardCodes(codes);

        if (game != null) {
            hand.setGame(game);
        }

        return _handRepository.save(hand);
    }
}
