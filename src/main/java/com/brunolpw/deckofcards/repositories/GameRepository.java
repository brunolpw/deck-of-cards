package com.brunolpw.deckofcards.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brunolpw.deckofcards.models.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {

}
