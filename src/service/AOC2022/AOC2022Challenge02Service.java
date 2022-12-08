package service.AOC2022;

import exception.AOCException;
import service.AOCService;
import utils.StaticUtils;

import java.util.List;

public class AOC2022Challenge02Service implements AOCService {

    private final String inputString;

    public AOC2022Challenge02Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() throws AOCException {
        List<String> inputList = StaticUtils.fromInputStringToStringList(inputString, "\n");
        int score = 0;
        for (String match : inputList) {
            score += this.duelv1(match.charAt(0), match.charAt(2));
        }
        return String.valueOf(score);
    }

    @Override
    public String solvePartTwo() throws AOCException {
        List<String> inputList = StaticUtils.fromInputStringToStringList(inputString, "\n");
        int score = 0;
        for (String match : inputList) {
            score += this.duelv2(match.charAt(0), match.charAt(2));
        }
        return String.valueOf(score);
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
