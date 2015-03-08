package org.test.chesstask.board;

import org.test.chesstask.piece.Piece;

import java.util.*;

public class BoardState {

    private int depth;
    private Cell[] last, locations;
    private long configCount;
    private Collection<Cell>[] movements;
    private Piece[] pieces;

    public BoardState(int pieces) {
        last = new Cell[pieces];
        locations = new Cell[pieces];
        movements = new Collection[pieces];
        this.pieces = new Piece[pieces];
    }

    public long getConfigCount() {
        return configCount;
    }

    public void increaseConfigCount() {
        configCount++;
    }

    public int getDepth() {
        return depth;
    }

    public void increaseDepth() {
        depth++;
    }

    public void decreaseDepth() {
        depth--;
    }

    public boolean isBeforeLast(Cell cell) {
        return last[depth] != null && cell.compareTo(last[depth]) < 0;
    }

    public void putLast(Cell cell) {
        last[depth] = cell;
    }

    public void add(Piece piece, Cell cell, Collection<Cell> movements) {
        this.locations[depth] = cell;
        this.movements[depth] = movements;
        this.pieces[depth] = piece;
    }

    public boolean overlap(Cell location, Collection<Cell> movements) {
        for(int i = 0; i < depth; i++) {
            if(movements.contains(locations[i]) || this.movements[i].contains(location)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            b.append(locations[i].getX()).append(locations[i].getY()).append(pieces[i].id());
        }
        return b.toString();
    }
}
