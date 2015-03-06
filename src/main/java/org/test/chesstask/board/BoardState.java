package org.test.chesstask.board;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class BoardState {

    private Map<Cell, Collection<Cell>> state = new TreeMap<Cell, Collection<Cell>>();

    public BoardState() {}

    public void add(Cell cell, Collection<Cell> movements) {
        this.state.put(cell, movements);
    }

    public boolean contains(Cell cell) {
        return containsLocation(cell) || containsMovement(cell);
    }

    public boolean containsLocation(Cell cell) {
        return locations().contains(cell);
    }

    public boolean containsMovement(Cell cell) {
        for (Cell location : locations()) {
            if (location.equals(cell) || state.get(location).contains(cell)) {
                return true;
            }
        }
        return false;
    }

    public boolean intersectWith(Collection<Cell> movements) {
        for (Cell location : locations()) {
            if (movements.contains(location)) {
                return true;
            }
        }
        return false;
    }

    public Collection<Cell> locations() {
        return state.keySet();
    }

    public void remove(Cell cell) {
        state.remove(cell);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardState that = (BoardState) o;

        return state.keySet().equals(that.state.keySet());
    }

    @Override
    public int hashCode() {
        return state.keySet().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (Cell location : locations()) {
            b.append(location.getX()).append(location.getY()).append(location.getPiece().id());
        }
        return b.toString();
    }
}
