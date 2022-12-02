package service.AOC2015;

import service.AOCService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AOC2015Challenge06Service implements AOCService {

    // TODO

    @Override
    public void solvePartOne(String input) {
        {
            // A 1000X1000 MATRIX IS AN ARRAY[1000] OF ARRAY[1000]
            int[][] lightGrid = new int[1000][];
            for (int i = 0; i < 1000; i++) {
                lightGrid[i] = new int[1000];
            }

            String[] instructionList = ((String)input).split("\n");

            // CREO UN PATTERN IN MODO DA ESTRAPOLARMI COMODAMENTE I DATI CHE MI SERVONO CON I GRUPPI DEL MATCHER
            Pattern pattern = Pattern.compile("(turn off|turn on|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)");

            for (String instruction : instructionList) {
                Matcher matcher = pattern.matcher(instruction);
                if (matcher.matches()) {
                    int x = Integer.parseInt(matcher.group(2));
                    int y = Integer.parseInt(matcher.group(3));
                    int x2 = Integer.parseInt(matcher.group(4));
                    int y2 = Integer.parseInt(matcher.group(5));

                    for (int i = x; i <= x2; i++) {
                        for (int j = y; j <= y2; j++) {
                            switch (matcher.group(1)) {
                                case "turn on":
                                    lightGrid[i][j] = 1;
                                    break;
                                case "turn off":
                                    lightGrid[i][j] = 0;
                                    break;
                                case "toggle":
                                    lightGrid[i][j] = lightGrid[i][j] == 1 ? 0 : 1;
                                    break;
                                default:
                                    throw new RuntimeException("Errore nell'interpretazione delle istruzioni");
                            }
                        }
                    }
                }
            }

            int lightsOnSum = 0;
            // CALCOLO L'INTENSITA' TOTALE
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    lightsOnSum += lightGrid[i][j];
                }
            }

            //return lightsOnSum;

        }

    }

    @Override
    public void solvePartTwo(String input) {
        {
            // CREO LA MATRICE 1000X1000
            int[][] grid = new int[1000][];
            for (int i = 0; i < 1000; i++) {
                grid[i] = new int[1000];
            }

            String[] instructionList = ((String)input).split("\n");

            // CREO UN PATTERN IN MODO DA ESTRAPOLARMI COMODAMENTE I DATI CHE MI SERVONO CON I GRUPPI DEL MATCHER
            Pattern pattern = Pattern.compile("(turn off|turn on|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)");

            for (String instruction : instructionList) {
                Matcher matcher = pattern.matcher(instruction);
                if (matcher.matches()) {
                    int x = Integer.parseInt(matcher.group(2));
                    int y = Integer.parseInt(matcher.group(3));
                    int x2 = Integer.parseInt(matcher.group(4));
                    int y2 = Integer.parseInt(matcher.group(5));

                    for (int i = x; i <= x2; i++) {
                        for (int j = y; j <= y2; j++) {
                            switch (matcher.group(1)) {
                                case "turn on":
                                    grid[i][j] += 1;
                                    break;
                                case "turn off":
                                    grid[i][j] += grid[i][j] > 0 ? -1 : 0;
                                    break;
                                case "toggle":
                                    grid[i][j] += 2;
                                    break;
                                default:
                                    throw new RuntimeException("Errore nell'interpretazione delle istruzioni");
                            }
                        }
                    }
                }
            }

            int brightnessSum = 0;
            // CALCOLO L'INTENSITA' TOTALE
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    brightnessSum += grid[i][j];
                }
            }

            //return brightnessSum;

        }
    }
}
