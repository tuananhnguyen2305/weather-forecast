package com.nta.cms.helper;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    private static final String PASSWORD_ALLOW = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL_CHARACTERS;

    private static final Integer PASSWORD_LENGTH = 12;
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword() {
        if (PASSWORD_LENGTH < 1) {
            throw new IllegalArgumentException("Độ dài mật khẩu phải lớn hơn 0.");
        }

        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(PASSWORD_ALLOW.length());
            password.append(PASSWORD_ALLOW.charAt(randomIndex));
        }

        return password.toString();
    }
}
