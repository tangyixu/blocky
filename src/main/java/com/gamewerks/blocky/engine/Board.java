package com.gamewerks.blocky.engine;

import java.util.LinkedList;
import java.util.List;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;

public class Board {
    private boolean[][] board;
    
    public Board() {
        board = new boolean[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
    }
    
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row <= board.length && col >= 0 && col <= board[0].length;
    }
    
    public boolean collides(Piece p) {
        return collides(p.getLayout(), p.getPosition());
    }
    
    public boolean collides(boolean[][] layout, Position pos) {
        for (int row = 0; row < layout.length; row++) {
            int boardRow = pos.row - row;
            for (int col = 0; col < layout[row].length; col++) {
                int boardCol = col + pos.col;
                if (layout[row][col]) {
                    if (!isValidPosition(boardRow, boardCol)) {
                        return true;
                    } else if (board[boardRow][boardCol]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void addToBoard(Piece p) {
        boolean[][] layout = p.getLayout();
        Position pos = p.getPosition();
        for (int row = 0; row < layout.length; row++) {
            int boardRow = pos.row - row;
            for (int col = 0; col < layout[row].length; col++) {
                int boardCol = pos.col + col;
                if (isValidPosition(boardRow, boardCol) && layout[row][col]) {
                    board[boardRow][boardCol] = true;
                }
            }
        }
    }
    
    public void deleteRow(int n) {
        for (int row = 0; row < n - 1; row++) {
            for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
                board[row][col] = board[row+1][col];
            }
        }
        for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
            board[n][col] = false;
        }
    }
    
    public void deleteRows(List rows) {
        for (int i = 0; i < rows.size(); i++) {
            int row = (Integer) rows.get(i);
            deleteRow(row);
        }
    }
    
    public boolean isCompletedRow(int row) {
        boolean isCompleted = true;
        for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
            isCompleted = isCompleted && board[row][col];
        }
        return isCompleted;
    }
    
    public List getCompletedRows() {
        List completedRows = new LinkedList();
        for (int row = 0; row < Constants.BOARD_HEIGHT; row++) {
            if (isCompletedRow(row)) {
                completedRows.add(board[row]);
            }
        }
        return completedRows;
    }
    
    public boolean[][] getBoard() {
        return board; 
    }
}
