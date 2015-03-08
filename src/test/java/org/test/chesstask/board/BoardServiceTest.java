package org.test.chesstask.board;

import org.junit.Test;
import org.test.chesstask.app.Input;
import org.test.chesstask.piece.Piece;
import org.test.chesstask.piece.impl.*;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardServiceTest {

    private BoardService service = new BoardService();
    private TestBoardServiceListener listener = new TestBoardServiceListener();

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

        Collection<String> against = new TreeSet<String>();

        against.add("12R31K33K");
        against.add("21R13K33K");
        against.add("23R11K31K");
        against.add("32R11K13K");

        assertBoards(against, 3, 3, pieces);
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

        Collection<String> against = new TreeSet<String>();

        against.add("11R33R22N24N42N44N");
        against.add("12R34R21N23N41N43N");
        against.add("13R31R22N24N42N44N");
        against.add("14R32R21N23N41N43N");
        against.add("21R43R12N14N32N34N");
        against.add("22R44R11N13N31N33N");
        against.add("23R41R12N14N32N34N");
        against.add("24R42R11N13N31N33N");

        assertBoards(against, 4, 4, pieces);
    }

    private void assertBoards(Collection<String> against, int x, int y, Piece... pieces) {
        Input input = new Input();
        input.setX(x);
        input.setY(y);
        for(Piece piece : pieces) {
            input.addPiece(piece);
        }
        service.threatFreeConfigurations(input, listener);
        Collection<String> what = listener.getItems();
        assertEquals(against.size(), what.size());
        for (String state : against) {
            assertTrue(what.contains(state));
        }
        what.clear();
    }

    @Test
    public void testThreatFreeConfigurations() {
        Input input = new Input();
        input.setX(7);
        input.setY(7);
        input.addPiece(new Queen());
        input.addPiece(new Queen());
        input.addPiece(new King());
        input.addPiece(new King());
        input.addPiece(new Bishop());
        input.addPiece(new Bishop());
        input.addPiece(new Knight());

        assertEquals(3063828, new BoardService().threatFreeConfigurations(input, null));
    }
}
