package com.brunolpw.deckofcards.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunolpw.deckofcards.models.Hand;

public interface HandRepository extends JpaRepository<Hand, UUID> {

}
