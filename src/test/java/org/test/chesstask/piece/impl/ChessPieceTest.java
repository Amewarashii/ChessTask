package org.test.chesstask.piece.impl;

import org.junit.Test;
import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;
import org.test.chesstask.piece.Piece;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChessPieceTest {

    @Test
    public void testPossibleMovesBishop() {
        Board board = new Board(6, 6);
        Piece piece = new Bishop();
        testPossibleMoves(2, 2, board, piece, new Cell(1, 1), new Cell(1, 3), new Cell(3, 1), new Cell(3, 3), new Cell(4, 4), new Cell(5, 5), new Cell(6, 6));
        testPossibleMoves(3, 3, board, piece, new Cell(1, 1), new Cell(1, 5), new Cell(2, 2), new Cell(2, 4), new Cell(4, 2), new Cell(4, 4), new Cell(5, 1), new Cell(5, 5), new Cell(6, 6));
        testPossibleMoves(1, 2, board, piece, new Cell(2, 1), new Cell(2, 3), new Cell(3, 4), new Cell(4, 5), new Cell(5, 6));
    }

    @Test
    public void testPossibleMovesKing() {
        Board board = new Board(6, 6);
        Piece piece = new King();
        testPossibleMoves(2, 2, board, piece, new Cell(1, 1), new Cell(1, 2), new Cell(1, 3), new Cell(2, 1), new Cell(2, 3), new Cell(3, 1), new Cell(3, 2), new Cell(3, 3));
        testPossibleMoves(1, 2, board, piece, new Cell(1, 1), new Cell(1, 3), new Cell(2, 1), new Cell(2, 2), new Cell(2, 3));
        testPossibleMoves(6, 6, board, piece, new Cell(5, 5), new Cell(5, 6), new Cell(6, 5));
    }

    @Test
    public void testPossibleMovesKnight() {
        Board board = new Board(6, 6);
        Piece piece = new Knight();
        testPossibleMoves(3, 3, board, piece, new Cell(1, 2), new Cell(1, 4), new Cell(2, 1), new Cell(2, 5), new Cell(4, 1), new Cell(4, 5), new Cell(5, 2), new Cell(5, 4));
        testPossibleMoves(1, 2, board, piece, new Cell(2, 4), new Cell(3, 1), new Cell(3, 3));
        testPossibleMoves(1, 1, board, piece, new Cell(2, 3), new Cell(3, 2));
    }

    @Test
    public void testPossibleMovesRook() {
        Board board = new Board(3, 3);
        Piece piece = new Rook();
        testPossibleMoves(2, 2, board, piece, new Cell(2, 1), new Cell(2, 3), new Cell(1, 2), new Cell(3, 2));
        testPossibleMoves(1, 1, board, piece, new Cell(1, 3), new Cell(1, 2), new Cell(3, 1), new Cell(2, 1));
    }

    @Test
    public void testPossibleMovesQueen() {
        Board board = new Board(6, 6);
        Piece piece = new Queen();
        testPossibleMoves(
                2, 2, board, piece,
                new Cell(1, 1), new Cell(1, 2), new Cell(1, 3), new Cell(2, 1), new Cell(2, 3), new Cell(2, 4), new Cell(2, 5), new Cell(2, 6),
                new Cell(3, 1), new Cell(3, 2), new Cell(3, 3), new Cell(4, 2), new Cell(4, 4), new Cell(5, 2), new Cell(5, 5), new Cell(6, 2), new Cell(6, 6)
        );

        testPossibleMoves(
                1, 1, board, piece,
                new Cell(1, 2), new Cell(1, 3), new Cell(1, 4), new Cell(1, 5), new Cell(1, 6), new Cell(2, 1), new Cell(2, 2), new Cell(3, 1),
                new Cell(3, 3), new Cell(4, 1), new Cell(4, 4), new Cell(5, 1), new Cell(5, 5), new Cell(6, 1), new Cell(6, 6)
        );
    }

    protected void testPossibleMoves(int x, int y, Board board, Piece piece, Cell... movesToCheck) {
        Cell location = board.cell(x, y);
        Set<Cell> moves = piece.possibleMoves(location, board);
        assertEquals(movesToCheck.length, moves.size());
        assertTrue(moves.equals(new HashSet<Cell>(Arrays.asList(movesToCheck))));
    }
}
