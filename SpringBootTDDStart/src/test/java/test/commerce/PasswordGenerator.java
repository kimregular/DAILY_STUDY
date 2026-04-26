package test.commerce;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword() {
        var mixture = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            mixture.append((char) random.nextInt('A', 'Z' + 1));
            mixture.append((char) random.nextInt('0', '9' + 1));
            mixture.append((char) random.nextInt('a', 'z' + 1));
        }
        return "password" + mixture;
    }
}
