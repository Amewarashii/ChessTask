package org.test.chesstask.piece.impl;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;

import java.util.Collection;

import static org.test.chesstask.piece.PieceType.BISHOP;

public class Bishop extends ChessPiece {

    public Bishop() {
        super(BISHOP);
    }

    @Override
    protected void fillPossibleMoves(Cell location, Board board, Collection<Cell> result) {
        int inc, y;
        for(int x = 1; x <= board.upperX(); x++) {
            inc = location.getX() - x;
            for(int j = 0; j < 2; j++) {
                y = location.getY() + inc;
                if (board.withinBounds(x, y)) {
                    result.add(board.cell(x, y));
                }
                inc = -inc;
            }
        }
    }
}
