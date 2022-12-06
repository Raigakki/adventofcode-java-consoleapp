package service.AOC2015;

import service.AOCService;

public class AOC2015Challenge02Service implements AOCService {

    private final String inputString;

    public AOC2015Challenge02Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() {
        String[] presents = inputString.split("\n");
        int paperNeeded = 0;
        for (String present : presents) {
            String[] dimensionsLWH = present.split("x");
            int areaLW = Integer.parseInt(dimensionsLWH[0]) * Integer.parseInt(dimensionsLWH[1]);
            int areaLH = Integer.parseInt(dimensionsLWH[0]) * Integer.parseInt(dimensionsLWH[2]);
            int areaWH = Integer.parseInt(dimensionsLWH[1]) * Integer.parseInt(dimensionsLWH[2]);
            int smallestArea = areaLW <= areaLH ? (Math.min(areaLW, areaWH)) : (Math.min(areaLH, areaWH));
            paperNeeded += 2*areaLW + 2*areaLH + 2*areaWH + smallestArea;
        }
        return String.valueOf(paperNeeded);
    }

    @Override
    public String solvePartTwo() {
        String[] presents = inputString.split("\n");
        int ribbonNeeded = 0;
        for (String present : presents) {
            String[] dimensionsLWH = present.split("x");
            int perimeterLW = (Integer.parseInt(dimensionsLWH[0]) + Integer.parseInt(dimensionsLWH[1])) * 2;
            int perimeterLH = (Integer.parseInt(dimensionsLWH[0]) + Integer.parseInt(dimensionsLWH[2])) * 2;
            int perimeterWH = (Integer.parseInt(dimensionsLWH[1]) + Integer.parseInt(dimensionsLWH[2])) * 2;
            int smallestPerimeter = perimeterLW <= perimeterLH ? (Math.min(perimeterLW, perimeterWH)) : (Math.min(perimeterLH, perimeterWH));
            int presentVolume = Integer.parseInt(dimensionsLWH[0]) * Integer.parseInt(dimensionsLWH[1]) *
                    Integer.parseInt(dimensionsLWH[2]);
            ribbonNeeded += smallestPerimeter + presentVolume;
        }
        return String.valueOf(ribbonNeeded);
    }
}
