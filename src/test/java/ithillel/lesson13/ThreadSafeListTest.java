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
        Thread th1 = new Thread(() -> list.add(1));
        Thread th2 = new Thread(() -> list.add(2));
        Thread th3 = new Thread(() -> list.add(3));
        Thread th4 = new Thread(() -> list.add(4));
        Thread th5 = new Thread(() -> list.add(5));

        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();

        try {
            th1.join();
            th2.join();
            th3.join();
            th4.join();
            th5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(list.contains(5));
    }

    @Test
    void removeTest() {
        ThreadSafeList<Integer> list = new ThreadSafeList<>();
        list.addAll(List.of(1, 2, 3, 4, 5));
        Thread th1 = new Thread(() -> list.remove(0));
        Thread th2 = new Thread(() -> list.remove(0));
        Thread th3 = new Thread(() -> list.remove(0));

        th1.start();
        th2.start();
        th3.start();

        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertFalse(list.contains(1));
    }
}