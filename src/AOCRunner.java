import consoleview.ConsoleViewRendering;
import exception.AOCException;
import model.AOCChallenge;
import repository.AOCAvailableChallengeFileRepository;
import repository.AOCAvailableChallengeRepository;
import service.AOCService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AOCRunner implements Runnable {

    private static final String SERVICE_PATH_PATTERN = "service.AOC%s.AOC%sChallenge%sService";
    private static final String RESOURCES_PATH_PATTERN = "./resources/AOC%s/E%s.txt";

    private final Scanner keyboardScanner = new Scanner(System.in);
    private final AOCAvailableChallengeRepository aocAvailableChallengeFileRepository =
            new AOCAvailableChallengeFileRepository();

    private String yearInput = "";
    private String challengeInput = "";

    boolean continueFlg = true;
    String errMsg = "";

    public void run() {
        ConsoleViewRendering.printWelcome();
        while (continueFlg) {
            this.showAndSelectAvailableChallenges();
            if (continueFlg)
                this.solveInputChallenge();
            this.askForAnotherSolution();
        }
    }

    private void showAndSelectAvailableChallenges() {
        List<AOCChallenge> aocChallengeList = aocAvailableChallengeFileRepository.getAOCChallengeList();
        try {
            // SELECT YEAR
            List<String> availableYearsList = new ArrayList<>();
            aocChallengeList
                    .forEach(aocChallenge -> {
                        if (!availableYearsList.contains(aocChallenge.year()))
                            availableYearsList.add(aocChallenge.year());
                    });
            ConsoleViewRendering.printAvailableYears(availableYearsList);
            yearInput = keyboardScanner.nextLine();
            if (!availableYearsList.contains(yearInput)) {
                errMsg = "Inserted an illegal year. Please check you input, it must be an available year.";
                throw new AOCException(errMsg);
            }

            // SELECT CHALLENGE
            List<String> availableSelectedYearChallengeList = new ArrayList<>();
            aocChallengeList
                    .stream()
                    .filter(aocChallenge -> aocChallenge.year().equals(yearInput))
                    .forEach(aocChallenge -> {
                        if (!availableSelectedYearChallengeList.contains(aocChallenge.challengeNumber()))
                            availableSelectedYearChallengeList.add(aocChallenge.challengeNumber());
                    });
            ConsoleViewRendering.printAvailableChallenges(availableSelectedYearChallengeList);
            challengeInput = keyboardScanner.nextLine();
            if (challengeInput.length() == 1)
                challengeInput = "0" + challengeInput;
            if (!availableSelectedYearChallengeList.contains(challengeInput)) {
                errMsg = "Inserted an illegal challenge number. Please check you input, it must be an available challenge.";
                throw new AOCException(errMsg);
            }
            continueFlg = true;
        } catch (AOCException e) {
            ConsoleViewRendering.printError(e.getMessage());
            continueFlg = false;
        }
    }

    private void solveInputChallenge() {
        String responsePartOne;
        String responsePartTwo;
        try {
            Class<?> serviceClass = Class.forName(String.format(SERVICE_PATH_PATTERN,
                    yearInput, yearInput, challengeInput));
            AOCService aocService = (AOCService) serviceClass.getDeclaredConstructor().newInstance();
            StringBuilder challengeInstructions = new StringBuilder();
            File inputFile = new File(String.format(RESOURCES_PATH_PATTERN, yearInput, challengeInput));
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextLine()) {
                challengeInstructions.append(fileScanner.nextLine());
                if (fileScanner.hasNextLine())
                    challengeInstructions.append("\n");
            }
            responsePartOne = aocService.solvePartOne(challengeInstructions.toString());
            responsePartTwo = aocService.solvePartTwo(challengeInstructions.toString());
            ConsoleViewRendering.printResponse(responsePartOne, responsePartTwo);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            errMsg = "ERROR IN INSTANTIATING CLASS OF THE SOLUTION. PLEASE CHECK IF THE SERVICE CLASS IS MISSING.";
            ConsoleViewRendering.printError(errMsg);
        } catch (IOException e) {
            errMsg = "ERROR IN READING INPUT FILE. PLEASE CHECK INPUT FILE @ ./resources/AOCYYYY.txt.";
            ConsoleViewRendering.printError(errMsg);
        } catch (AOCException e) {
            errMsg = e.getMessage();
            ConsoleViewRendering.printError(errMsg);
        }
    }

    private void askForAnotherSolution() {
        System.out.println("Do you want to solve another Challenge?\n");
        String inputContinue = keyboardScanner.nextLine();
        if (inputContinue.equalsIgnoreCase("y")) {
            continueFlg = true;
        } else {
            ConsoleViewRendering.printBye();
            continueFlg = false;
        }
    }

}
