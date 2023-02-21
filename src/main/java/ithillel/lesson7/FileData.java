package ithillel.lesson7;

public record FileData(String fileName, int size, String path) implements Comparable<FileData>{
    @Override
    public int compareTo(FileData o) {
        return this.size - o.size;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "fileName='" + fileName + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                '}';
    }
}
