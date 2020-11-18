package main.utils;

public class Password {

    final static int ENCRYPT_VAL = 3;

    public static String encryptPassword(String password) {

        char passwordChar[] = password.toCharArray();

        for(int i = 0; i < passwordChar.length; i++){
            passwordChar[i] = (char) (passwordChar[i] + ENCRYPT_VAL);
        }

        return buildString(passwordChar);
    }

    public static String decryptPassword(String encryptedPassword) {

        char passwordChar[] = encryptedPassword.toCharArray();

        for(int i = 0; i < passwordChar.length; i++){
            passwordChar[i] = (char) (passwordChar[i] - (ENCRYPT_VAL * 2));
        }

        return buildString(passwordChar);
    }

    /*
     * this method checks if the password submitted is valid
     * @param return true if valid
     */
    public static boolean comparePasswords(String unencryptedPassword, String encryptedPassword){

        System.out.println(unencryptedPassword);
        System.out.println(decryptPassword(encryptedPassword));

        return (decryptPassword(encryptedPassword).compareTo(unencryptedPassword) == 0);
    }

    private static String buildString(char[] password){
            StringBuilder stringBuilder = new StringBuilder();
            for(char c : password){
                stringBuilder.append(c);
            }
            return stringBuilder.toString();
    }


}
