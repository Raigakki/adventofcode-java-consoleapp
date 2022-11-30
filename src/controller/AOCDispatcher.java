package controller;

import model.AOCYearChallenge;
import repository.AOCInMemoryRepository;
import repository.AOCRepository;
import service.AOCService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AOCDispatcher {

    private final String EMAIL_ADDRESS = "maltagliatiandrea@gmail.com";
    private final Scanner keyboardScanner = new Scanner(System.in);
    private final AOCRepository aocRepository = new AOCInMemoryRepository();

    private AOCService aocService;
    private String yearInput = "";
    private String challengeInput = "";
    private String challengePartInput = "";

    public void run() {
        boolean proceedFlag;
        do {
            proceedFlag = this.selectChallenge();
            if (proceedFlag)
                proceedFlag = this.getAOCService(yearInput, challengeInput);
            if (proceedFlag)
                this.solveInputChallenge(challengePartInput);
            proceedFlag = this.askForAnotherRound();
        } while (proceedFlag);
    }

    private boolean selectChallenge() {
        List<AOCYearChallenge> aocYearChallengeList = aocRepository.getAOCChallengeList();
        try {
            // MANAGING AVAILABLE YEARS
            StringBuilder availableYearsSB = new StringBuilder();
            String separator = ", ";
            for (AOCYearChallenge aocYearChallenge : aocYearChallengeList) {
                availableYearsSB.append(aocYearChallenge.year());
                availableYearsSB.append(separator);
            }
            availableYearsSB = new StringBuilder(availableYearsSB.substring(0, availableYearsSB.length() - separator.length()));
            System.out.printf("""
                    Here is a list of all available year of the AOC Challenge:
                    %s
                    Please insert the AOC year challenge you want to solve:
                    """, availableYearsSB);
            yearInput = keyboardScanner.nextLine();
            aocYearChallengeList = aocYearChallengeList.stream()
                    .filter(aocYearChallenge -> aocYearChallenge.year().equals(yearInput))
                    .collect(Collectors.toList());
            if (aocYearChallengeList.isEmpty())
                throw new Exception();

            // TODO MANAGING CHALLENGE SOLUTION
            System.out.printf(
                    "Please insert the number of the %s's challenge you want to solve:\n", yearInput);
            challengeInput = keyboardScanner.nextLine();

            // TODO MANAGING CHALL PART
            System.out.println("Please insert whether you want to solve Part 1 or Part 2.\n" +
                    "Type \"1\" for Part 1, or \"2\" for Part 2");
            challengePartInput = keyboardScanner.nextLine();
            return true;
        } catch (Exception e) {
            if (yearInput.equals("") || aocYearChallengeList.isEmpty()) {
                System.out.println("Inserted an illegal Year. Please check you input, " +
                        "must be an available year.");
                return false;
            }
            if (challengeInput.equals("")) {
                System.out.println("Inserted an illegal Challenge Number. Please check you input validity, " +
                        "must be between 1 and 15 and needs to be available.");
                return false;
            }
            if (challengePartInput.equals("")) {
                System.out.println("Inserted an illegal Challenge Part. Please check you input, " +
                        "must be either 1 or 2 and needs to be available.");
                return false;
            }
            System.out.println("Generic runtime error. Please check code.");
            return false;
        }
    }

    private boolean getAOCService(String yearInput, String challengeInput) {
        try {
            if (challengeInput.length() == 1)
                challengeInput = "0" + challengeInput;
            Class<?> serviceClass =
                    Class.forName(String.format("service.AOC%s.AOC%sChallenge%sService", yearInput, yearInput, challengeInput));
            aocService = (AOCService) serviceClass.getDeclaredConstructor().newInstance();
            System.out.println("Classe creata correttamente");
            return true;
        } catch (ClassNotFoundException e) {
            // TODO LOGGARE ERRORE
            return false;
        } catch (InstantiationException e) {
            // TODO LOGGARE ERRORE
            return false;
        } catch (IllegalAccessException e) {
            // TODO LOGGARE ERRORE
            return false;
        } catch (InvocationTargetException e) {
            // TODO LOGGARE ERRORE
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            // TODO LOGGARE ERRORE
            throw new RuntimeException(e);
        }
    }

    private void solveInputChallenge(String challengePartInput) {
        if (challengePartInput.equals("1") || challengePartInput.equals("01"))
            aocService.solvePartOne();
        else if (challengePartInput.equals("2") || challengePartInput.equals("02"))
            aocService.solvePartTwo();
    }

    private boolean askForAnotherRound() {
        System.out.println("Do you want to solve another Challenge?");
        String inputContinue = keyboardScanner.nextLine();
        if (inputContinue.equalsIgnoreCase("yes") ||
            inputContinue.equalsIgnoreCase("y") ||
            inputContinue.equalsIgnoreCase("continue") ||
            inputContinue.equalsIgnoreCase("another")) {
            return true;
        } else {
            System.out.println("Thank you for trying this simple Java console app!\n" +
                    "If you have any kind of advice please write me at " + this.EMAIL_ADDRESS);
            return false;
        }
    }
}
