package ithillel.lesson7;

import java.util.*;

public class FileNavigator{
    private final Map<String, List<FileData>> navigator = new HashMap<>();
    private List<FileData> sorteredList;

    public void add(String pathDir, String fileName, int size, String pathFile){
        if (!pathFile.startsWith(pathDir))
            throw new InvalidPathException("Entered invalid file path!");
        FileData fileData = new FileData(fileName, size, pathFile);
        if(navigator.containsKey(pathDir)) {
            navigator.get(pathDir).add(fileData);
        }
        else {
            List<FileData> data = new ArrayList<>();
            data.add(fileData);
            navigator.put(pathDir, data);
        }
    }
    public void add(String pathDir, String fileName, int size){
        add(pathDir, fileName, size, pathDir + "/" + fileName);
    }
    public void add(String pathDir, List<FileData> dataList){
        for (FileData item: dataList){
            add(pathDir, item.fileName(), item.size(), item.path());
        }
    }

    public List<FileData> find(String path){
        return navigator.get(path);
    }

    public List<FileData> filterBySize(int maxSize){
        List<FileData> dataList = new ArrayList<>();
        navigator.forEach((key, value) -> {
            for (FileData item: value){
                if (item.size() <= maxSize)
                    dataList.add(item);
            }
        });
        return dataList;
    }

    public void remove(String path){
        navigator.remove(path);
    }

    public List<FileData> sortBySize(){
        List<FileData> sorteredList = new ArrayList<>();
        navigator.forEach((key, value) -> {
            for (FileData item: value) {
                sorteredList.add(item);
            }
        });
        Collections.sort(sorteredList);
        return sorteredList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileNavigator navigator1 = (FileNavigator) o;
        return Objects.equals(navigator, navigator1.navigator);
    }
    @Override
    public int hashCode() {
        return Objects.hash(navigator);
    }
}
