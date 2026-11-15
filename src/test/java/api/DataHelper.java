package api;


import java.util.Random;

public class DataHelper {

    private static final Random RANDOM = new Random();


    public static String randomString(Integer symbolsCount) {
        String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(symbolsCount);
        for (int i = 0; i < symbolsCount; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARACTERS.length());
            sb.append(ALPHANUMERIC_CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static double generateRandomDouble() {
        return RANDOM.nextInt(10001) / 100.0;
    }

    public static int generateRandomInteger() {
        return RANDOM.nextInt(100);
    }
}
