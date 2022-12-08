package service.AOC2022;

import service.AOCService;
import utils.StaticUtils;

import java.util.List;

public class AOC2022Challenge04Service implements AOCService {

    private final String inputString;

    public AOC2022Challenge04Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() {
        List<String> inputList = StaticUtils.fromInputStringToStringList(inputString, "\n");
        int numPairTotallyOverlap = 0;
        for (String sectionsRangesPair : inputList) { // sectionsRangesPair = 14-98,14-14
            String[] sectionsRanges = sectionsRangesPair.split(","); // ARRAY WITH 2 POSITION
            String[] sectionStartSectionEndA = sectionsRanges[0].split("-");
            String[] sectionStartSectionEndB = sectionsRanges[1].split("-");
            int sectionStartA = Integer.parseInt(sectionStartSectionEndA[0]);
            int sectionEndA = Integer.parseInt(sectionStartSectionEndA[1]);
            int sectionStartB = Integer.parseInt(sectionStartSectionEndB[0]);
            int sectionEndB = Integer.parseInt(sectionStartSectionEndB[1]);
            numPairTotallyOverlap += isTotallyOverlapped(sectionStartA, sectionEndA, sectionStartB, sectionEndB) ? 1 : 0;
            }
        return String.valueOf(numPairTotallyOverlap);
    }

    @Override
    public String solvePartTwo() {
        List<String> inputList = StaticUtils.fromInputStringToStringList(inputString, "\n");
        int numPairTotallyOverlap = 0;
        for (String sectionsRangesPair : inputList) { // sectionsRangesPair = 14-98,14-14
            String[] sectionsRanges = sectionsRangesPair.split(","); // ARRAY WITH 2 POSITION
            String[] sectionStartSectionEndA = sectionsRanges[0].split("-");
            String[] sectionStartSectionEndB = sectionsRanges[1].split("-");
            int sectionStartA = Integer.parseInt(sectionStartSectionEndA[0]);
            int sectionEndA = Integer.parseInt(sectionStartSectionEndA[1]);
            int sectionStartB = Integer.parseInt(sectionStartSectionEndB[0]);
            int sectionEndB = Integer.parseInt(sectionStartSectionEndB[1]);
            numPairTotallyOverlap += isOverlapped(sectionStartA, sectionEndA, sectionStartB, sectionEndB) ? 1 : 0;
        }
        return String.valueOf(numPairTotallyOverlap);

    }

    private boolean isTotallyOverlapped(int startA, int endA, int startB, int endB) {
        if (startA >= startB && endA <= endB)
            return true;
        return startB >= startA && endB <= endA;
    }

    private boolean isOverlapped(int startA, int endA, int startB, int endB) {
        if (startA >= startB && startA <= endB)
            return true;
        return startB >= startA && startB <= endA;
    }

}
