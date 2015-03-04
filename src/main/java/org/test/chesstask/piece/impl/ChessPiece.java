package org.test.chesstask.piece.impl;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.Cell;
import org.test.chesstask.piece.Piece;
import org.test.chesstask.piece.PieceType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

abstract class ChessPiece implements Piece {

    private int color;
    private String id;

    protected ChessPiece(PieceType type) {
        this.id = Character.toString(type.getId());
    }

    protected ChessPiece(int color, PieceType type) {
        this(type);
        this.color = color;
    }

    @Override
    public Set<Cell> possibleMoves(Cell location, Board board) {
        Set<Cell> result = new HashSet<Cell>();
        fillPossibleMoves(location, board, result);
        result.remove(location);
        return result;
    }

    @Override
    public String id() {
        return id;
    }

    protected abstract void fillPossibleMoves(Cell location, Board board, Collection<Cell> result);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChessPiece that = (ChessPiece) o;

        return color == that.color;
    }

    @Override
    public int hashCode() {
        return color + super.hashCode();
    }
}
