package repository;

import model.AOCChallenge;

import java.util.List;

public interface AOCAvailableChallengeRepository {
    List<AOCChallenge> getAOCChallengeList();
}
