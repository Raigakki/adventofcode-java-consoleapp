package service.AOC2022;

import service.AOCService;
import utils.StaticUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AOC2022Challenge01Service implements AOCService {

    private final String inputString;

    public AOC2022Challenge01Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() {
        List<String> inputList = StaticUtils.fromInputToStringList(inputString, "\n\n");
        int maxCalories = Integer.MIN_VALUE;
        int currentIterationCalories = 0;
        for (String totalCalories : inputList) {
            for (String caloriesPerElf : totalCalories.split("\n")) {
                currentIterationCalories += Integer.parseInt(caloriesPerElf);
            }
            if (currentIterationCalories > maxCalories)
                maxCalories = currentIterationCalories;
            currentIterationCalories = 0;
        }
        return String.valueOf(maxCalories);
    }

    @Override
    public String solvePartTwo() {
        List<String> inputList = StaticUtils.fromInputToStringList(inputString, "\n\n");
        int top3ElfCalories = 0;
        List<Integer> totalCaloriesPerElfList = new ArrayList<>();
        int currentIterationCalories = 0;
        for (String totalCalories : inputList) {
            for (String caloriesPerElf : totalCalories.split("\n")) {
                currentIterationCalories += Integer.parseInt(caloriesPerElf);
            }
            totalCaloriesPerElfList.add(currentIterationCalories);
            currentIterationCalories = 0;
        }
        totalCaloriesPerElfList = totalCaloriesPerElfList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for (int i = 0; i < 3; i++)
            top3ElfCalories += totalCaloriesPerElfList.get(i);
        return String.valueOf(top3ElfCalories);
    }

}
