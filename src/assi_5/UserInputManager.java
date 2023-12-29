package assi_5;

import java.util.Scanner;

public class UserInputManager {

    private static Scanner scanner = new Scanner(System.in);

    public static String getStringInput(String prompt, int maxLength) {
        while (true) {
            String input = getInput(prompt);
            if (Validator.forString(input)
                    .hasLengthBetween(1, maxLength)
                    .isValid()) {
                return input;
            }
        }
    }

    public static String getStringInput(String prompt, int maxLength, String[] values) {
        while (true) {
            String input = getStringInput(prompt, maxLength);
            if (Validator.forString(input)
                    .isInValues(values)
                    .isValid()) {
                return input;
            }
        }
    }

    public static String getStringInput(String prompt) {
        return getStringInput(prompt, Integer.MAX_VALUE);
    }

    public static int getIntInput(String prompt, int minValue, int maxValue) {
        while (true) {
            int input = getIntInput(prompt);
            if (!Validator.forInt(input)
                    .isBetween(minValue, maxValue)
                    .isValid()) {
                Logging.print("Please enter a valid integer between " + minValue + " and " + maxValue + ".",
                        Logging.LogType.ERROR);
                continue;
            }
            return input;
        }
    }

    public static int getIntInput(String prompt, int minValue) {
        return getIntInput(prompt, minValue, Integer.MAX_VALUE);
    }

    public static String getInput(String prompt) {
        Logging.print(prompt);
        return scanner.nextLine();
    }

    public static int getIntInput(String prompt) {
        while (true) {
            String input = getInput(prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Logging.print("Please enter a valid integer.", Logging.LogType.ERROR);
            }
        }
    }

    public static double getDoubleInput(String prompt) {
        while (true) {
            String input = getInput(prompt);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                Logging.print("Please enter a valid double.", Logging.LogType.ERROR);
            }
        }
    }

    public static double getDoubleInput(String prompt, double minValue, double maxValue) {
        while (true) {
            double input = getDoubleInput(prompt);
            if (!Validator.forDouble(input)
                    .isBetween(minValue, maxValue)
                    .isValid()) {
                Logging.print("Please enter a valid double between " + minValue + " and " + maxValue + ".",
                        Logging.LogType.ERROR);
                continue;
            }
            return input;
        }
    }

    public static double getDoubleInput(String prompt, double minValue) {
        return getDoubleInput(prompt, minValue, Double.MAX_VALUE);
    }
}
