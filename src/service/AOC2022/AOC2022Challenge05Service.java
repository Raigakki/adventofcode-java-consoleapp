package service.AOC2022;

import service.AOCService;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AOC2022Challenge05Service implements AOCService {

    @Override
    public String solvePartOne(String input) {
        Pattern pattern = Pattern.compile("((.*\\R)+.*)\\n\\n((.*\\R)+.*)");
        Matcher matcher = pattern.matcher(input);
        String crates = "";
        String[] instructionStringArray = new String[]{};
        if (matcher.matches()) {
            crates = matcher.group(1);
            instructionStringArray = matcher.group(3).split(("\n"));
        }

        // SETTING UP A LIST OF QUEUE OF CHARACTER
        crates = this.fixString(crates, "     ", " [0] ");
        crates = crates.replace("   ", "[0] ");
        crates = crates.replace(" ", "");
        crates = crates.replace("[", "");
        crates = crates.replace("]", "");
        Deque<String> horizontalCratesQueue = new ArrayDeque<>(Arrays.asList(crates.split("\n")));
        horizontalCratesQueue.removeLast();
        List<Deque<Character>> verticalCratesQueueList = new ArrayList<>();
        for (int i = 0; i < horizontalCratesQueue.getLast().length(); i++) {
            Deque<Character> verticalCratesQueue = new ArrayDeque<>();
            verticalCratesQueueList.add(verticalCratesQueue);
        }
        for (String horizontalCrates : horizontalCratesQueue) {
            char[] cratesArray = horizontalCrates.toCharArray();
            for (int i = 0; i < horizontalCrates.length(); i++) {
                if (cratesArray[i] != '0')
                    verticalCratesQueueList.get(i).add(horizontalCrates.toCharArray()[i]);
            }
        }

        // MOVING CRATES
        pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        for (String instructionString : instructionStringArray) {
            matcher = pattern.matcher(instructionString);
            if (matcher.matches()) {
                for (int i = 1; i <= Integer.parseInt(matcher.group(1)); i++) {
                    if (!verticalCratesQueueList.get(Integer.parseInt(matcher.group(2))-1).isEmpty()) {
                        char moved = verticalCratesQueueList.get(Integer.parseInt(matcher.group(2))-1).removeFirst();
                        verticalCratesQueueList.get(Integer.parseInt(matcher.group(3))-1).addFirst(moved);
                    }
                }
            }
        }
        StringBuilder response = new StringBuilder();
        for (Deque<Character> deque : verticalCratesQueueList) {
            response.append(deque.size() != 0 ? deque.getFirst() : "");
        }
        return response.toString();
    }

    @Override
    public String solvePartTwo(String input) {
        Pattern pattern = Pattern.compile("((.*\\R)+.*)\\n\\n((.*\\R)+.*)");
        Matcher matcher = pattern.matcher(input);
        String crates = "";
        String[] instructionStringArray = new String[]{};
        if (matcher.matches()) {
            crates = matcher.group(1);
            instructionStringArray = matcher.group(3).split(("\n"));
        }

        // SETTING UP A LIST OF QUEUE OF CHARACTER
        crates = this.fixString(crates, "     ", " [0] ");
        crates = crates.replace("   ", "[0] ");
        crates = crates.replace(" ", "");
        crates = crates.replace("[", "");
        crates = crates.replace("]", "");
        Deque<String> horizontalCratesQueue = new ArrayDeque<>(Arrays.asList(crates.split("\n")));
        horizontalCratesQueue.removeLast();
        List<Deque<Character>> verticalCratesQueueList = new ArrayList<>();
        for (int i = 0; i < horizontalCratesQueue.getLast().length(); i++) {
            Deque<Character> verticalCratesQueue = new ArrayDeque<>();
            verticalCratesQueueList.add(verticalCratesQueue);
        }
        for (String horizontalCrates : horizontalCratesQueue) {
            char[] cratesArray = horizontalCrates.toCharArray();
            for (int i = 0; i < horizontalCrates.length(); i++) {
                if (cratesArray[i] != '0')
                    verticalCratesQueueList.get(i).add(horizontalCrates.toCharArray()[i]);
            }
        }

        // MOVING CRATES
        pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        for (String instructionString : instructionStringArray) {
            matcher = pattern.matcher(instructionString);
            Deque<Character> movingCharDeque = new ArrayDeque<>();
            if (matcher.matches()) {
                for (int i = 1; i <= Integer.parseInt(matcher.group(1)); i++) {
                    if (!verticalCratesQueueList.get(Integer.parseInt(matcher.group(2))-1).isEmpty()) {
                        movingCharDeque.add(verticalCratesQueueList.get(Integer.parseInt(matcher.group(2))-1).removeFirst());
                    }
                }
            }
            int dequeSize = movingCharDeque.size();
            for (int i = 0; i < dequeSize; i++) {
                verticalCratesQueueList.get(Integer.parseInt(matcher.group(3))-1).addFirst(movingCharDeque.removeLast());
            }
            movingCharDeque.clear();

        }
        StringBuilder response = new StringBuilder();
        for (Deque<Character> deque : verticalCratesQueueList) {
            response.append(deque.size() != 0 ? deque.getFirst() : "");
        }
        return response.toString();
    }

    private String fixString(String crates, String check, String substitution) {
        if (crates.contains(check)) {
            crates = crates.replaceFirst(check, substitution);
            return this.fixString(crates, check, substitution);
        }
        return crates;
    }

}
