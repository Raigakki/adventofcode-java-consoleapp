package service.AOC2022;

import model.AOC2022.AOC2022E07File;
import model.AOC2022.AOC2022E07Directory;
import service.AOCService;
import utils.StaticUtils;

import java.util.ArrayList;
import java.util.List;

public class AOC2022Challenge07Service implements AOCService {

    private final String inputString;
    private int sizeUnderThreshold;

    public AOC2022Challenge07Service(String inputString) {
        this.inputString = inputString;
    }

    private static AOC2022E07Directory root = new AOC2022E07Directory("/", null);
    private static AOC2022E07Directory cwd = root;

    // TODO ANCORA DA FINIRE

    @Override
    public String solvePartOne() {
        List<String> inputList = StaticUtils.fromInputStringToStringList(inputString, "\n");
        AOC2022E07Directory fileSystem = this.createFileSystem(inputList);
        int size = countDirectorySize(fileSystem);
        return null;
    }

    @Override
    public String solvePartTwo() {
        return null;
    }

    private AOC2022E07Directory createFileSystem(List<String> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            String input = inputList.get(i);
            String[] inputArgs = input.split(" ");
            if (inputArgs[0].equals("$")) {
                if (inputArgs[1].equals("cd")) {
                    if (inputArgs[2].equals("/")) {
                        cwd = root;
                    } else if (inputArgs[2].equals("..")) {
                        cwd = cwd.getParentDirectory();
                    } else if (cwd.containsDirectory(inputArgs[2]))
                        cwd = cwd.getDirectoryByName(inputArgs[2]);
                } else if (inputArgs[1].equals("ls")) {
                    List<String> operations;
                    int index = i;
                    for (int j = i + 1; j < inputList.size(); j++) {
                        if (inputList.get(j).charAt(0) != '$')
                            index = j;
                        else
                            break;
                    }
                    operations = inputList.subList(i + 1, index + 1);
                    executeList(operations);
                }
            }
        }
        return root;
    }

    private static void executeList(List<String> input) {
        for (String s : input) {
            String[] inputArgs = s.split(" ");

            if (inputArgs[0].equals("dir")) {
                cwd.addDirectoryToDirectoryList(new AOC2022E07Directory(inputArgs[1], cwd));
            } else {
                cwd.addFileToDirectoryList(new AOC2022E07File(inputArgs[1], Integer.parseInt(inputArgs[0]), cwd));
            }
        }
    }

    private int countDirectorySize(AOC2022E07Directory directory) {
        int currentIterationSize;
        currentIterationSize = directory.getFileListSize();
        for (AOC2022E07Directory childDirectory : directory.getDirectoryList()) {
            currentIterationSize += countDirectorySize(childDirectory);
        }
        if (currentIterationSize <= 100000)
            sizeUnderThreshold += currentIterationSize;

        return currentIterationSize;
    }

}
