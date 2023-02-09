package ithillel.lesson5;

import static org.junit.jupiter.api.Assertions.*;

class ArrayHillelListTest {
    private static final String[] INPUT_ARRAY = {"item1", "item2", "item3"};

    @org.junit.jupiter.api.Test
    void addTest() {
        HillelList list = new ArrayHillelList();
        list.add("elem");
        assertArrayEquals(list.getAll(), new String[] {"elem"});
    }

    @org.junit.jupiter.api.Test
    void removeTest() {
        HillelList list = new ArrayHillelList(INPUT_ARRAY);
        assertEquals(list.remove(0), "item1");
        assertArrayEquals(list.getAll(), new String[]{"item2", "item3"});

        assertEquals(list.remove(1), "item3");
        assertArrayEquals(list.getAll(), new String[]{"item2"});

        assertEquals(list.remove(0), "item2");
        assertArrayEquals(list.getAll(), new String[]{});

        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(10);});
    }

    @org.junit.jupiter.api.Test
    void containsTest() {
        HillelList list = new ArrayHillelList(INPUT_ARRAY);
        assertTrue(list.contains("item1"));
        assertFalse(list.contains("this item doesnt exist"));
    }

    @org.junit.jupiter.api.Test
    void indexOfTest() {
        HillelList list = new ArrayHillelList(INPUT_ARRAY);
        assertEquals(1, list.indexOf("item2"));
        assertEquals(-1, list.indexOf("this item doesnt exist"));
    }

    @org.junit.jupiter.api.Test
    void sizeTest() {
        HillelList list = new ArrayHillelList(INPUT_ARRAY);
        assertEquals(3, list.size());
    }

    @org.junit.jupiter.api.Test
    void getTest() {
        HillelList list = new ArrayHillelList(INPUT_ARRAY);
        assertEquals("item2", list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(10);});
    }
}