package com.example.UrlShortener.Utility;

import java.security.SecureRandom;

import org.apache.commons.validator.routines.UrlValidator;

public class Utility {
    public static String generateShortCode()
    {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder shortCode = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            char ch = (char)(secureRandom.nextInt(26) + 'a');
            shortCode.append(ch);
        }
        return shortCode.toString();
    }

    public static boolean isValidURL(String URL)
    {
        String[] schemes = {"http", "https"};
        UrlValidator validator = new UrlValidator(schemes);
        return validator.isValid(URL);
    }
}
