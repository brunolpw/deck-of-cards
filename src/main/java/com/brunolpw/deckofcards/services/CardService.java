package com.brunolpw.deckofcards.services;

import org.springframework.stereotype.Service;

import com.brunolpw.deckofcards.dtos.CardDto;
import com.brunolpw.deckofcards.models.Card;
import com.brunolpw.deckofcards.repositories.CardRepository;

import jakarta.transaction.Transactional;

@Service
public class CardService {
    private final CardRepository _cardRepository;

    public CardService(CardRepository cardRepository) {
        _cardRepository = cardRepository;
    }

    @Transactional
    public Card createCard(CardDto dto) {
        var card = new Card(dto);

        return getCard(card.getCode()) != null
                ? card
                : _cardRepository.save(card);
    }

    @Transactional
    public Card getCard(String cardCode) {
        return _cardRepository.findById(cardCode).orElse(null);
    }
}
