package service.AOC2022;

import exception.AOCException;
import service.AOCService;

public class AOC2022Challenge02Service implements AOCService {

    @Override
    public String solvePartOne(String input) throws AOCException {
        String[] matches = input.split("\n");
        int score = 0;
        for (String match : matches) {
            score += this.duelv1(match.charAt(0), match.charAt(2));
        }
        return String.format("Your total score is: %d", score);
    }

    @Override
    public String solvePartTwo(String input) throws AOCException {
        String[] matches = input.split("\n");
        int score = 0;
        for (String match : matches) {
            score += this.duelv2(match.charAt(0), match.charAt(2));
        }
        return String.format("Your total score is: %d", score);
    }

    private int duelv1(char player1move, char player2move) throws AOCException {
        int score = 0;
        switch (player2move) {
            case 'Y' -> {
                score = score + 2;
                if (player1move == 'A') score = score + 6;
                if (player1move == 'B') score = score + 3;
            }
            case 'X' -> {
                score = score + 1;
                if (player1move == 'A') score = score + 3;
                if (player1move == 'C') score = score + 6;
            }
            case 'Z' -> {
                score = score + 3;
                if (player1move == 'B') score = score + 6;
                if (player1move == 'C') score = score + 3;
            }
            default -> throw new AOCException("ERROR IN PARSING ELF MOVES");
        }
        return score;
    }

    private int duelv2(char player1move, char player2move) throws AOCException {
        int score = 0;
        switch (player2move) {
            case 'Y' -> { // DRAW
                score = score + 3;
                if (player1move == 'A') score = score + 1;
                if (player1move == 'B') score = score + 2;
                if (player1move == 'C') score = score + 3;
            }
            case 'X' -> { // LOSE
                if (player1move == 'A') score = score + 3;
                if (player1move == 'B') score = score + 1;
                if (player1move == 'C') score = score + 2;
            }
            case 'Z' -> { // WIN
                score = score + 6;
                if (player1move == 'A') score = score + 2;
                if (player1move == 'B') score = score + 3;
                if (player1move == 'C') score = score + 1;
            }
            default -> throw new AOCException("ERROR IN PARSING ELF MOVES");
        }
        return score;
    }

}
