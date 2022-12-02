package consoleview;

import java.util.List;

public class ConsoleViewRendering {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printWelcome() {
        String displayMessage =
                """
                #################################################################
                ######    WELCOME TO ADVENT OF CODE CHALLENGE RESOLUTOR    ######
                #################################################################
                """;
        System.out.println(displayMessage);
    }

    public static void printAvailableYears(List<String> availableYearsList) {
        String availableYearString = "";
        for (String year : availableYearsList)
            availableYearString += String.format("|| %s ||\n", year);
        availableYearString = availableYearString.substring(0, availableYearString.length() - 1);
        String displayMessage = String.format(
                """
                Here are the available year of the AOC Challenge this app can solve:
                
                ##########
                %s
                ##########
                
                Please type the year you want the challenge to be solved:""",
                availableYearString);
        System.out.println(displayMessage);
    }

    public static void printAvailableChallenges(List<String> availableChallengeList) {
        String availableChallengesString = "";
        String borderLine = "";
        String innerLine = "";
        for (String challenge : availableChallengeList)
            availableChallengesString += String.format("%s - ", challenge);
        availableChallengesString = availableChallengesString.substring(0, availableChallengesString.length() - 3);
        innerLine = String.format("|| %s ||", availableChallengesString);
        for (int i = 0; i < innerLine.length(); i++)
            borderLine += "#";
        String displayMessage = String.format(
                """
                Here are the available challenges of the selected year this app can solve:
                
                %s
                %s
                %s
                
                Please type the challenge you want to be solved:
                """,
                borderLine,
                innerLine,
                borderLine);
        System.out.println(displayMessage);
    }

    public static void printAvailableParts(List<String> availableChallengePartList) {
        String availableChallengesPartString = "";
        if (availableChallengePartList.contains("01"))
            availableChallengesPartString += "|| PART ONE ||\n";
        if (availableChallengePartList.contains("02"))
            availableChallengesPartString += "|| PART TWO ||\n";
        availableChallengesPartString = availableChallengesPartString.substring(0, availableChallengesPartString.length() - 1);
        String displayMessage = String.format(
                """
                Here are the available parts of the selected challenge :
                
                ##############
                %s
                ##############
                             
                Please insert whether you want to solve Part One (Type "1") or Part Two (Type "2").
                """,
                availableChallengesPartString);
        System.out.println(displayMessage);
    }
}
