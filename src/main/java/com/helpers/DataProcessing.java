package com.helpers;

import org.apache.commons.lang.RandomStringUtils;
import java.util.StringTokenizer;

public class DataProcessing {

    private static final int STR_LENGTH = 90;
    private String tagS = "<br/>- %s";

    public String formatMessage(Object message) {

        if (!message.toString().contains("<a")) {

            String s = message.toString();
            StringTokenizer tok = new StringTokenizer(s, " ");
            StringBuilder output = new StringBuilder(s.length());

            while (tok.hasMoreTokens()) {
                String word = tok.nextToken();
                if (word.length() > STR_LENGTH) {
                    output.append(formatMessage(new StringBuilder(word).insert(STR_LENGTH - 1, " ").toString()));
                } else {
                    output.append(" ").append(word);
                }
            }
            return String.format(tagS, output);
        }
        return message.toString();
    }

    public static String getRandomString(Integer length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
