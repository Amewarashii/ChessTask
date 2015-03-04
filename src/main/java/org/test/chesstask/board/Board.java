package org.test.chesstask.board;

import org.test.chesstask.piece.Piece;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private Cell[][] cellCache;
    private Map<Cell, Piece> pieces = new HashMap<Cell, Piece>();

    public Board(int axisX, int axisY) {
        cellCache = new Cell[axisX + 1][axisY + 1];
        for(int i = 1; i <= axisX; i++) {
            for(int j = 1; j <= axisY; j++) {
                Cell cell = new Cell(i, j);
                cellCache[i][j] = cell;
            }
        }
    }

    public Cell cell(int x, int y) {
        return cellCache[x][y];
    }

    public boolean withinBounds(int x, int y) {
        return (x > 0 && cellCache.length > x) && (y > 0 && cellCache[x].length > y);
    }

    public int upperX() {
        return cellCache.length;
    }

    public int upperY() {
        return cellCache[0].length;
    }

    public Collection<Cell> cells(int x) {
        return Arrays.asList(cellCache[x]).subList(1, cellCache[x].length);
    }

    public void locateIn(Cell cell, Piece piece) {
        pieces.put(cell, piece);
    }

    public boolean occupied(Cell cell) {
        return pieces.containsKey(cell);
    }

    public Piece pieceForCell(Cell cell) {
        return pieces.get(cell);
    }

    public void clear() {
        pieces.clear();
    }

    public void apply(BoardState state) {
        for(Cell location : state.locations()) {
            pieces.put(location, location.getPiece());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return pieces.equals(board.pieces);
    }

    @Override
    public int hashCode() {
        return pieces.hashCode();
    }
}
