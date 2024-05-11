package com.brunolpw.deckofcards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brunolpw.deckofcards.models.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, String> {
}
