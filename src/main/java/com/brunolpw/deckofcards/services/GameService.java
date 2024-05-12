package com.brunolpw.deckofcards.services;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brunolpw.deckofcards.dtos.GameDto;
import com.brunolpw.deckofcards.dtos.GamePlay;
import com.brunolpw.deckofcards.dtos.GameToPlay;
import com.brunolpw.deckofcards.exceptions.DeckRemainingException;
import com.brunolpw.deckofcards.models.Game;
import com.brunolpw.deckofcards.repositories.GameRepository;

import jakarta.transaction.Transactional;

@Service
public class GameService {

    private final GameRepository _gameRepository;
    private final DeckService _deckService;
    private final HandService _handService;

    public GameService(GameRepository gameRepository, DeckService deckService, HandService handService) {
        this._gameRepository = gameRepository;
        this._deckService = deckService;
        this._handService = handService;
    }

    @Transactional
    public List<Game> getAllGames() {
        return _gameRepository.findAll();
    }

    @Transactional
    public GamePlay playGame(GameToPlay play) {
        if (play == null) {
            return null;
        }

        var deckId = deckExistsOrCreate(play.deckId());
        var totalCards = play.countHands() * play.countCards();

        if (!_deckService.verifyRemaining(deckId, totalCards)) {
            throw new DeckRemainingException();
        }

        var game = createGame(new GameDto(null, deckId));

        _handService.updateWithGame(play, game);

        return getResultGame(game.getGameId());
    }

    protected GamePlay getResultGame(UUID gameId) {
        var game = getGameById(gameId);
        var hands = _handService.getAllByGameId(gameId);
        var handsGame = hands.stream().map(h -> _handService.getHandWithCardsById(h.getHandId())).toList();
        var greatter = handsGame.stream().map(v -> v.value()).max(Comparator.naturalOrder()).get();
        var winners = handsGame.stream().filter(hg -> hg.value() == greatter).toList();

        var gamePlay = new GamePlay(game.getGameId(), game.getDeckId(), handsGame, winners);

        return gamePlay;
    }

    protected Game createGame(GameDto dto) {
        return _gameRepository.save(new Game(dto));
    }

    protected Game getGameById(UUID gameId) {
        return _gameRepository.findById(gameId).orElse(null);
    }

    protected Game update(GameDto dto) {
        return _gameRepository.save(new Game(dto));
    }
    
    private String deckExistsOrCreate(String deckId) {
        return _deckService.getDeck(deckId) == null 
            ? _deckService.createDeck().getDeckId()
            : deckId;
    }
    
}
