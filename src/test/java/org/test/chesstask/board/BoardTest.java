package org.test.chesstask.board;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void testBounds() {
        Board board = new Board(3, 3);

        assertTrue(board.withinBounds(2, 2));
        assertFalse(board.withinBounds(3, 4));
    }
}
