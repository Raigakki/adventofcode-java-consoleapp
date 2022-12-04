package service.AOC2022;

import service.AOCService;

public class AOC2022Challenge04Service implements AOCService {

    @Override
    public String solvePartOne(String input) {
        String[] sectionsRangesPairs = input.split("\n");
        int numPairTotallyOverlap = 0;
        for (String sectionsRangesPair : sectionsRangesPairs) { // sectionsRangesPair = 14-98,14-14
            String[] sectionsRanges = sectionsRangesPair.split(","); // ARRAY WITH 2 POSITION
            String[] sectionStartSectionEndA = sectionsRanges[0].split("-");
            String[] sectionStartSectionEndB = sectionsRanges[1].split("-");
            int sectionStartA = Integer.parseInt(sectionStartSectionEndA[0]);
            int sectionEndA = Integer.parseInt(sectionStartSectionEndA[1]);
            int sectionStartB = Integer.parseInt(sectionStartSectionEndB[0]);
            int sectionEndB = Integer.parseInt(sectionStartSectionEndB[1]);
            numPairTotallyOverlap += isTotallyOverlapped(sectionStartA, sectionEndA, sectionStartB, sectionEndB) ? 1 : 0;
            }
        return String.format("THERE ARE A TOTAL OF %d PAIRS WHICH SECTIONS " +
                "TOTALLY OVERLAPS WITH EACH OTHER.", numPairTotallyOverlap);
    }

    @Override
    public String solvePartTwo(String input) {
        String[] sectionsRangesPairs = input.split("\n");
        int numPairTotallyOverlap = 0;
        for (String sectionsRangesPair : sectionsRangesPairs) { // sectionsRangesPair = 14-98,14-14
            String[] sectionsRanges = sectionsRangesPair.split(","); // ARRAY WITH 2 POSITION
            String[] sectionStartSectionEndA = sectionsRanges[0].split("-");
            String[] sectionStartSectionEndB = sectionsRanges[1].split("-");
            int sectionStartA = Integer.parseInt(sectionStartSectionEndA[0]);
            int sectionEndA = Integer.parseInt(sectionStartSectionEndA[1]);
            int sectionStartB = Integer.parseInt(sectionStartSectionEndB[0]);
            int sectionEndB = Integer.parseInt(sectionStartSectionEndB[1]);
            numPairTotallyOverlap += isOverlapped(sectionStartA, sectionEndA, sectionStartB, sectionEndB) ? 1 : 0;
        }
        return String.format("THERE ARE A TOTAL OF %d PAIRS THAT OVERLAPS " +
                "FOR AT LEAST A SECTION WITH EACH OTHER.", numPairTotallyOverlap);

    }

    private boolean isTotallyOverlapped(int startA, int endA, int startB, int endB) {
        if (startA >= startB && endA <= endB)
            return true;
        if (startB >= startA && endB <= endA)
            return true;
        return false;
    }

    private boolean isOverlapped(int startA, int endA, int startB, int endB) {
        if (startA >= startB && startA <= endB)
            return true;
        if (startB >= startA && startB <= endA)
            return true;
        return false;
    }

}
