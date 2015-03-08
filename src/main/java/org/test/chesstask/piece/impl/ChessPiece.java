package org.test.chesstask.piece.impl;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;
import org.test.chesstask.piece.Piece;
import org.test.chesstask.piece.PieceType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

abstract class ChessPiece implements Piece {

    private String id;
    private int code;

    protected ChessPiece(PieceType type) {
        this.id = Character.toString(type.getId());
        this.code = type.getCode();
    }

    @Override
    public Collection<Cell> possibleMoves(Cell location, Board board) {
        Set<Cell> result = new HashSet<Cell>();
        fillPossibleMoves(location, board, result);
        result.add(location);
        return result;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public int code() {
        return code;
    }

    protected abstract void fillPossibleMoves(Cell location, Board board, Collection<Cell> result);

    @Override
    public int compareTo(Piece o) {
        return code - o.code();
    }
}
