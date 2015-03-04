package org.test.chesstask.piece;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;

import java.util.Set;

public interface Piece {

    Set<Cell> possibleMoves(Cell location, Board board);

    String id();
}