package org.test.chesstask.board;

import org.test.chesstask.piece.Piece;

public class BoardBuilder {

    private Board board;

    private BoardBuilder() {}

    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    public BoardBuilder board(int x, int y) {
        this.board = new Board(x, y);
        return this;
    }

    public BoardBuilder piece(int x, int y, Piece piece) {
        board.locateIn(board.cell(x, y), piece);
        return this;
    }

    public Board result() {
        return board;
    }
}
