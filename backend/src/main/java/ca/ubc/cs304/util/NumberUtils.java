package ca.ubc.cs304.util;

import java.util.Random;

public class NumberUtils {
    // Generates a random number between 0 (inclusive) and 1 billion
    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(1_000_000_000);
    }
}
