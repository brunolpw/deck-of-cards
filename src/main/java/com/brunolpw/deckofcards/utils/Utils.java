package com.brunolpw.deckofcards.utils;

import com.google.gson.Gson;

public abstract class Utils {
    public static <T> T convertJsonToObject(String json, Class<T> targetClass) {
        var gson = new Gson();
        var object = gson.fromJson(json, targetClass);

        return object;
    }
}
