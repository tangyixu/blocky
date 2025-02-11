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
    public static int currentIndex = 0;
    
    /**
     * Shuffle the ALL array for PieceKind.
     * @return a shuffled array of PieceKind.ALL.
     */
    public static PieceKind[] shuffleIfNeeded() {
        if(PieceKind.currentIndex == 6) {
           PieceKind.currentIndex = 0;
           PieceKind[] shuffledALL = new PieceKind[7];
           for(int n = 6; n >= 0; n--) {
              int index = (int) (Math.random()* n);
              if (index != n) {
                  swap(index, n);
              }
              shuffledALL[n] = PieceKind.ALL[n];   
           }  
        return shuffledALL; 
        } else {
          PieceKind.currentIndex++;
          return PieceKind.ALL;      
        }      
    }
    
    /**
     * Swap the PieceKind at index1 and index2 in ALL 
     * @param index1
     * @param index2 
     */
    public static void swap(int index1, int index2) {
        PieceKind val = PieceKind.ALL[index1];
        PieceKind.ALL[index1] = PieceKind.ALL[index2];
        PieceKind.ALL[index2] = val;
    }
}
