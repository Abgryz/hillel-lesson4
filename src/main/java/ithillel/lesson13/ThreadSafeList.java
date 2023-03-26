package ithillel.lesson13;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeList<T> extends ArrayList<T> {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    @Override
    public T get(int index) {
        lock.readLock().lock();
        try {
            return super.get(index);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean add(T t) {
        lock.writeLock().lock();
        try{
            return super.add(t);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public T remove(int index) {
        lock.writeLock().lock();
        try{
            return super.remove(index);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
