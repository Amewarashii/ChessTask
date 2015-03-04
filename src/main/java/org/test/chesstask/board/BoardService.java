package org.test.chesstask.board;

import org.test.chesstask.common.Utilities;
import org.test.chesstask.piece.Piece;

import java.util.*;

public class BoardService {

    public Collection<Piece[]> possiblePiecesSeq(Piece... pieces) {
        return Utilities.permutations(pieces, pieces.length);
    }

    public BoardState movementsForPiece(Piece piece, Board board) {
        BoardState result = new BoardState();
        for (int i = 1; i < board.upperX(); i++) {
            for (Cell cell : board.cells(i)) {
                result.add(new Cell(cell, piece), piece.possibleMoves(cell, board));
            }
        }
        return result;
    }

    public Map<Piece, BoardState> movementsForPiece(Collection<Piece> pieces, Board board) {
        Map<Piece, BoardState> result = new HashMap<Piece, BoardState>();
        for (Piece piece : pieces) {
            result.put(piece, movementsForPiece(piece, board));
        }
        return result;
    }

    public Collection<BoardState> threatFreeConfigurations(Board board, Collection<Piece> pieces) {
        Set<BoardState> result = new HashSet<BoardState>();
        Map<Piece, BoardState> piecesMovements = movementsForPiece(pieces, board);
        for (Piece[] seq : possiblePiecesSeq(pieces.toArray(new Piece[pieces.size()]))) {
            threatFreeConfigurations(seq, result, piecesMovements);
        }
        return result;
    }

    private void threatFreeConfigurations(Piece[] pieces, Collection<BoardState> result, Map<Piece, BoardState> piecesMovements) {
        for (int i = 0; i < pieces.length; i++) {
            BoardState state = piecesMovements.get(pieces[i]);
            for (Cell cell : state.locations()) {
                BoardState cellState = new BoardState(cell, state.movementsFor(cell));
                for (int j = 0; j < pieces.length; j++) {
                    if (j == i) {
                        continue;
                    }
                    if ((cellState = merge(cellState, piecesMovements.get(pieces[j]))) == null) {
                        break;
                    }
                }
                if (cellState != null) {
                    result.add(cellState);
                }
            }
        }
    }

    private BoardState merge(BoardState state, BoardState movements) {
        for (Cell location : movements.locations()) {
            Set<Cell> cellMovements = movements.movementsFor(location);
            if (state.contains(location) || state.intersectWith(cellMovements)) {
                continue;
            }
            BoardState next = new BoardState(state);
            next.add(location, cellMovements);
            return next;
        }
        return null;
    }
}
