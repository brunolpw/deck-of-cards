package com.brunolpw.deckofcards.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface DeckServiceClient {

    @GetMapping("/new")
    String getNewDeck();

    @GetMapping("{card_id}/shuffle")
    String shuffleDeck(@PathVariable("card_id") String cardId);
}
