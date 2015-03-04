package org.test.chesstask.piece.impl;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;

import java.util.Collection;

import static org.test.chesstask.piece.PieceType.ROOK;

public class Rook extends ChessPiece {

    public Rook() {
        super(ROOK);
    }

    public Rook(int color) {
        super(color, ROOK);
    }

    @Override
    protected void fillPossibleMoves(Cell location, Board board, Collection<Cell> result) {
        result.addAll(board.cells(location.getX()));
        for (int i = 1; i < board.upperX(); i++) {
            result.add(board.cell(i, location.getY()));
        }
    }
}
