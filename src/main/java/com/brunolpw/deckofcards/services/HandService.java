package com.brunolpw.deckofcards.services;

import org.springframework.stereotype.Service;

import com.brunolpw.deckofcards.dtos.CardDto;
import com.brunolpw.deckofcards.dtos.HandDto;
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
    public Hand createHand(String deckId, int count) {
        var json = _deckOfCardsServiceClient.createHand(deckId, count);

        if (json == null) {
            return null;
        }

        if (_deckService.remainingCards(deckId, count) == null) {
            return null;
        }

        var dto = Utils.convertJsonToObject(json, HandDto.class);
        var hand = _handRepository.save(new Hand(dto));

        dto.cards().forEach(card -> {
            _cardService.createCard(new CardDto(card.code(), card.value(), card.suit()));
        });

        return hand;
    }
}
