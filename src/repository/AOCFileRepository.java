package repository;

import model.AOCChallenge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AOCFileRepository implements AOCRepository {

    private static final String RESOURCES_PATH_PATTERN = "./resources/AOCAvailableChallenges.txt";

    public List<AOCChallenge> getAOCChallengeList() {
        List<AOCChallenge> aocChallengeList = this.createListFromFile();
        return aocChallengeList;
    }

    private List<AOCChallenge> createListFromFile() {
        List<AOCChallenge> aocChallengeList = new ArrayList<>();
        try {
            File inputFile = new File(RESOURCES_PATH_PATTERN);
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextLine()) {
                String[] challenge = fileScanner.nextLine().split(",");
                aocChallengeList.add(new AOCChallenge(challenge[0], challenge[1], challenge[2]));
            }
        } catch (IOException e) {
            System.out.println("Error in reading file. Please check input file.");
        } catch (Exception e) {
            System.out.println("Errore in creating Challenge list. Please check input file.");
        }
        return aocChallengeList;
    }
}
