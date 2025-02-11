package com.gamewerks.blocky.engine;

import java.io.IOException;
import java.util.HashMap;

import com.gamewerks.blocky.util.Loader;
import com.gamewerks.blocky.util.Position;

public class Piece {
    
    private static HashMap ROTATION_DATA = null;
    private PieceKind kind;
    private int orientation;
    private Position pos;
    
    public Piece(PieceKind kind, Position pos) {
        this.kind = kind;
        orientation = 0;
        this.pos = pos;
        try {
            ROTATION_DATA = Loader.loadAllRotationData();
        } catch (IOException ex) {
            System.out.println("Exception occurred loading rotation data");
            System.exit(-1);
        }
    }
    // the try-catch method used to be wrapped by static, it caused InitializationError, 
    // so we fix that by adding it into the piece constructor, and now it is 
    // working. But not sure if we need to keep make it static, we will see.
    
    public Position getPosition() {
        return pos;
    }
    
    public void moveTo(Position p) {
        pos = p;
    }
    
    public boolean[][] getLayout() {
        return ((boolean[][][]) ROTATION_DATA.get(kind))[orientation];
    }
    
    public void rotate(boolean dir) {
        if (dir) {
            orientation = (orientation + 1) % 4;
        } else {
            int k = orientation - 1;
            orientation = k < 0 ? 3 : k;
        }
    }
}
