package utils;

import service.AOCService;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StaticUtils {

    private static final String RESOURCES_PATH_PATTERN = "./resources/AOC%s/E%s.txt";
    private static final String SERVICE_PATH_PATTERN = "service.AOC%s.AOC%sChallenge%sService";

    public static List<String> fromInputStringToStringList(String inputString, String splitter) {
        return new ArrayList<>(Arrays.asList(inputString.split(splitter)));
    }

    public static AOCService getAOCService(String yearInput, String challengeInput)
            throws ClassNotFoundException, NoSuchMethodException, FileNotFoundException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> serviceClass =
                Class.forName(String.format(
                        SERVICE_PATH_PATTERN,
                        yearInput,
                        yearInput,
                        challengeInput));
        return (AOCService) serviceClass
                .getDeclaredConstructor(String.class)
                .newInstance(fromInputFileToString(yearInput,challengeInput));
    }

    private static String fromInputFileToString(String yearInput, String challengeInput) throws FileNotFoundException {
        StringBuilder challengeInstructions = new StringBuilder();
        File inputFile = new File(String.format(RESOURCES_PATH_PATTERN, yearInput, challengeInput));
        Scanner fileScanner = new Scanner(inputFile);
        while (fileScanner.hasNextLine()) {
            challengeInstructions.append(fileScanner.nextLine());
            if (fileScanner.hasNextLine())
                challengeInstructions.append("\n");
        }
        return challengeInstructions.toString();
    }

}
