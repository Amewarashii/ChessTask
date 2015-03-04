package org.test.chesstask.board;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class BoardState {

    private Map<Cell, Set<Cell>> state = new TreeMap<Cell, Set<Cell>>();

    public BoardState() {}

    public BoardState(Cell location, Set<Cell> movements) {
        add(location, movements);
    }

    public BoardState(BoardState result) {
        this.state.putAll(result.getState());
    }

    public Map<Cell, Set<Cell>> getState() {
        return state;
    }

    public void add(Cell cell, Set<Cell> movements) {
        this.state.put(cell, movements);
    }

    public boolean contains(Cell cell) {
        return containsLocation(cell) || containsMovement(cell);
    }

    public boolean containsLocation(Cell cell) {
        return locations().contains(cell);
    }

    public boolean containsMovement(Cell cell) {
        for(Cell location : locations()) {
            if(state.get(location).contains(cell)) {
                return true;
            }
        }
        return false;
    }

    public boolean intersectWith(Set<Cell> movements) {
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

    public Set<Cell> movementsFor(Cell location) {
        return state.get(location);
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
}
