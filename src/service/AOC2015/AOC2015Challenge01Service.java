package service.AOC2015;

import service.AOCService;

public class AOC2015Challenge01Service implements AOCService {

    @Override
    public void solvePartOne(String input) {
        int floor = 0;
        char[] instructionArray = input.toCharArray();
        for (char character : instructionArray) {
            if (character == '(')
                floor = floor + 1;
            else if (character == ')')
                floor = floor - 1;
        }
        System.out.println(String.format("The floor where Santa has to go is floor %d!", floor));
    }

    @Override
    public void solvePartTwo(String input) {
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
                System.out.printf(
                        "The first character position where the floor is below zero is %d!\n",
                        instructionCharacterPosition);
                break;
            }
        }
        // TODO STAMPARE SE NON VA MAI SOTTO ZERO
    }
}
