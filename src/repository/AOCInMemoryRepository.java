package repository;

import model.AOCYearChallenge;

import java.util.ArrayList;
import java.util.List;

public class AOCInMemoryRepository implements AOCRepository {

    private final AOCYearChallenge aoc2015Challenge = new AOCYearChallenge(
            "2015",
            new String[]{"E01P01", "E1P02"}
    );

    public List<AOCYearChallenge> getAOCChallengeList() {
        List<AOCYearChallenge> aocYearChallengeList = new ArrayList<>();
        aocYearChallengeList.add(aoc2015Challenge);
        return aocYearChallengeList;
    }
}
