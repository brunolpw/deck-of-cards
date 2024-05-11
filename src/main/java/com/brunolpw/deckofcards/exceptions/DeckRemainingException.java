package com.brunolpw.deckofcards.exceptions;

public class DeckRemainingException extends RuntimeException {
    
    public DeckRemainingException(String message) {
        super(message);
    }

    public DeckRemainingException(int cardsRemaining) {
        super("O número de cartas não é compativel (" + cardsRemaining + ").");

    }
    
    public DeckRemainingException() {
        super("O número de cartas não é compativel.");
        
    }
}
