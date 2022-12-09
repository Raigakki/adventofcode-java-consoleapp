package service.AOC2022;

import service.AOCService;
import utils.StaticUtils;

import java.util.List;

public class AOC2022Challenge08Service implements AOCService {

    private final String inputString;

    public AOC2022Challenge08Service(String inputString) {
        this.inputString = inputString;
    }


    @Override
    public String solvePartOne() {
        List<String> inputList = StaticUtils.fromInputStringToStringList(inputString, "\n");
        int[][] treeGrid = this.createTreeGrid(inputList);
        int[][] visibleTreeGrid = this.createVisibleTreeGrid(treeGrid);
        return String.valueOf(this.countVisibleTrees(visibleTreeGrid));
    }

    @Override
    public String solvePartTwo() {
        List<String> inputList = StaticUtils.fromInputStringToStringList(inputString, "\n");
        int[][] treeGrid = this.createTreeGrid(inputList);
        int[][] scenicScoreTreeGrid = this.createScenicScoreTreeGrid(treeGrid);
        return String.valueOf(this.findBestScenicScoreTree(scenicScoreTreeGrid));
    }

    private int[][] createTreeGrid(List<String> inputList) {
        int[][] treeGrid = new int[inputList.size()][inputList.get(0).length()];
        for (int x = 0; x < inputList.size(); x++) {
            for (int y = 0; y < inputList.get(x).length(); y++) {
                treeGrid[x][y] = Integer.parseInt(String.valueOf(inputList.get(x).charAt(y)));
            }
        }
        return treeGrid;
    }

    private int[][] createVisibleTreeGrid(int[][] treeGrid) {
        int[][] visibleTreeGrid = new int[treeGrid.length][treeGrid[0].length];
        for (int x = 0; x < treeGrid.length; x++) {
            for (int y = 0; y < treeGrid[x].length; y++) {
                visibleTreeGrid[x][y] = 1;
            }
        }

        for (int x = 0; x < treeGrid.length; x++) {
            for (int y = 0; y < treeGrid[x].length; y++) {
                boolean notVisibleFromWest = false;
                boolean notVisibleFromEast = false;
                boolean notVisibleFromNorth = false;
                boolean notVisibleFromSouth = false;

                // NOT VISIBLE FROM WEST
                for (int z = 0; z < y; z++) {
                    if (treeGrid[x][z] >= treeGrid[x][y]) {
                        notVisibleFromWest = true;
                        break;
                    }
                }

                // NOT VISIBLE FROM EAST
                for (int z = y + 1; z < treeGrid[y].length; z++) {
                    if (treeGrid[x][z] >= treeGrid[x][y]) {
                        notVisibleFromEast = true;
                        break;
                    }
                }

                // NOT VISIBLE FROM NORTH
                for (int z = 0; z < x; z++) {
                    if (treeGrid[z][y] >= treeGrid[x][y]) {
                        notVisibleFromNorth = true;
                        break;
                    }
                }

                // NOT VISIBLE FROM SOUTH
                for (int z = x + 1; z < treeGrid[x].length; z++) {
                    if (treeGrid[z][y] >= treeGrid[x][y]) {
                        notVisibleFromSouth = true;
                        break;
                    }
                }

                if (notVisibleFromWest && notVisibleFromEast && notVisibleFromNorth && notVisibleFromSouth)
                    visibleTreeGrid[x][y] = 0;

            }
        }
        return visibleTreeGrid;
    }

    private int countVisibleTrees(int[][] visibleTreeGrid) {
        int count = 0;
        for (int x = 0; x < visibleTreeGrid.length; x++) {
            for (int y = 0; y < visibleTreeGrid[x].length; y++) {
                count += visibleTreeGrid[x][y];
            }
        }
        return count;
    }

    private int[][] createScenicScoreTreeGrid(int[][] treeGrid) {
        int[][] scenicScoreTreeGrid = new int[treeGrid.length][treeGrid[0].length];

        for (int x = 0; x < treeGrid.length; x++) {
            for (int y = 0; y < treeGrid[x].length; y++) {
                int visibilityLookingWest = 0;
                int visibilityLookingEast = 0;
                int visibilityLookingNorth = 0;
                int visibilityLookingSouth = 0;

                // NUM TREES VISIBLE LOOKING WEST
                for (int z = y - 1; z >= 0; z--) {
                    if (treeGrid[x][z] < treeGrid[x][y]) {
                        visibilityLookingWest++;
                    }
                    if (treeGrid[x][z] >= treeGrid[x][y]) {
                        visibilityLookingWest++;
                        break;
                    }
                }

                // NUM TREES VISIBLE LOOKING EAST
                for (int z = y + 1; z < treeGrid[x].length; z++) {
                    if (treeGrid[x][z] < treeGrid[x][y]) {
                        visibilityLookingEast++;
                    }
                    if (treeGrid[x][z] >= treeGrid[x][y]) {
                        visibilityLookingEast++;
                        break;
                    }
                }

                // NUM TREES VISIBLE LOOKING NORTH
                for (int z = x - 1; z >= 0; z--) {
                    if (treeGrid[z][y] < treeGrid[x][y]) {
                        visibilityLookingNorth++;
                    }
                    if (treeGrid[z][y] >= treeGrid[x][y]) {
                        visibilityLookingNorth++;
                        break;
                    }
                }

                // NUM TREES VISIBLE LOOKING SOUTH
                for (int z = x + 1; z < treeGrid.length; z++) {
                    if (treeGrid[z][y] < treeGrid[x][y]) {
                        visibilityLookingSouth++;
                    }
                    if (treeGrid[z][y] >= treeGrid[x][y]) {
                        visibilityLookingSouth++;
                        break;
                    }
                }
                scenicScoreTreeGrid[x][y] = visibilityLookingWest * visibilityLookingEast *
                        visibilityLookingNorth * visibilityLookingSouth;
            }
        }
        return scenicScoreTreeGrid;
    }

    private int findBestScenicScoreTree(int[][] scenicScoreTreeGrid) {
        int maxScenicScore = 0;
        for (int x = 0; x < scenicScoreTreeGrid.length; x++) {
            for (int y = 0; y < scenicScoreTreeGrid[x].length; y++) {
                if (scenicScoreTreeGrid[x][y] > maxScenicScore)
                    maxScenicScore = scenicScoreTreeGrid[x][y];
            }
        }
        return maxScenicScore;
    }


}
