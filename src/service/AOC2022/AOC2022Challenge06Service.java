package service.AOC2022;

import service.AOCService;

import java.util.HashSet;
import java.util.Set;

public class AOC2022Challenge06Service implements AOCService {

    private final String inputString;

    public AOC2022Challenge06Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() {
        String marker = "";
        for (int i = 0; i < inputString.length() - 4; i++) {
            String preMarker = inputString.substring(i, i+4);
            Set<Character> characterSet = new HashSet<>();
            for (char c : preMarker.toCharArray()) {
                characterSet.add(c);
            }
            if (characterSet.size() == 4) {
                marker = String.valueOf(i + 4);
                break;
            }
            characterSet.clear();
        }
        return marker;
    }

    @Override
    public String solvePartTwo() {
        String marker = "";
        for (int i = 0; i < inputString.length() - 14; i++) {
            String preMarker = inputString.substring(i, i+14);
            Set<Character> characterSet = new HashSet<>();
            for (char c : preMarker.toCharArray()) {
                characterSet.add(c);
            }
            if (characterSet.size() == 14) {
                marker = String.valueOf(i + 14);
                break;
            }
            characterSet.clear();
        }
        return marker;
    }

}
