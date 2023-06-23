package ithillel.lesson34;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BinaryTreeTest {

    private static final BinaryTree<Integer> expectedTree = new BinaryTree<>(5,
            new BinaryTree<>(3,
                    null, new BinaryTree<>(4)
            ),
            new BinaryTree<>(7,
                    new BinaryTree<>(6), null
            ));
    @Test
    void addTest() {
        BinaryTree<Integer> tree = new BinaryTree<>(5)
                .add(3)
                .add(4)
                .add(7)
                .add(6);
        log.info(tree.toString());
        assertEquals(tree, expectedTree);
    }
}