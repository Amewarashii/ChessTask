package org.test.chesstask.app;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.BoardState;
import org.test.chesstask.board.Cell;

import java.io.IOException;
import java.util.Collection;

public class BoardPainter {

    private ResultWriter writer;
    private StringBuilder builder = new StringBuilder();

    public BoardPainter(ResultWriter writer) {
        this.writer = writer;
    }

    public void paint(Board board) throws IOException {
        builder.append("\r\n");

        for (int i = 1; i < board.upperX(); i++) {
            for (Cell cell : board.cells(i)) {
                builder.append(" ");
                if (board.occupied(cell)) {
                    builder.append(board.pieceForCell(cell).id());
                } else {
                    builder.append("*");
                }
                builder.append(" ");
            }
            builder.append("\r\n");
        }

        writer.write(builder);
        builder.delete(0, builder.length());
    }

    public void paint(Board board, Collection<BoardState> results) throws IOException {
        for (BoardState item : results) {
            for (Cell c : item.getState().keySet()) {
                board.locateIn(c, c.getPiece());
            }
            paint(board);
            board.clear();
        }
    }
}
