package org.test.chesstask.board;

import org.test.chesstask.app.Input;
import org.test.chesstask.piece.Piece;

import java.util.*;

public class BoardService {

    public Map<Cell, Collection<Cell>> movementsForPiece(Piece piece, Board board) {
        Map<Cell, Collection<Cell>> result = new HashMap<Cell, Collection<Cell>>();
        for (Cell cell : board.cells()) {
            result.put(cell, piece.possibleMoves(cell, board));
        }
        return result;
    }

    public long threatFreeConfigurations(Input config, BoardServiceListener listener) {
        long s1 = System.currentTimeMillis();

        Board board = new Board(config.getX(), config.getY());
        List<Piece> pieces = config.getPieces();

        Collections.sort(pieces);

        Map<Integer, Map<Cell, Collection<Cell>>> movements = new HashMap<Integer, Map<Cell, Collection<Cell>>>();
        for (Piece piece : pieces) {
            if (movements.containsKey(piece.code())) {
                continue;
            }
            movements.put(piece.code(), movementsForPiece(piece, board));
        }

        BoardState state = new BoardState(pieces.size());

        merge2(state, board.cells(), pieces, movements, listener);

        System.out.println("time (s): " + (((double) System.currentTimeMillis() - (double) s1) / 1000));

        return state.getConfigCount();
    }

    private void merge2(BoardState state, Collection<Cell> cells, List<Piece> pieces, Map<Integer, Map<Cell, Collection<Cell>>> movementsMap, BoardServiceListener listener) {
        int depth = state.getDepth();
        Piece piece = pieces.get(depth);
        Map<Cell, Collection<Cell>> movements = movementsMap.get(piece.code());

        for (Cell cell : cells) {
            if (state.isBeforeLast(cell)) {
                continue;
            }
            Collection<Cell> cellMovements = movements.get(cell);
            if (state.overlap(cell, cellMovements)) {
                continue;
            }
            state.add(piece, cell, cellMovements);
            state.increaseDepth();
            if (depth == pieces.size() - 1) {
                state.increaseConfigCount();
                if (listener != null) {
                    listener.onItem(state);
                }
            } else {
                List<Cell> next = new ArrayList<Cell>(cells);
                next.removeAll(cellMovements);
                if (pieces.get(state.getDepth()).code() == piece.code()) {
                    state.putLast(cell);
                }
                merge2(state, next, pieces, movementsMap, listener);
            }
            state.decreaseDepth();
        }
    }
}
