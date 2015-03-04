package org.test.chesstask.piece;

public enum PieceType {

    KING('K'), ROOK('R'), KNIGHT('N'), BISHOP('B'), QUEEN('Q');

    private char id;

    private PieceType(char id) {
        this.id = id;
    }

    public char getId() {
        return id;
    }

    public static PieceType find(char id) {
        for(PieceType type : values()) {
            if(type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}
