package com.brunolpw.deckofcards;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.test.context.ActiveProfiles;

import com.brunolpw.deckofcards.services.DeckServiceClient;
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
			
	private DeckServiceClient deckServiceClient;

	private void builderFeignClient(MockClient mockClient) {
		deckServiceClient = Feign.builder()
				.client(mockClient)
				.contract(new SpringMvcContract())
				.target(DeckServiceClient.class, BASE_URL);
	}

	@Test
	public void whenGetClientReturnOk() {
		builderFeignClient(new MockClient().ok(
				HttpMethod.GET,
				BASE_URL.concat("/new"),
				RESPONSE_DECK));

		String json = deckServiceClient.getNewDeck();

		assertFalse(json.isEmpty());
	}
}
