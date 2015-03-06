package org.test.chesstask.board;

import org.test.chesstask.piece.Piece;

public class Cell implements Comparable<Cell> {

    private int x, y;
    private Piece piece;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, Piece piece) {
        this(x, y);
        this.piece = piece;
    }

    public Cell(Cell cell, Piece piece) {
        this(cell.getX(), cell.getY());
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(Cell o) {
        int result = x - o.x;
        if(result == 0) {
            result = y - o.y;
        }
        return result;
    }
}
