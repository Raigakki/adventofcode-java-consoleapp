package service.AOC2015;

import service.AOCService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AOC2015Challenge06Service implements AOCService {

    @Override
    public String solvePartOne(String input) {
        String[] instructionList = input.split("\n");

        // WE CAN SEE THE GRID OF 1000X1000 LIGHT AS A MATRIX
        // A 1000X1000 MATRIX IS AN ARRAY[1000] OF ARRAY[1000]
        // WHERE VALUE = 1 MEANS "ON", AND VALUE = 0 MEANS "OFF"
        int[][] lightGrid = new int[1000][];
        for (int i = 0; i < 1000; i++) {
            lightGrid[i] = new int[1000];
        }

        // I CREATE A PATTERN IN ORDER TO EASILY EXTRACT DATA I NEED, USING GROUPS MATCHER
        Pattern pattern = Pattern.compile("(turn off|turn on|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)");

        try {
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
                                case "turn on" -> lightGrid[i][j] = 1;
                                case "turn off" -> lightGrid[i][j] = 0;
                                case "toggle" -> lightGrid[i][j] = lightGrid[i][j] == 1 ? 0 : 1;
                                default -> throw new Exception("Error in interpreting instructions.");
                            }
                        }
                    }
                }
            }

            // CALCULATING TOTAL NUMBERS OF LIGHTS ON
            int lightsOnSum = 0;
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    lightsOnSum += lightGrid[i][j];
                }
            }

            return String.format("THE TOTAL NUMBER OF LIGHTS TURNED ON IS %d.", lightsOnSum);

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public String solvePartTwo(String input) {
        String[] instructionList = input.split("\n");

        int[][] lightGrid = new int[1000][];
        for (int i = 0; i < 1000; i++) {
            lightGrid[i] = new int[1000];
        }

        Pattern pattern = Pattern.compile("(turn off|turn on|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)");

        try {
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
                                case "turn on" -> lightGrid[i][j] += 1;
                                case "turn off" -> lightGrid[i][j] += lightGrid[i][j] > 0 ? -1 : 0;
                                case "toggle" -> lightGrid[i][j] += 2;
                                default -> throw new RuntimeException("ERROR IN INTERPRETING LIGHTS INSTRUCTIONS.");
                            }
                        }
                    }
                }
            }

            // CALCULATING TOTAL BRIGHTNESS
            int brightnessSum = 0;
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    brightnessSum += lightGrid[i][j];
                }
            }

            return String.format("THE TOTAL BRIGHTNESS OF THE LIGHTS IS %d.", brightnessSum);

        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
