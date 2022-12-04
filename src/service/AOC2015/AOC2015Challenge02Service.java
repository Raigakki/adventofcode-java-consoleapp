package service.AOC2015;

import service.AOCService;

public class AOC2015Challenge02Service implements AOCService {

    @Override
    public String solvePartOne(String input) {
        String[] presents = input.split("\n");
        int paperNeeded = 0;
        for (String present : presents) {
            String[] dimensionsLWH = present.split("x");
            int areaLW = Integer.parseInt(dimensionsLWH[0]) * Integer.parseInt(dimensionsLWH[1]);
            int areaLH = Integer.parseInt(dimensionsLWH[0]) * Integer.parseInt(dimensionsLWH[2]);
            int areaWH = Integer.parseInt(dimensionsLWH[1]) * Integer.parseInt(dimensionsLWH[2]);
            int smallestArea = areaLW <= areaLH ? (areaLW <= areaWH ? areaLW : areaWH) : (areaLH <= areaWH ? areaLH : areaWH);
            paperNeeded += 2*areaLW + 2*areaLH + 2*areaWH + smallestArea;
        }
        return String.format("THE TOTAL FEET^2 OF PAPER NEEDED IS %d FT^2.", paperNeeded);
    }

    @Override
    public String solvePartTwo(String input) {
        String[] presents = input.split("\n");
        int ribbonNeeded = 0;
        for (String present : presents) {
            String[] dimensionsLWH = present.split("x");
            int perimeterLW = (Integer.parseInt(dimensionsLWH[0]) + Integer.parseInt(dimensionsLWH[1])) * 2;
            int perimeterLH = (Integer.parseInt(dimensionsLWH[0]) + Integer.parseInt(dimensionsLWH[2])) * 2;
            int perimeterWH = (Integer.parseInt(dimensionsLWH[1]) + Integer.parseInt(dimensionsLWH[2])) * 2;
            int smallestPerimeter = perimeterLW <= perimeterLH ? (perimeterLW <= perimeterWH ? perimeterLW : perimeterWH) : (perimeterLH <= perimeterWH ? perimeterLH : perimeterWH);
            int presentVolume = Integer.parseInt(dimensionsLWH[0]) * Integer.parseInt(dimensionsLWH[1]) *
                    Integer.parseInt(dimensionsLWH[2]);
            ribbonNeeded += smallestPerimeter + presentVolume;
        }
        return String.format("THE TOTAL FEET OF RIBBON NEEDED IS %d FT.", ribbonNeeded);
    }
}
