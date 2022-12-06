package service.AOC2015;

import service.AOCService;

public class AOC2015Challenge01Service implements AOCService {

    private final String inputString;

    public AOC2015Challenge01Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() {
        int floor = 0;
        char[] instructionArray = inputString.toCharArray();
        for (char character : instructionArray) {
            if (character == '(')
                floor = floor + 1;
            else if (character == ')')
                floor = floor - 1;
        }
        return String.valueOf(floor);
    }

    @Override
    public String solvePartTwo() {
        int floor = 0;
        int instructionCharacterPosition = 0;
        char[] instructionArray = inputString.toCharArray();
        for (char character : instructionArray) {
            if (character == '(')
                floor = floor + 1;
            else if (character == ')')
                floor = floor - 1;
            instructionCharacterPosition++;
            if (floor < 0) {
                return String.valueOf(instructionCharacterPosition);
            }
        }
        return "SANTA NEVER GOES BELOW FLOOR ZERO!";
    }

}
