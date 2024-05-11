package com.brunolpw.deckofcards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brunolpw.deckofcards.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {

}
