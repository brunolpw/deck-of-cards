package com.brunolpw.deckofcards.exceptions;

public class DeckRemainingException extends RuntimeException {
    
    public DeckRemainingException(String message) {
        super(message);
    }

    public DeckRemainingException(int cardsRemaining) {
        super("O número de cartas retiradas é superior ao que existe no baralho (" + cardsRemaining + ").");

    }
    
    public DeckRemainingException() {
        super("O número de cartas retiradas é superior ao que existe no baralho.");
        
    }
}
