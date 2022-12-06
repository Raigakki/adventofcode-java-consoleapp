package service.AOC2022;

import service.AOCService;

import java.util.HashSet;
import java.util.Set;

public class AOC2022Challenge06Service implements AOCService {

    @Override
    public String solvePartOne(String input) {
        String marker = "";
        for (int i = 0; i < input.length() - 4; i++) {
            String preMarker = input.substring(i, i+4);
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
    public String solvePartTwo(String input) {
        String marker = "";
        for (int i = 0; i < input.length() - 14; i++) {
            String preMarker = input.substring(i, i+14);
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
