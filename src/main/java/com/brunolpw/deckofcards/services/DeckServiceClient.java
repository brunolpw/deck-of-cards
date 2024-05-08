package com.brunolpw.deckofcards.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface DeckServiceClient {

    @GetMapping("/new")
    String getNewDeck();
}
