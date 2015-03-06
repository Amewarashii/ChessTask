package org.test.chesstask.app;

import org.test.chesstask.board.Board;
import org.test.chesstask.board.BoardService;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessTaskApp {

    private static final Logger LOGGER = Logger.getLogger(ChessTaskApp.class.getSimpleName());

    private MessagesBundle messages;

    public ChessTaskApp(MessagesBundle messages) {
        this.messages = messages;
    }

    public int run(Input config) {
        BoardService service = new BoardService();
        Board board = new Board(config.getX(), config.getY());
        Collection<String> result = service.threatFreeConfigurations(config.getPieces(), board);

        ResultWriter writer;
        try {
            writer = new FileResultWriter();
            writer.open(config);
        } catch (IOException e) {
            System.err.print(messages.getUnableCreateFileErr(e.getMessage()));
            LOGGER.log(Level.FINEST, e.getMessage(), e);
            writer = new ConsoleOutputWriter();
        }
        try {
            for(String item : result) {
                writer.write(item);
            }
        } catch (IOException e) {
            System.err.println(messages.getUnableWriteBoardsErr(e.getMessage()));
            LOGGER.log(Level.FINEST, e.getMessage(), e);
        }
        writer.close();
        return result.size();
    }
}
