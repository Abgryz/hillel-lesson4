package ithillel.lesson7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileNavigatorTest {
    public final static List<FileData> DATA_LIST01 = List.of(
            new FileData("file01", 10, "/path/to/file"),
            new FileData("file02", 34, "/path/to/file"),
            new FileData("file03", 13, "/path/to/file"),
            new FileData("file04", 654, "/path/to/file"),
            new FileData("file05", 45, "/path/to/file"),
            new FileData("file06", 1, "/path/to/file")
    );
    public final static List<FileData> DATA_LIST02 = List.of(
            new FileData("file01", 100, "/another/path/to/file"),
            new FileData("file02", 340, "/another/path/to/file"),
            new FileData("file03", 130, "/another/path/to/file")
    );
    public final static List<FileData> DATA_LIST03 = List.of(
            new FileData("file01", 1000, "/another/path/to/another/file"),
            new FileData("file02", 3400, "/another/path/to/another/file"),
            new FileData("file03", 1300, "/another/path/to/another/file")
    );

    public final static List<FileData> SORTERED_DATA_LIST = List.of(
            new FileData("file06", 1, "/path/to/file"),
            new FileData("file01", 10, "/path/to/file"),
            new FileData("file03", 13, "/path/to/file"),
            new FileData("file02", 34, "/path/to/file"),
            new FileData("file05", 45, "/path/to/file"),
            new FileData("file01", 100, "/another/path/to/file"),
            new FileData("file03", 130, "/another/path/to/file"),
            new FileData("file02", 340, "/another/path/to/file"),
            new FileData("file04", 654, "/path/to/file"),
            new FileData("file01", 1000, "/another/path/to/another/file"),
            new FileData("file03", 1300, "/another/path/to/another/file"),
            new FileData("file02", 3400, "/another/path/to/another/file")
    );

    public final static List<FileData> FILTERED_LIST = List.of(
            new FileData("file01", 10, "/path/to/file"),
            new FileData("file02", 34, "/path/to/file"),
            new FileData("file03", 13, "/path/to/file"),
            new FileData("file05", 45, "/path/to/file"),
            new FileData("file06", 1, "/path/to/file"),
            new FileData("file01", 100, "/another/path/to/file")
    );
    public final static List<FileData> ADD_VALUE = List.of(new FileData("filename", 0, "/path/to/file"));

    @Test
    void addTest() {
        FileNavigator navigator = new FileNavigator();
        navigator.add("/path/to/file", "filename", 0, "/path/to/file");
        assertEquals(navigator.find("/path/to/file"), ADD_VALUE);
        assertThrows(InvalidPathException.class, () -> navigator.add("/path/to/file", "filename", 1, "/invalid/path"));
    }

    @Test
    void findTest() {
        FileNavigator navigator = new FileNavigator();
        navigator.add("/path/to/file", DATA_LIST01);
        navigator.add("/another/path/to/file", DATA_LIST02);
        navigator.add("/another/path/to/another/file", DATA_LIST03);

        assertEquals(navigator.find("/path/to/file"), DATA_LIST01);
    }

    @Test
    void filterBySizeTest() {
        FileNavigator navigator = new FileNavigator();
        navigator.add("/path/to/file", DATA_LIST01);
        navigator.add("/another/path/to/file", DATA_LIST02);
        navigator.add("/another/path/to/another/file", DATA_LIST03);

        assertEquals(navigator.filterBySize(100), FILTERED_LIST);
    }

    @Test
    void removeTest() {
        FileNavigator navigator = new FileNavigator();
        navigator.add("/path/to/file", DATA_LIST01);
        navigator.add("/another/path/to/file", DATA_LIST02);
        navigator.add("/another/path/to/another/file", DATA_LIST03);

        FileNavigator navigatorRemove = new FileNavigator();
        navigatorRemove.add("/path/to/file", DATA_LIST01);
        navigatorRemove.add("/another/path/to/another/file", DATA_LIST03);

        navigator.remove("/another/path/to/file");
        assertEquals(navigator, navigatorRemove);
    }

    @Test
    void sortBySizeTest() {
        FileNavigator navigator = new FileNavigator();
        navigator.add("/path/to/file", DATA_LIST01);
        navigator.add("/another/path/to/file", DATA_LIST02);
        navigator.add("/another/path/to/another/file", DATA_LIST03);

        assertEquals(navigator.sortBySize(), SORTERED_DATA_LIST);
    }
}