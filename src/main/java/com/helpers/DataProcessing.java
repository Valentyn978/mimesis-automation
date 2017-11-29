package com.helpers;

import java.util.Random;

public class DataProcessing {

    public static String getRandomString(Integer length) {
        Random ran = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(ran.nextInt(characters.length()));
        }
        return new String(text);
    }
}
