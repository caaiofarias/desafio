package com.desafio.rest.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomToken {
    public static void main(String[] args) {

    }

    public static String generateString() {
        final int NUMBER_TOKEN_CHARS = 32;
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[NUMBER_TOKEN_CHARS];
        secureRandom.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
    }
}