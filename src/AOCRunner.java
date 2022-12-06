import consoleview.ConsoleViewRendering;
import exception.AOCException;
import model.AOCChallenge;
import repository.AOCAvailableChallengeFileRepository;
import repository.AOCAvailableChallengeRepository;
import service.AOCService;
import utils.StaticUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AOCRunner implements Runnable {


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
        try {
            AOCService aocService = StaticUtils.getAOCService(yearInput, challengeInput);
            String responsePartOne = aocService.solvePartOne();
            String responsePartTwo = aocService.solvePartTwo();
            ConsoleViewRendering.printResponse(responsePartOne, responsePartTwo);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            errMsg = "ERROR IN CREATING INSTANCE OF SERVICE CLASS: " + e.getMessage();
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
