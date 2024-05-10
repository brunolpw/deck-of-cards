package com.brunolpw.deckofcards.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface DeckOfCardsServiceClient {

    @GetMapping("/new")
    String getNewDeck();

    @GetMapping("{card_id}/shuffle")
    String shuffleDeck(@PathVariable("card_id") String cardId);
    
    @GetMapping("{card_id}/draw/?count={count}")
    String getHands(@PathVariable("card_id") String cardId, @PathVariable("count") int count);
}
