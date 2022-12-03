package service.AOC2022;

import service.AOCService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AOC2022Challenge01Service implements AOCService {

    @Override
    public String solvePartOne(String input) {
        int maxCalories = Integer.MIN_VALUE;
        int currentIterationCalories = 0;
        for (String totalCalories : input.split("\n\n")) {
            for (String caloriesPerElf : totalCalories.split("\n")) {
                currentIterationCalories += Integer.parseInt(caloriesPerElf);
            }
            if (currentIterationCalories > maxCalories)
                maxCalories = currentIterationCalories;
            currentIterationCalories = 0;
        }
        return String.format("The maximum calories an elf is carrying is %d", maxCalories);
    }

    @Override
    public String solvePartTwo(String input) {
        int top3ElfCalories = 0;
        List<Integer> totalCaloriesPerElfList = new ArrayList<>();
        int currentIterationCalories = 0;
        for (String totalCalories : input.split("\n\n")) {
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
        return String.format("The top three elf calories-carrying elves are carrying a total of " +
                "%d calories.", top3ElfCalories);
    }

}
