package ca.ubc.cs304.util;

import ca.ubc.cs304.database.model.PostedJob;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class Utils {
    /**
     * Generates a random number between 0 and 1,000,000,000.
     *
     * @return the random number
     */
    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(1_000_000_000);
    }

    /**
     * Parses an integer from a string. Returns null if the string is not a valid integer.
     *
     * @param s the string to parse
     * @return the integer, or null if the string is not a valid integer
     */
    public static Integer parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Parses a date from a string. Returns null if the string is not a valid date.
     *
     * @param s the string to parse
     * @return the date, or null if the string is not a valid date
     */
    public static LocalDate parseDate(String s) {
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Validates that a string is not null or blank.
     *
     * @param s the string to validate
     * @throws IllegalArgumentException if the string is null or blank
     */
    public static void validateNotBlank(String s, String fieldName) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException(fieldName + ": cannot be blank");
        }
    }

    /**
     * Validates that a PostedJob is valid.
     *
     * @param postedJob the PostedJob to validate
     * @throws IllegalArgumentException if the PostedJob is invalid
     */
    public static void validatePostedJob(PostedJob postedJob) {
        validateNotBlank(postedJob.getPosition(), "position");
        validateNotBlank(postedJob.getLocation(), "location");
        validateNotBlank(postedJob.getDescription(), "description");
    }
}
