package ithillel.lesson34;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
    private final T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree<T> add(T newValue){
        if (newValue.compareTo(value) < 0){
            left = left == null ? new BinaryTree<>(newValue) : left.add(newValue);
        } else {
            right = right == null ? new BinaryTree<>(newValue) : right.add(newValue);
        }
        return this;
    }

    public boolean isLeaf(){
        return left == null && right == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(this);
    }
    @Override
    public String toString() {
        String leftStr = left == null ? "" : ", left=" + left;
        String rightStr = right == null ? "" : ", right=" + right;

        return "BinaryTree{" +
                "value=" + value +
                leftStr +
                rightStr +
                '}';
    }
}
