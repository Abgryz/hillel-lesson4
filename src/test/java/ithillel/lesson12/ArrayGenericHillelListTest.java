package ithillel.lesson12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayGenericHillelListTest {
    private static final String[] INPUT_ARRAY_STRING = {"item1", "item2", "item3"};
    private static final Integer[] INPUT_ARRAY_INTEGER = {1, 2, 3};

    @Test
    void addTest() {
        GenericHillelList<String> list1 = new ArrayGenericHillelList<>();
        list1.add("elem");
        assertArrayEquals(list1.getAll(), new String[] {"elem"});

        GenericHillelList<Integer> list2 = new ArrayGenericHillelList<>();
        list2.add(1);
        assertArrayEquals(list2.getAll(), new Integer[] {1});
    }

    @Test
    void removeTest() {
        GenericHillelList<String> list1 = new ArrayGenericHillelList<>(INPUT_ARRAY_STRING);
        assertEquals(list1.remove(0), "item1");
        assertArrayEquals(list1.getAll(), new String[]{"item2", "item3"});
        assertEquals(list1.remove(1), "item3");
        assertArrayEquals(list1.getAll(), new String[]{"item2"});
        assertEquals(list1.remove(0), "item2");
        assertArrayEquals(list1.getAll(), new String[]{});
        assertThrows(IndexOutOfBoundsException.class, () -> {list1.remove(10);});

        GenericHillelList<Integer> list2 = new ArrayGenericHillelList<>(INPUT_ARRAY_INTEGER);
        assertEquals(list2.remove(0), 1);
        assertArrayEquals(list2.getAll(), new Integer[]{2, 3});
    }

    @Test
    void containsTest() {
        GenericHillelList<String> list1 = new ArrayGenericHillelList<>(INPUT_ARRAY_STRING);
        assertTrue(list1.contains("item1"));
        assertFalse(list1.contains("this item doesnt exist"));

        GenericHillelList<Integer> list2 = new ArrayGenericHillelList<>(INPUT_ARRAY_INTEGER);
        assertTrue(list2.contains(1));
        assertFalse(list2.contains(123));
    }

    @Test
    void indexOfTest() {
        GenericHillelList<String> list1 = new ArrayGenericHillelList<>(INPUT_ARRAY_STRING);
        assertEquals(1, list1.indexOf("item2"));
        assertEquals(-1, list1.indexOf("this item doesnt exist"));

        GenericHillelList<Integer> list2 = new ArrayGenericHillelList<>(INPUT_ARRAY_INTEGER);
        assertEquals(1, list2.indexOf(2));
        assertEquals(-1, list2.indexOf(1000));
    }

    @Test
    void sizeTest() {
        GenericHillelList<String> list1 = new ArrayGenericHillelList<>(INPUT_ARRAY_STRING);
        assertEquals(3, list1.size());

        GenericHillelList<Integer> list2 = new ArrayGenericHillelList<>(INPUT_ARRAY_INTEGER);
        assertEquals(3, list2.size());
    }

    @Test
    void getTest() {
        GenericHillelList<String> list1 = new ArrayGenericHillelList<>(INPUT_ARRAY_STRING);
        assertEquals("item2", list1.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> {list1.get(10);});

        GenericHillelList<Integer> list2 = new ArrayGenericHillelList<>(INPUT_ARRAY_INTEGER);
        assertEquals(2, list2.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> {list2.get(10);});
    }
}