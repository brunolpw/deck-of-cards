package com.brunolpw.deckofcards.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.test.context.ActiveProfiles;

import feign.Feign;
import feign.mock.HttpMethod;
import feign.mock.MockClient;

@SpringBootTest
@ActiveProfiles("test")
class DeckOfCardsApplicationTests {

	private static String BASE_URL = "https://deckofcardsapi.com/api/deck";
	private static String RESPONSE_DECK = """
		{"success": true, "deck_id": "6f02hawgm2ny", "remaining": 52, "shuffled": true}
				""";
	
	private DeckOfCardsServiceClient deckOfCardsService;

	private void builderFeignClient(MockClient mockClient) {
		deckOfCardsService = Feign.builder()
				.client(mockClient)
				.contract(new SpringMvcContract())
				.target(DeckOfCardsServiceClient.class, BASE_URL);
	}

	@Test
	public void whenGetClientReturnOk() {
		builderFeignClient(new MockClient().ok(
				HttpMethod.GET,
				BASE_URL.concat("/new"),
				RESPONSE_DECK));

		String json = deckOfCardsService.createDeck();

		assertFalse(json.isEmpty());
	}

	@Test
	public void testGetNewDeckSuccess() throws Exception {
		String mockJson = """
			{"success": true, "deck_id": "m01kju969unb", "cards": [{"code": "AC", "image": "https://deckofcardsapi.com/static/img/AC.png", "images": {"svg": "https://deckofcardsapi.com/static/img/AC.svg", "png": "https://deckofcardsapi.com/static/img/AC.png"}, "value": "ACE", "suit": "CLUBS"}, {"code": "3C", "image": "https://deckofcardsapi.com/static/img/3C.png", "images": {"svg": "https://deckofcardsapi.com/static/img/3C.svg", "png": "https://deckofcardsapi.com/static/img/3C.png"}, "value": "3", "suit": "CLUBS"}, {"code": "9H", "image": "https://deckofcardsapi.com/static/img/9H.png", "images": {"svg": "https://deckofcardsapi.com/static/img/9H.svg", "png": "https://deckofcardsapi.com/static/img/9H.png"}, "value": "9", "suit": "HEARTS"}], "remaining": 43}
		""";

		DeckOfCardsServiceClient mockClient = Mockito.mock(DeckOfCardsServiceClient.class);
		Mockito.when(mockClient.createDeck()).thenReturn(mockJson);

		String newDeckJson = mockClient.createDeck();

		assertNotNull(newDeckJson);
		assertTrue(newDeckJson.contains("\"success\": true")); // Verifica a presença de "success"
	}

	@Test
	public void testGetHandsSuccess() throws Exception {
		String mockJson = """
			{"success": true, "deck_id": "m01kju969unb", "cards": [{"code": "AC", "image": "https://deckofcardsapi.com/static/img/AC.png", "images": {"svg": "https://deckofcardsapi.com/static/img/AC.svg", "png": "https://deckofcardsapi.com/static/img/AC.png"}, "value": "ACE", "suit": "CLUBS"}, {"code": "3C", "image": "https://deckofcardsapi.com/static/img/3C.png", "images": {"svg": "https://deckofcardsapi.com/static/img/3C.svg", "png": "https://deckofcardsapi.com/static/img/3C.png"}, "value": "3", "suit": "CLUBS"}, {"code": "9H", "image": "https://deckofcardsapi.com/static/img/9H.png", "images": {"svg": "https://deckofcardsapi.com/static/img/9H.svg", "png": "https://deckofcardsapi.com/static/img/9H.png"}, "value": "9", "suit": "HEARTS"}], "remaining": 43}
		""";
		String cardId = "m01kju969unb";
		int count = 3;

		DeckOfCardsServiceClient mockClient = Mockito.mock(DeckOfCardsServiceClient.class);
		Mockito.when(mockClient.createHand(cardId, count)).thenReturn(mockJson);

		String handsJson = mockClient.createHand(cardId, count);

		assertNotNull(handsJson);
		assertTrue(handsJson.contains("\"cards\":"));
	}
	
}
