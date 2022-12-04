package consoleview;

import java.util.List;

public class ConsoleViewRendering {

    private static final String EMAIL_ADDRESS = "maltagliatiandrea@gmail.com";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void printWelcome() {
        String displayMessage =
                String.format(
                """
                %s
                #################################################################
                ######    WELCOME TO ADVENT OF CODE CHALLENGE RESOLUTOR    ######
                #################################################################%s
                """,
                ANSI_PURPLE,
                ANSI_RESET);
        System.out.println(displayMessage);
    }

    public static void printAvailableYears(List<String> availableYearsList) {
        StringBuilder availableYearString = new StringBuilder();
        for (String year : availableYearsList)
            availableYearString.append(String.format("|| YEAR %s ||\n", year));
        availableYearString = new StringBuilder(availableYearString.substring(0, availableYearString.length() - 1));
        String displayMessage = String.format(
                """
                %s###############
                %s
                ###############%s
                
                Please choose the year you want the challenge to be solved:
                """,
                ANSI_BLUE,
                availableYearString,
                ANSI_RESET);
        System.out.println(displayMessage);
    }

    public static void printAvailableChallenges(List<String> availableChallengeList) {
        StringBuilder availableChallengesString = new StringBuilder();
        for (String challenge : availableChallengeList)
            availableChallengesString.append(String.format("|| CHALLENGE %s ||\n", challenge));
        availableChallengesString = new StringBuilder(availableChallengesString.substring(0, availableChallengesString.length() - 1));
        String displayMessage = String.format(
                """
                
                %s##################
                %s
                ##################%s
                
                Please choose the challenge you want to be solved:
                """,
                ANSI_BLUE,
                availableChallengesString,
                ANSI_RESET);
        System.out.println(displayMessage);
    }

    public static void printResponse(String responsePartOne, String responsePartTwo) {
        String displayMessage = String.format(
                """
                %s#########################################################################################
                %s
                %s
                #########################################################################################%s
                """,
                ANSI_GREEN,
                responsePartOne,
                responsePartTwo,
                ANSI_RESET);
        System.out.println(displayMessage);
    }

    public static void printError(String error) {
        String displayMessage = String.format(
                """
                %s#########################################################################################
                %s
                #########################################################################################%s
                """,
                ANSI_RED,
                error,
                ANSI_RESET);
        System.out.println(displayMessage);
    }

    public static void printBye() {
        String displayMessage = String.format(
                """
                %s
                ###############################################################################
                THANKS FOR TRYING THIS SIMPLE JAVA CONSOLE APP!
                IF YOU HAVE ANY KIND OF ADVICE PLEASE WRITE ME AT %s!
                ###############################################################################
                %s
                """,
                ANSI_RED,
                EMAIL_ADDRESS,
                ANSI_RESET);
        System.out.println(displayMessage);
    }


}
