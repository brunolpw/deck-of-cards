package com.brunolpw.deckofcards.utils;

import java.util.Hashtable;

import com.google.gson.Gson;

public abstract class Utils {
    public static <T> T convertJsonToObject(String json, Class<T> targetClass) {
        var gson = new Gson();
        var object = gson.fromJson(json, targetClass);

        return object;
    }
    
    public static int convertCardValue(String value) {
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
