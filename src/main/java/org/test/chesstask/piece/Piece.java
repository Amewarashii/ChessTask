package org.test.chesstask.piece;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;

import java.util.Collection;

public interface Piece extends Comparable<Piece> {

    Collection<Cell> possibleMoves(Cell location, Board board);

    String id();

    int code();
}
