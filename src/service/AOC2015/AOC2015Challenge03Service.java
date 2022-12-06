package service.AOC2015;

import service.AOCService;

import java.util.HashSet;
import java.util.Set;

public class AOC2015Challenge03Service implements AOCService {

    private final String inputString;

    public AOC2015Challenge03Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() {
        int x = 0;
        int y = 0;
        Set<String> passedByCoordinates = new HashSet<>();
        passedByCoordinates.add("" + x + "," + y);
        for (char movement : inputString.toCharArray()) {
            switch (movement) {
                case '^' -> y++;
                case 'v' -> y--;
                case '>' -> x++;
                case '<' -> x--;
            }
            passedByCoordinates.add("" + x + "," + y);
        }
        return String.valueOf(passedByCoordinates.size());
    }

    @Override
    public String solvePartTwo() {
        int xSanta = 0;
        int ySanta = 0;
        int xRoboSanta = 0;
        int yRoboSanta = 0;
        int turn = 1;
        Set<String> passedByCoordinates = new HashSet<>();
        passedByCoordinates.add("" + xSanta + "," + ySanta);
        for (char movement : inputString.toCharArray()) {
            if (turn == 1) {
                switch (movement) {
                    case '^' -> ySanta++;
                    case 'v' -> ySanta--;
                    case '>' -> xSanta++;
                    case '<' -> xSanta--;
                }
                passedByCoordinates.add("" + xSanta + "," + ySanta);
            }
            if (turn == -1) {
                switch (movement) {
                    case '^' -> yRoboSanta++;
                    case 'v' -> yRoboSanta--;
                    case '>' -> xRoboSanta++;
                    case '<' -> xRoboSanta--;
                }
                passedByCoordinates.add("" + xRoboSanta + "," + yRoboSanta);
            }
            turn = turn * -1;
        }
        return String.valueOf(passedByCoordinates.size());
    }
}
