package com.octro.lobby.Entity;

import java.util.ArrayList;
import java.util.List;

public class ChessGameState {

    private char[][] board; // Use 'char' instead of 'String'
    private List<String> players;
    private Integer currentPlayer;

    public ChessGameState(String user1,String user2) {
        this.board = new char[8][8];
        initializeBoard();
        players =new ArrayList<>();
        players.add(user1);
        players.add(user2);
         currentPlayer =0;
    }

    public void applyMove(String move) {
        String from = move.substring(0, 2);
        String to = move.substring(2, 4);
        int fromRow = Character.getNumericValue(from.charAt(1));
        int fromCol = from.charAt(0) - 'A';
        int toRow =Character.getNumericValue(to.charAt(1));
        int toCol = to.charAt(0) - 'A';

        board[toRow][toCol] = board[fromRow][fromCol];
        board[fromRow][fromCol] = '0';

        currentPlayer = (currentPlayer + 1) % 2;
    }

    private void initializeBoard() {
        board[0][0] = 'R'; board[0][1] = 'N'; board[0][2] = 'B'; board[0][3] = 'Q';
        board[0][4] = 'K'; board[0][5] = 'B'; board[0][6] = 'N'; board[0][7] = 'R';
        for (int i = 0; i < 8; i++) {
            board[1][i] = 'P';
        }

        board[7][0] = 'r'; board[7][1] = 'n'; board[7][2] = 'b'; board[7][3] = 'q';
        board[7][4] = 'k'; board[7][5] = 'b'; board[7][6] = 'n'; board[7][7] = 'r';
        for (int i = 0; i < 8; i++) {
            board[6][i] = 'p';
        }

        for (int i = 2; i <= 5; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '0';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public Integer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Integer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}
