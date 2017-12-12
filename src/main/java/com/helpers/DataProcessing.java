package com.helpers;

import org.apache.commons.lang.RandomStringUtils;

public class DataProcessing {

    public static String getRandomString(Integer length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
