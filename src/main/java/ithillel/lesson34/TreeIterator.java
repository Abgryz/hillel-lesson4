package ithillel.lesson34;

import java.util.Iterator;
import java.util.Stack;

public class TreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final Stack<BinaryTree<T>> treeElements = new Stack<>();
    public TreeIterator(BinaryTree<T> root){
        treeElements.push(root);
    }
    @Override
    public boolean hasNext() {
        return !treeElements.isEmpty();
    }

    @Override
    public T next() {
        BinaryTree<T> currentElem = treeElements.pop();
        if(!currentElem.isLeaf()) {
            if (currentElem.getRight() != null) {
                treeElements.push(currentElem.getRight());
            }
            if (currentElem.getLeft() != null) {
                treeElements.push(currentElem.getLeft());
            }
        }
        return currentElem.getValue();

    }
}
