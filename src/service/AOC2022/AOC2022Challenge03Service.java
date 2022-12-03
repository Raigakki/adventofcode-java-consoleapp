package service.AOC2022;

import service.AOCService;

public class AOC2022Challenge03Service implements AOCService {

    private static final String ITEM_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public String solvePartOne(String input) {
        int sharedItemsPrioritySum = 0;
        String[] rucksacks = input.split("\n");
        String compartment1;
        String compartment2;
        for (String rucksack : rucksacks) {
            compartment1 = rucksack.substring(0, rucksack.length()/2);
            compartment2 = rucksack.substring(rucksack.length()/2);
            char sharedItem = 0;
            outerloop:
            for (int i = 0; i < compartment1.length(); i++) {
                for (int j = 0; j < compartment2.length(); j++) {
                    if (compartment1.charAt(i) == compartment2.charAt(j)) {
                        sharedItem = compartment1.charAt(i);
                        break outerloop;
                    }
                }
            }
            sharedItemsPrioritySum += ITEM_LIST.indexOf(sharedItem) + 1;
        }
        return String.format("The sum of the priorities of shared items is: %d.", sharedItemsPrioritySum);
    }

    @Override
    public String solvePartTwo(String input) {
        int badgesPrioritySum = 0;
        String[] rucksacks = input.split("\n");
        String rucksack1;
        String rucksack2;
        String rucksack3;
        for (int i = 0; i < rucksacks.length - 2; i = i+3) {
            rucksack1 = rucksacks[i];
            rucksack2 = rucksacks[i+1];
            rucksack3 = rucksacks[i+2];
            char badge = 0;
            outerloop:
            for (int j = 0; j < rucksack1.length(); j++) {
                for (int z = 0; z < rucksack2.length(); z++) {
                    if (rucksack1.charAt(j) == rucksack2.charAt(z)) {
                        for (int k = 0; k < rucksack3.length(); k++) {
                            if (rucksack1.charAt(j) == rucksack3.charAt(k)) {
                                badge = rucksack1.charAt(j);
                                break outerloop;
                            }
                        }
                    }
                }
            }
            badgesPrioritySum += ITEM_LIST.indexOf(badge) + 1;
        }
        return String.format("The sum of the priorities of badges is: %d.", badgesPrioritySum);
    }

}
