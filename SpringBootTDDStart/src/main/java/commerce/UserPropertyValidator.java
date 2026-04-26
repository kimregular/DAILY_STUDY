package commerce;

public class UserPropertyValidator {

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9_-]{3,}$";

    public static boolean isEmailValid(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public static boolean isUsernameValid(String username) {
        return username != null && username.matches(USERNAME_REGEX);
    }

    public static boolean isPasswordValid(String password) {
        return password != null
            && password.length() >= 8
            && contains4SequentialCharacters(password) == false;
    }

    private static boolean contains4SequentialCharacters(String s) {
        for (int i = 0; i < s.length() - 3; i++) {
            if (s.charAt(i) + 1 == s.charAt(i + 1) &&
                s.charAt(i + 1) + 1 == s.charAt(i + 2) &&
                s.charAt(i + 2) + 1 == s.charAt(i + 3)) {
                return true;
            }
        }

        return false;
    }
}
