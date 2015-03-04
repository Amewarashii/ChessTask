package org.test.chesstask.piece.impl;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;

import java.util.Collection;

import static org.test.chesstask.piece.PieceType.KNIGHT;

public class Knight extends ChessPiece {

    public Knight() {
        super(KNIGHT);
    }

    public Knight(int color) {
        super(color, KNIGHT);
    }

    @Override
    protected void fillPossibleMoves(Cell location, Board board, Collection<Cell> result) {
        int x, y, inc;
        for (int i = -2; i <= 2; i++) {
            if (i == 0) continue;
            x = location.getX() + i;
            inc = (i % 2 == 0) ? -1 : -2;
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
