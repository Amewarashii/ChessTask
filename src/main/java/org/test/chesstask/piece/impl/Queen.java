package org.test.chesstask.piece.impl;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;
import org.test.chesstask.piece.Piece;

import java.util.Collection;

import static org.test.chesstask.piece.PieceType.QUEEN;

public class Queen extends ChessPiece {

    private Piece[] providers = new Piece[] { new Rook(), new Bishop() };

    public Queen() {
        super(QUEEN);
    }

    public Queen(int color) {
        super(color, QUEEN);
    }

    @Override
    protected void fillPossibleMoves(Cell location, Board board, Collection<Cell> result) {
        for(Piece piece : providers) {
            result.addAll(piece.possibleMoves(location, board));
        }
    }
}
