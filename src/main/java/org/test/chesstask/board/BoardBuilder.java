package org.test.chesstask.board;

import org.test.chesstask.piece.Piece;

import java.util.ArrayList;

public class BoardBuilder {

    private BoardState board;

    private BoardBuilder() {}

    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    public BoardBuilder board() {
        this.board = new BoardState();
        return this;
    }

    public BoardBuilder piece(int x, int y, Piece piece) {
        board.add(new Cell(x, y, piece), new ArrayList<Cell>());
        return this;
    }

    public BoardState result() {
        return board;
    }
}
