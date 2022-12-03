import consoleview.ConsoleViewRendering;
import exception.AOCException;
import model.AOCChallenge;
import repository.AOCFileRepository;
import repository.AOCRepository;
import service.AOCService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AOCRunner implements Runnable {

    private static final String EMAIL_ADDRESS = "maltagliatiandrea@gmail.com";
    private static final String SERVICE_PATH_PATTERN = "service.AOC%s.AOC%sChallenge%sService";
    private static final String RESOURCES_PATH_PATTERN = "./resources/AOC%s/E%s.txt";

    private final Scanner keyboardScanner = new Scanner(System.in);
    private final AOCRepository aocRepository = new AOCFileRepository();

    private String yearInput = "";
    private String challengeInput = "";
    private String challengePartInput = "";

    boolean continueFlg = true;
    String errMsg = "";

    public void run() {
        ConsoleViewRendering.printWelcome();
        while (continueFlg) {
            this.selectChallenge();
            if (continueFlg)
                this.solveInputChallenge(challengePartInput);
            this.askForAnotherSolution();
        }
    }

    private void selectChallenge() {
        List<AOCChallenge> aocChallengeList = aocRepository.getAOCChallengeList();
        try {
            // AVAILABLE YEARS
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

            // AVAILABLE CHALLENGES
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

            // AVAILABLE PARTS
            List<String> availableSelectedChallengePartList = new ArrayList<>();
            aocChallengeList
                    .stream()
                    .filter(aocChallenge -> aocChallenge.year().equals(yearInput))
                    .filter(aocChallenge -> aocChallenge.challengeNumber().equals(challengeInput))
                    .forEach(aocChallenge -> availableSelectedChallengePartList.add(aocChallenge.challengePart()));
            ConsoleViewRendering.printAvailableParts(availableSelectedChallengePartList);
            challengePartInput = keyboardScanner.nextLine();
            if (challengePartInput.length() == 1)
                challengePartInput = "0" + challengePartInput;
            if (!availableSelectedChallengePartList.contains(challengePartInput)) {
                errMsg = "Inserted an illegal challenge part. Please check you input, it must be an available part.";
                throw new AOCException(errMsg);
            }
            continueFlg = true;

        } catch (AOCException e) {
            System.out.println(e.getMessage());
            continueFlg = false;
        }
    }

    private void solveInputChallenge(String challengePartInput) {
        // TODO REFACTOR SPOSTANDO A UNA CLASSE/METODO LA LETTURA FILE (CON THROW DI 1 ECC)
        String response = "";
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
            if (challengePartInput.equals("1") || challengePartInput.equals("01"))
                response = aocService.solvePartOne(challengeInstructions.toString());
            else if (challengePartInput.equals("2") || challengePartInput.equals("02"))
                response = aocService.solvePartTwo(challengeInstructions.toString());
            ConsoleViewRendering.printResponse(response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error in reading file. Please check input file.");
        }
    }

    private void askForAnotherSolution() {
        // TODO REFACTOR CONDIZIONI USCITA + VIEW
        System.out.println("Do you want to solve another Challenge?");
        String inputContinue = keyboardScanner.nextLine();
        if (inputContinue.equalsIgnoreCase("yes") ||
            inputContinue.equalsIgnoreCase("y") ||
            inputContinue.equalsIgnoreCase("continue") ||
            inputContinue.equalsIgnoreCase("another")) {
            continueFlg = true;
        } else {
            System.out.println("THANKS FOR TRYING THIS SIMPLE JAVA CONSOLE APP!\n" +
                    "IF YOU HAVE ANY KIND OF ADVICE PLEASE WRITE ME AT " + EMAIL_ADDRESS);
            continueFlg = false;
        }
    }

}
