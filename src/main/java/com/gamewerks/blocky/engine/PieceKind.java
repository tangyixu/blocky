package com.gamewerks.blocky.engine;

public enum PieceKind {
    I,
    J,
    L,
    O,
    S,
    T,
    Z;
    
    public static final PieceKind[] ALL = { I, J, L, O, S, T, Z };
    
    /**
     * Random draw a kind for the next piece.
     * @return a random index of ALL to decide the next kind.
     */
    public static int randomKind() {
       return (int) (Math.random()*7);
    }
}
