package ithillel.lesson34;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeIteratorTest {
    @Test
    void iteratorTest() {
        BinaryTree<Integer> tree = new BinaryTree<>(5)
                .add(3)
                .add(4)
                .add(7)
                .add(6);
        TreeIterator<Integer> iterator = new TreeIterator<>(tree);
        assertEquals(iterator.next(), 5);
        assertEquals(iterator.next(), 3);
        assertEquals(iterator.next(), 4);
        assertEquals(iterator.next(), 7);
        assertEquals(iterator.next(), 6);
        assertFalse(iterator.hasNext());
    }
}