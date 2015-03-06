package org.test.chesstask.board;

import org.test.chesstask.piece.Piece;

import java.util.*;

public class BoardService {

    public Map<Cell, Collection<Cell>> movementsForPiece(Piece piece, Board board) {
        Map<Cell, Collection<Cell>> result = new TreeMap<Cell, Collection<Cell>>();
        for (int i = 1; i < board.upperX(); i++) {
            for (Cell cell : board.cells(i)) {
                result.put(new Cell(cell, piece), piece.possibleMoves(cell, board));
            }
        }
        return result;
    }

    public Map<Piece, Map<Cell, Collection<Cell>>> movementsForPiece(Collection<Piece> pieces, Board board) {
        Map<Piece, Map<Cell, Collection<Cell>>> result = new TreeMap<Piece, Map<Cell, Collection<Cell>>>();
        for (Piece piece : pieces) {
            result.put(piece, movementsForPiece(piece, board));
        }
        return result;
    }

    public Collection<String> threatFreeConfigurations(Collection<Piece> pieces, Board board) {
        Set<String> result = new HashSet<String>();

        Map<Piece, Map<Cell, Collection<Cell>>> piecesMovements = movementsForPiece(pieces, board);

        Queue<Map<Cell, Collection<Cell>>> queue = new LinkedList<Map<Cell, Collection<Cell>>>();
        for (Piece piece : pieces) {
            queue.add(piecesMovements.get(piece));
        }

        long s1 = System.currentTimeMillis();

        merge2(new BoardState(), queue, result);

        System.out.println(result.size());
        System.out.println("mem (M) " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024));
        System.out.println("time (s): " + (((double) System.currentTimeMillis() - (double) s1) / 1000));

        return result;
    }



    private void merge2(BoardState state, Queue<Map<Cell, Collection<Cell>>> movementsQueue, Collection<String> result) {
        Map<Cell, Collection<Cell>> movements = movementsQueue.poll();
        if (movements == null) {
            result.add(state.toString());
            return;
        }
        for (Cell location : movements.keySet()) {
            Collection<Cell> cellMovements = movements.get(location);
            if (state.contains(location) || state.intersectWith(cellMovements)) {
                continue;
            }
            state.add(location, cellMovements);
            merge2(state, movementsQueue, result);
            state.remove(location);
        }
        movementsQueue.add(movements);
    }
}
