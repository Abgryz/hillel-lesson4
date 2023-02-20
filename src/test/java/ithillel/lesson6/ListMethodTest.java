package ithillel.lesson6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ithillel.lesson6.ListMethod.countOccurrence;
import static org.junit.jupiter.api.Assertions.*;

class ListMethodTest {
    public final static List<String> INPUT_STRING_LIST = List.of("bird", "fox", "cat", "dog", "wolf", "bear", "fox", "cat", "dog", "wolf", "bear", "dog", "wolf", "bear");
    public final static List<Integer> INPUT_INT_LIST = List.of(1, 2, 3, 3, 2, 1, 4, 5, 6, 7, 7, 8, 8, 9, 9, 0);
    public final static List<Integer> OUTPUT_INT_LIST = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    public final static List<ElemOccurrence> OUTPUT_STRUCT_LIST = List.of(
            new ElemOccurrence("bird", 1),
            new ElemOccurrence("fox", 2),
            new ElemOccurrence("cat", 2),
            new ElemOccurrence("dog", 3),
            new ElemOccurrence("wolf", 3),
            new ElemOccurrence("bear", 3)
    );

    public final static int[] INPUT_ARRAY = {1, 2, 3, 4, 5, 6};

    @Test
    void countOccurrenceTest() {
        assertEquals(3, ListMethod.countOccurrence(INPUT_STRING_LIST, "dog"));
    }

    @Test
    void toListTest() {
        List<Integer> list = ListMethod.toList(INPUT_ARRAY);
        assertEquals(list.size(), INPUT_ARRAY.length);
        for (int i = 0; i < list.size(); i++){
            assertEquals(INPUT_ARRAY[i], list.get(i));
        }
    }

    @Test
    void findUniqueTest() {
        List<Integer> list = ListMethod.findUnique(INPUT_INT_LIST);
        assertEquals(OUTPUT_INT_LIST, list);
    }

    @Test
    void findOccurrenceTest() {
        List<ElemOccurrence> list = ListMethod.findOccurrence(INPUT_STRING_LIST);
        assertEquals(OUTPUT_STRUCT_LIST, list);
    }
}