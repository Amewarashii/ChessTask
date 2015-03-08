package org.test.chesstask.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Board {

    private Cell[][] cellCache;
    private List<Cell> cells = new ArrayList<Cell>();

    public Board(int axisX, int axisY) {
        cellCache = new Cell[axisX + 1][axisY + 1];
        for(int i = 1; i <= axisX; i++) {
            for(int j = 1; j <= axisY; j++) {
                Cell cell = new Cell(i, j);
                cellCache[i][j] = cell;
                cells.add(cell);
            }
        }
    }

    public Cell cell(int x, int y) {
        return cellCache[x][y];
    }

    public List<Cell> cells() {
        return cells;
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
}
