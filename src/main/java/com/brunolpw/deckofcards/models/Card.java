package com.brunolpw.deckofcards.models;

import java.io.Serializable;

import com.brunolpw.deckofcards.dtos.CardDto;
import com.brunolpw.deckofcards.utils.Utils;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card implements Serializable {
    @Id
    private String code;
    private int value;
    private String suit;
    
    @ManyToOne
    private Hand hand;

    public Card() {
    }
    
    public Card(String code, int value, String suit, Hand hand) {
        this.code = code;
        this.value = value;
        this.suit = suit;
        this.hand = hand;
    }
    
    public Card(CardDto dto) {
        this.code = dto.code();
        this.value = Utils.convertCardValue(dto.value());
        this.suit = dto.suit();
        this.hand = dto.hand();
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return "Card [code=" + code + ", value=" + value + ", suit=" + suit + ", hand=" + hand + "]";
    }
}
