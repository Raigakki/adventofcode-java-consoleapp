package repository;

import model.AOCChallenge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AOCAvailableChallengeFileRepository implements AOCAvailableChallengeRepository {

    private static final String RESOURCES_PATH = "./resources";

    public List<AOCChallenge> getAOCChallengeList() {
        List<AOCChallenge> aocChallengeList = new ArrayList<>();
        final File folder = new File(RESOURCES_PATH);
        for (File directoriesEntry : Objects.requireNonNull(folder.listFiles())) {
            String year = directoriesEntry.getName().substring(3);
            for (File fileEntry : Objects.requireNonNull(directoriesEntry.listFiles())) {
                String challenge = fileEntry.getName().substring(1,3);
                aocChallengeList.add(new AOCChallenge(year, challenge));
            }
        }
        return aocChallengeList;
    }
}
