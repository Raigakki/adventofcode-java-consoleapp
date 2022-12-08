package model.AOC2022;

public class AOC2022E07File {

    private String fileName;
    private int fileSize;
    private AOC2022E07Directory parentDirectory;

    public AOC2022E07File(String fileName, int fileSize, AOC2022E07Directory parentDirectory) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.parentDirectory = parentDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public AOC2022E07Directory getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(AOC2022E07Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

}
