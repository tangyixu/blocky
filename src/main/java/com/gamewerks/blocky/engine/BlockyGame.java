package com.gamewerks.blocky.engine;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;

public class BlockyGame {
    private static final int LOCK_DELAY_LIMIT = 30;
    
    private Board board;
    private Piece activePiece;
    private Direction movement;
    
    private int lockCounter;
    
    public BlockyGame() {
        board = new Board();
        movement = Direction.NONE;
        lockCounter = 0;
        trySpawnBlock();
    }
    
    private void trySpawnBlock() {
        if (activePiece == null) { 
            activePiece = new Piece(PieceKind.I,//PieceKind.shuffleIfNeeded()[PieceKind.currentIndex], 
                    new Position(20,20));//(int) Math.random()* Constants.BOARD_HEIGHT, 
                                 //(int) Math.random()* Constants.BOARD_WIDTH));
//            if (board.collides(activePiece)) {
//                System.exit(0);
//            } it's not supposed to collide if it is the first piece active.
// So comment that out just to check if the program could run, and it 
        }
    }
    
    private void processMovement() {
        Position nextPos;
        switch(movement) {
        case NONE:
            nextPos = activePiece.getPosition();
            break;
        case LEFT:
            nextPos = activePiece.getPosition().add(0, -1);
            break;
        case RIGHT:
            nextPos = activePiece.getPosition().add(0, 1);
        default:
            throw new IllegalStateException("Unrecognized direction: " + movement.name());
        }
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            activePiece.moveTo(nextPos);
        }
    }
    
    private void processGravity() {
        Position nextPos = activePiece.getPosition().add(-1, 0);
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            lockCounter = 0;
            activePiece.moveTo(nextPos);
        } else {
            if (lockCounter < LOCK_DELAY_LIMIT) {
                lockCounter += 1;
            } else {
                board.addToBoard(activePiece);
                lockCounter = 0;
                activePiece = null;
            }
        }
    }
    
    private void processClearedLines() {
        board.deleteRows(board.getCompletedRows());
    }
    
    public void step() {
        trySpawnBlock();
        processGravity();
        processClearedLines();
    }
    
    public boolean[][] getBoard() {
        return board.getBoard();
    }
    
    public Piece getActivePiece() { 
        return activePiece; 
    }
    
    public void setDirection(Direction movement) {
        this.movement = movement; 
    }
    
    public void rotatePiece(boolean dir) { 
        activePiece.rotate(dir); 
    }
}
