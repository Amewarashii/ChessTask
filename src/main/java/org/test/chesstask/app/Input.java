package org.test.chesstask.app;

import org.test.chesstask.piece.Piece;

import java.util.*;

public class Input {

    private int x, y;
    private List<Piece> pieces = new ArrayList<Piece>();
    private Map<String, Integer> piecesCount = new HashMap<String, Integer>();

    public void addPiece(Piece piece) {
        pieces.add(piece);
        if(!piecesCount.containsKey(piece.id())) {
            piecesCount.put(piece.id(), 0);
        }
        piecesCount.put(piece.id(), piecesCount.get(piece.id()) + 1);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Board[%d,%d]", x, y));
        for(String piece : piecesCount.keySet()) {
            builder.append(" ").append(piece).append(piecesCount.get(piece));
        }
        return builder.toString();
    }
}
