package ithillel.lesson13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreadSafeListTest {

    @Test
    void getTest() {
        ThreadSafeList<Integer> list = new ThreadSafeList<>();
        list.addAll(List.of(1, 2, 3, 4, 5));
        new Thread(() -> assertEquals(list.get(0), 1)).start();
        new Thread(() -> assertEquals(list.get(1), 2)).start();
        new Thread(() -> assertEquals(list.get(2), 3)).start();
        new Thread(() -> assertEquals(list.get(3), 4)).start();
        new Thread(() -> assertEquals(list.get(4), 5)).start();
    }

    @Test
    void addTest() {
        ThreadSafeList<Integer> list = new ThreadSafeList<>();
        new Thread(() -> list.add(1)).start();
        new Thread(() -> list.add(2)).start();
        new Thread(() -> list.add(3)).start();
        new Thread(() -> list.add(4)).start();
        new Thread(() -> list.add(5)).start();

        assertTrue(list.contains(1));
    }

    @Test
    void removeTest() {
        ThreadSafeList<Integer> list = new ThreadSafeList<>();
        list.addAll(List.of(1, 2, 3, 4, 5));
        new Thread(() -> list.remove(0)).start();
        new Thread(() -> list.remove(0)).start();
        new Thread(() -> list.remove(0)).start();

        assertFalse(list.contains(1));
    }
}