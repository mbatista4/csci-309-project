package main.utils;

/*
 * this class provides basic encryption/decryption support for Manager Registration/Login
 */
public class Password {

    final static int ENCRYPT_VAL = 3;

    /*
     * Encrypts password for protection
     */
    public static String encryptPassword(String password) {

        char[] passwordChar = password.toCharArray();

        for (int i = 0; i < passwordChar.length; i++) {
            passwordChar[i] = (char) (passwordChar[i] + ENCRYPT_VAL);
        }
        return buildString(passwordChar);
    }

    /*
     * Decrypts password in order to compare 2 passwords
     */
    public static String decryptPassword(String encryptedPassword) {

        char[] passwordChar = encryptedPassword.toCharArray();

        for (int i = 0; i < passwordChar.length; i++) {
            passwordChar[i] = (char) (passwordChar[i] - (ENCRYPT_VAL));
        }

        return buildString(passwordChar);
    }


    /*
     * this method checks if the password submitted is valid
     * @param return true if valid
     */
    public static boolean comparePasswords(String unencryptedPassword, String encryptedPassword) {
        return (decryptPassword(encryptedPassword).compareTo(unencryptedPassword) == 0);
    }

    /*
     * Helper method is used to build password back to a String
     */
    private static String buildString(char[] password) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : password) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}