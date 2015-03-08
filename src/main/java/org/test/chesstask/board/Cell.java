package org.test.chesstask.board;

public class Cell implements Comparable<Cell> {

    private int x, y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    @Override
    public String toString() {
        return x + "" + y;
    }
}
