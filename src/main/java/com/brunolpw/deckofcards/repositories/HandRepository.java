package com.brunolpw.deckofcards.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brunolpw.deckofcards.models.Hand;

import feign.Param;

@Repository
public interface HandRepository extends JpaRepository<Hand, UUID> {

    @Query(value = "SELECT * FROM hands WHERE game_id = :gameId", nativeQuery = true)
    public List<Hand> findAllByGameId(@Param("gameId") UUID gameId);

}
