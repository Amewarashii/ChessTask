package org.test.chesstask.piece;

public enum PieceType {

    KING('K', 3), ROOK('R', 2), KNIGHT('N', 4), BISHOP('B', 1), QUEEN('Q', 0);

    private char id;
    private int code;

    PieceType(char id, int code) {
        this.id = id;
        this.code = code;
    }

    public char getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public static PieceType find(char id) {
        for(PieceType type : values()) {
            if(type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public static PieceType find(int id) {
        for(PieceType type : values()) {
            if(type.getCode() == id) {
                return type;
            }
        }
        return null;
    }
}
