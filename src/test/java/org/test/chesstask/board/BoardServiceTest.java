package org.test.chesstask.board;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.test.chesstask.app.ChessTaskApp;
import org.test.chesstask.app.Input;
import org.test.chesstask.app.MessagesBundle;
import org.test.chesstask.piece.Piece;
import org.test.chesstask.piece.impl.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardServiceTest {

    private BoardService service;

    @Before
    public void before() {
        service = new BoardService();
    }

    @Test
    public void testMovementsForPiece() {
        Board board = new Board(3, 3);
        Piece king = new King();

        Map<Cell, Collection<Cell>> movements = service.movementsForPiece(king, board);

        assertEquals(9, movements.size());
    }

    @Test
    public void testThreatFreeConfigurationsTwoKingsRook() {
        testThreatFreeConfigurationsTwoKingsRook(new King(), new Rook(), new King());
        testThreatFreeConfigurationsTwoKingsRook(new King(), new King(), new Rook());
        testThreatFreeConfigurationsTwoKingsRook(new Rook(), new King(), new King());
    }

    private void testThreatFreeConfigurationsTwoKingsRook(Piece... pieces) {
        Board board = new Board(3, 3);
        Collection<String> results = service.threatFreeConfigurations(Arrays.asList(pieces), board);

        assertEquals(4, results.size());

        List<BoardState> boards = new ArrayList<BoardState>();

        boards.add(BoardBuilder.builder().board().piece(1, 1, new King()).piece(1, 3, new King()).piece(3, 2, new Rook()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 1, new King()).piece(3, 1, new King()).piece(2, 3, new Rook()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 3, new King()).piece(2, 1, new Rook()).piece(3, 3, new King()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 2, new Rook()).piece(3, 1, new King()).piece(3, 3, new King()).result());

        assertBoards(boards, results);
    }

    @Test
    public void testThreatFreeConfigurationsTwoRooksFourKnights() {
        testThreatFreeConfigurationsTwoRooksFourKnights(
                new Rook(), new Rook(), new Knight(), new Knight(), new Knight(), new Knight()
        );
        testThreatFreeConfigurationsTwoRooksFourKnights(
                new Rook(), new Knight(), new Knight(), new Knight(), new Knight(), new Rook()
        );
        testThreatFreeConfigurationsTwoRooksFourKnights(
                new Knight(), new Knight(), new Knight(), new Knight(), new Rook(), new Rook()
        );
        testThreatFreeConfigurationsTwoRooksFourKnights(
                new Rook(), new Knight(), new Knight(), new Rook(), new Knight(), new Knight()
        );
    }

    private void testThreatFreeConfigurationsTwoRooksFourKnights(Piece... pieces) {
        Board board = new Board(4, 4);
        Collection<String> results = service.threatFreeConfigurations(Arrays.asList(pieces), board);

        List<BoardState> boards = new ArrayList<BoardState>();

        boards.add(BoardBuilder.builder().board().piece(1, 2, new Knight()).piece(1, 4, new Knight()).piece(2, 3, new Rook())
                .piece(3, 2, new Knight()).piece(3, 4, new Knight()).piece(4, 1, new Rook()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 2, new Knight()).piece(1, 4, new Knight()).piece(2, 1, new Rook())
                .piece(3, 2, new Knight()).piece(3, 4, new Knight()).piece(4, 3, new Rook()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 1, new Rook()).piece(2, 2, new Knight()).piece(2, 4, new Knight())
                .piece(3, 3, new Rook()).piece(4, 2, new Knight()).piece(4, 4, new Knight()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 3, new Rook()).piece(2, 2, new Knight()).piece(2, 4, new Knight())
                .piece(3, 1, new Rook()).piece(4, 2, new Knight()).piece(4, 4, new Knight()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 2, new Rook()).piece(2, 1, new Knight()).piece(2, 3, new Knight())
                .piece(3, 4, new Rook()).piece(4, 1, new Knight()).piece(4, 3, new Knight()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 4, new Rook()).piece(2, 1, new Knight()).piece(2, 3, new Knight())
                .piece(3, 2, new Rook()).piece(4, 1, new Knight()).piece(4, 3, new Knight()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 1, new Knight()).piece(1, 3, new Knight()).piece(2, 4, new Rook())
                .piece(3, 1, new Knight()).piece(3, 3, new Knight()).piece(4, 2, new Rook()).result());
        boards.add(BoardBuilder.builder().board().piece(1, 1, new Knight()).piece(1, 3, new Knight()).piece(2, 2, new Rook())
                .piece(3, 1, new Knight()).piece(3, 3, new Knight()).piece(4, 4, new Rook()).result());

        assertEquals(8, results.size());

        assertBoards(boards, results);
    }

    private void assertBoards(List<BoardState> against, Collection<String> what) {
        for (BoardState state : against) {
            assertTrue(what.contains(state.toString()));
        }
    }

    ////

    //@Test
    @Ignore
    public void testThreatFreeConfigurations() {
        Input input = new Input();
        input.setX(7);
        input.setY(7);
        input.addPiece(new Queen());
        input.addPiece(new Queen());
        input.addPiece(new Bishop());
        input.addPiece(new Bishop());
        input.addPiece(new King());
        input.addPiece(new King());
        input.addPiece(new Knight());

        MessagesBundle bundle = new MessagesBundle();
        bundle.load();
        ChessTaskApp app = new ChessTaskApp(bundle);

        System.out.println("result: " + app.run(input));
    }
}
