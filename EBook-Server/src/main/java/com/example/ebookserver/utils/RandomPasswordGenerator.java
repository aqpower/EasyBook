package com.example.ebookserver.utils;

import java.util.Random;

public class RandomPasswordGenerator {
    public static String generatePassword(int length) {
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String allChars = capitalLetters + smallLetters + numbers ;

        Random random = new Random();
        char[] password = new char[length];

        // 随机选择一个字符集，并从中随机选择一个字符添加到密码中
        for (int i = 0; i < length; i++) {
            String charSet = allChars;
            int charSetIndex = random.nextInt(4);
            if (charSetIndex == 0) {
                charSet = capitalLetters;
            } else if (charSetIndex == 1) {
                charSet = smallLetters;
            } else if (charSetIndex == 2) {
                charSet = numbers;
            }
            int charIndex = random.nextInt(charSet.length());
            password[i] = charSet.charAt(charIndex);
        }
        return new String(password);
    }
}

