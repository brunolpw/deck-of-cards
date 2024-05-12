package com.brunolpw.deckofcards.models;

import java.io.Serializable;
import java.util.Hashtable;

import com.brunolpw.deckofcards.dtos.CardDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card implements Serializable {
    @Id
    private String code;
    private int value;
    private String suit;

    public Card() {
    }

    public Card(String code, int value, String suit) {
        this.code = code;
        this.value = value;
        this.suit = suit;
    }
    
    public Card(CardDto dto) {
        this.code = dto.code();
        this.value = convertCardValue(dto.value());
        this.suit = dto.suit();
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

    @Override
    public String toString() {
        return "Card [code=" + code + ", value=" + value + ", suit=" + suit + "]";
    }

    private int convertCardValue(String value) {
        var values = new Hashtable<>();
        values.put("ACE", 1);
        values.put("2", 2);
        values.put("3", 3);
        values.put("4", 4);
        values.put("5", 5);
        values.put("6", 6);
        values.put("7", 7);
        values.put("8", 8);
        values.put("9", 9);
        values.put("10", 10);
        values.put("JACK", 11);
        values.put("QUEEN", 12);
        values.put("KING", 13);

        return (int) values.get(value);
    }
}
