package com.brunolpw.deckofcards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunolpw.deckofcards.models.Deck;

public interface DeckRepository extends JpaRepository<Deck, String> {
}
