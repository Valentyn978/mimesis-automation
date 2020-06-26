package com.helpers;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang.RandomStringUtils;
import java.util.StringTokenizer;

import static org.apache.commons.lang.StringUtils.EMPTY;

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

    public static String stringReverse(String incomingStr) {
        return stringReverse(incomingStr, EMPTY);
    }

    public static String stringReverse(String incomingStr, String outputStr) {
        Predicate<Integer> ifRecursionShouldStop = f -> f < 1;
        Function<String, Integer> lastCharNumber = c -> c.length() - 1;

        if (ifRecursionShouldStop.test(incomingStr.length())) return outputStr;

        Function<String, String> getRestOfTheString = in -> in.substring(0, lastCharNumber.apply(in));
        BinaryOperator<String> getResult = (in, out) -> out += in.substring(lastCharNumber.apply(in));

        return stringReverse(getRestOfTheString.apply(incomingStr), getResult.apply(incomingStr, outputStr));
    }
}
