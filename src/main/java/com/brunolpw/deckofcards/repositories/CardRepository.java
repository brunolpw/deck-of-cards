package com.brunolpw.deckofcards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunolpw.deckofcards.models.Card;

public interface CardRepository extends JpaRepository<Card, String> {

}
