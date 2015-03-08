package org.test.chesstask.app;

import org.test.chesstask.board.BoardService;
import org.test.chesstask.piece.Piece;
import org.test.chesstask.piece.PieceType;
import org.test.chesstask.piece.impl.*;

import java.util.Scanner;

public class ChessTaskAppExecutor {

    public static void main(String... args) {
        MessagesBundle messages = new MessagesBundle();
        messages.load();
        Input config;
        if(args.length == 1 && args[0].equals("-default")) {
            config = new Input();
            config.setX(7);
            config.setY(7);
            config.addPiece(new Queen());
            config.addPiece(new Queen());
            config.addPiece(new King());
            config.addPiece(new King());
            config.addPiece(new Bishop());
            config.addPiece(new Bishop());
            config.addPiece(new Knight());
        } else {
            config = interact(messages);
        }

        System.out.println(messages.getProcessingMsg(config.toString()));
        System.out.println(messages.getResultMsg(new BoardService().threatFreeConfigurations(config, null), config.toString()));
    }

    private static Input interact(MessagesBundle messages) {
        Input config = new Input();
        Scanner inputReader = new Scanner(System.in);

        config.setX(integerInput(messages.getInviteXMsg(), messages, inputReader));
        config.setY(integerInput(messages.getInviteYMsg(), messages, inputReader));

        String enough = "";
        while (!enough.equalsIgnoreCase("N")) {
            PieceType pieceType = null;

            while (pieceType == null) {
                System.out.println(messages.getInvitePieceMsg());
                String arg = inputReader.nextLine();
                pieceType = PieceType.find(arg.charAt(0));
                if (pieceType == null) {
                    System.err.println(messages.getIncorrectInputMsg(arg));
                }
            }

            int count = integerInput(messages.getInvitePieceCountMsg(), messages, inputReader);

            while (count > 0) {
                config.addPiece(piece(pieceType));
                --count;
            }

            System.out.println(messages.getInviteMorePieceMsg());
            enough = inputReader.nextLine();
        }
        return config;
    }

    private static int integerInput(String message, MessagesBundle messages, Scanner inputReader) {
        int r = 0;
        while (r <= 0) {
            System.out.println(message);
            String arg = inputReader.nextLine();
            try {
                r = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                System.err.println(messages.getIncorrectInputMsg(arg));
            }
        }
        return r;
    }

    private static Piece piece(PieceType type) {
        switch (type) {
            case KNIGHT:
                return new Knight();
            case KING:
                return new King();
            case ROOK:
                return new Rook();
            case BISHOP:
                return new Bishop();
            case QUEEN:
                return new Queen();
            default:
                return null;
        }
    }
}
