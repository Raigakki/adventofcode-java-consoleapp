package service.AOC2015;

import service.AOCService;
import utils.StaticUtils;

import java.util.List;
import java.util.Set;

public class AOC2015Challenge05Service implements AOCService {

    private final String inputString;

    private static final Set<Character> VOWELS_LOWERCASE = Set.of('a','e','i','o','u');
    private static final Set<String> BLACKLISTED_STRINGS = Set.of("ab", "cd", "pq", "xy");

    public AOC2015Challenge05Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() {
        List<String> inputList = StaticUtils.fromInputStringToStringList(inputString, "\n");
        long niceStringCount = inputList.stream()
                .filter(this::vowelsCheck)
                .filter(this::twiceInARowCheck)
                .filter(this::blacklistedStringCheck)
                .count();
        return String.valueOf(niceStringCount);
    }

    @Override
    public String solvePartTwo() {
        List<String> inputList = StaticUtils.fromInputStringToStringList(inputString, "\n");
        long niceStringCount = inputList.stream()
                .filter(this::doublePairNotOverlappingCheck)
                .filter(this::letterRepeatsWithCharInBetweenCheck)
                .count();
        return String.valueOf(niceStringCount);
    }

    private boolean vowelsCheck(String stringToCheck) {
        int vowelsCount = 0;
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (VOWELS_LOWERCASE.contains(stringToCheck.charAt(i)))
                vowelsCount++;
        }
        return vowelsCount >= 3;
    }

    private boolean twiceInARowCheck(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length() - 1; i++) {
            if (stringToCheck.charAt(i) == stringToCheck.charAt(i + 1))
                return true;
        }
        return false;
    }

    private boolean blacklistedStringCheck(String stringToCheck) {
        for (String blacklistedString : BLACKLISTED_STRINGS) {
            if (stringToCheck.contains(blacklistedString))
                return false;
        }
        return true;
    }

    private boolean doublePairNotOverlappingCheck(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length() - 3; i++) {
            for (int j = i + 2; j < stringToCheck.length() - 1; j++) {
                    if (stringToCheck.substring(i, i+2).equals(stringToCheck.substring(j, j+2)))
                        return true;
                }
            }
        return false;
    }

    private boolean letterRepeatsWithCharInBetweenCheck(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length() - 2; i++) {
            if (stringToCheck.charAt(i) == stringToCheck.charAt(i+2))
                return true;
        }
        return false;
    }

}
