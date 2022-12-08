package model.AOC2022;

import java.util.ArrayList;
import java.util.List;

public class AOC2022E07Directory {

    private String directoryName;
    private List<AOC2022E07Directory> directoryList = new ArrayList<>();
    private List<AOC2022E07File> fileList = new ArrayList<>();
    private AOC2022E07Directory parentDirectory;

    public AOC2022E07Directory(String directoryName, AOC2022E07Directory parentDirectory) {
        this.directoryName = directoryName;
        this.parentDirectory = parentDirectory;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public List<AOC2022E07Directory> getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(List<AOC2022E07Directory> directoryList) {
        this.directoryList = directoryList;
    }

    public List<AOC2022E07File> getFileList() {
        return fileList;
    }

    public void setFileList(List<AOC2022E07File> fileList) {
        this.fileList = fileList;
    }

    public AOC2022E07Directory getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(AOC2022E07Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

    public boolean containsDirectory(String directoryName) {
        for (AOC2022E07Directory directory: this.directoryList) {
            if (directory.getDirectoryName().equals(directoryName))
                return true;
        }
        return false;
    }
    
    public AOC2022E07Directory getDirectoryByName(String directoryName) {
        for (AOC2022E07Directory directory: this.directoryList) {
            if (directory.getDirectoryName().equals(directoryName))
                return directory;
        }
        return null;
    }

    public void addFileToDirectoryList(AOC2022E07File file) {
        this.fileList.add(file);
    }

    public void addDirectoryToDirectoryList(AOC2022E07Directory directory) {
        this.directoryList.add(directory);
    }

    public int getFileListSize() {
        return this.fileList.stream()
                .mapToInt(file -> file.getFileSize())
                .sum();
    }

}
