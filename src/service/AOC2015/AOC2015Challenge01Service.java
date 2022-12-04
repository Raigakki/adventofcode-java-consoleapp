package service.AOC2015;

import service.AOCService;

public class AOC2015Challenge01Service implements AOCService {

    @Override
    public String solvePartOne(String input) {
        int floor = 0;
        char[] instructionArray = input.toCharArray();
        for (char character : instructionArray) {
            if (character == '(')
                floor = floor + 1;
            else if (character == ')')
                floor = floor - 1;
        }
        return String.format("THE FLOOR WHERE SANTA HAS TO GO IS FLOOR %d.", floor);
    }

    @Override
    public String solvePartTwo(String input) {
        int floor = 0;
        int instructionCharacterPosition = 0;
        char[] instructionArray = input.toCharArray();
        for (char character : instructionArray) {
            if (character == '(')
                floor = floor + 1;
            else if (character == ')')
                floor = floor - 1;
            instructionCharacterPosition++;
            if (floor < 0) {
                return String.format(
                        "THE FIRST CHARACTER POSITION WHEN SANTA GOES BELOW FLOOR ZERO IS %d.\n",
                        instructionCharacterPosition);
            }
        }
        return ("SANTA NEVER GOES BELOW FLOOR ZERO!");
    }
}
