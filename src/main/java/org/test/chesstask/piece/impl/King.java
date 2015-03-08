package org.test.chesstask.piece.impl;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;

import java.util.Collection;

import static org.test.chesstask.piece.PieceType.KING;

public class King extends ChessPiece {

    public King() {
        super(KING);
    }

    @Override
    protected void fillPossibleMoves(Cell location, Board board, Collection<Cell> result) {
        int x, y;
        for(int i = -1; i < 2; i++) {
            x = location.getX() + i;
            for(int j = -1; j < 2; j++) {
                y = location.getY() + j;
                if(board.withinBounds(x, y)) {
                    result.add(board.cell(x, y));
                }
            }
        }
    }
}
