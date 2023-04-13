import exceptions.InvalidPositionException;
import exceptions.PositionException;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Game {
    private Player[] players;
    private int index;
    private String[][] gameBoard = new String[3][3];
    private boolean isWon;
    private boolean isTie;

    public Game() {
        players = new Player[2];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }
        for (int i = 0; i < gameBoard.length; i++) {
            Arrays.fill(gameBoard[i], " ");
        }
    }

    public void registerPlayers(String name, int index) {
        players[index].setName(name);
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getCurrentPlayersName() {
        return players[index].getName();
    }

    public void setSymbol(int position) throws PositionException, InvalidPositionException {
        int row = (position - 1) / 3;
        int column = (position - 1) % 3;
        checkNumber(position);
        validatePosition(row, column);
        gameBoard[row][column] = players[index].getSymbol();
    }

    private void validatePosition(int row, int column) throws PositionException {
        if (!gameBoard[row][column].equals(" ")) throw new PositionException("Position already taken!");
    }

    public void play(int position) throws PositionException, InvalidPositionException{
        setSymbol(position);
        checkWinning();
        resetCurrentPlayer();
    }

    public void checkRowOne() {
        String columnOne = gameBoard[0][0];
        String columnTwo = gameBoard[0][1];
        String columnThree = gameBoard[0][2];
        if (Stream.of(columnOne, columnTwo, columnThree).noneMatch(s -> s.equals(" "))) {
            if (columnOne.equals(columnTwo) && columnTwo.equals(columnThree)) {
                isWon = true;
                index--;
            }
        }
    }

    public void checkRowTwo() {
        String columnFour = gameBoard[1][0];
        String columnFive = gameBoard[1][1];
        String columnSix = gameBoard[1][2];
        if (Stream.of(columnFour, columnFive, columnSix).noneMatch(s -> s.equals(" "))) {
            if (columnFour.equalsIgnoreCase(columnFive) && columnFour.equalsIgnoreCase(columnSix)) {
                isWon = true;
                index--;
            }
        }

    }

    public void checkRowThree() {
        String columnSeven = gameBoard[2][0];
        String columnEight = gameBoard[2][1];
        String columnNine = gameBoard[2][2];
        if (Stream.of(columnSeven, columnEight, columnNine).noneMatch(s -> s.equals(" "))) {
            if (columnSeven.equalsIgnoreCase(columnEight) && columnEight.equalsIgnoreCase(columnNine)) {
                isWon = true;
                index--;
            }
        }
    }

    public void checkSlantOne() {
        String columnOne = gameBoard[0][0];
        String columnFive = gameBoard[1][1];
        String columnNine = gameBoard[2][2];
        if (Stream.of(columnOne, columnFive, columnNine).noneMatch(s -> s.equals(" "))) {
            if (columnOne.equalsIgnoreCase(columnFive) && columnFive.equalsIgnoreCase(columnNine)) {
                isWon = true;
                index--;
            }
        }
    }

    public void checkSlantTwo() {
        String columnThree = gameBoard[0][2];
        String columnFive = gameBoard[1][1];
        String columnSeven = gameBoard[2][0];

        if (Stream.of(columnThree, columnFive, columnSeven).noneMatch(s -> s.equals(" "))) {
            if (columnThree.equalsIgnoreCase(columnFive) && columnFive.equalsIgnoreCase(columnSeven)) {
                isWon = true;
                index--;
            }
        }
    }

    public void checkWinning() {
        checkRowOne();
        checkRowTwo();
        checkRowThree();
        checkColumnOne();
        checkColumnTwo();
        checkColumnThree();
        checkSlantOne();
        checkSlantTwo();
        checkTie();
    }
    private void checkColumnThree() {
        String columnThree = gameBoard[0][2];
        String columnSix = gameBoard[1][2];
        String columnNine = gameBoard[2][2];
        if (Stream.of(columnThree, columnSix, columnNine).noneMatch(s -> s.equals(" "))) {
            if (columnThree.equalsIgnoreCase(columnSix) && columnSix.equalsIgnoreCase(columnNine)) {
                isWon = true;
                index--;
            }
        }
    }

    private void checkColumnTwo() {
        String columnTwo = gameBoard[0][1];
        String columnFive = gameBoard[1][1];
        String columnEight = gameBoard[2][1];
        if (Stream.of(columnTwo, columnFive, columnEight).noneMatch(s -> s.equals(" "))) {
            if (columnTwo.equalsIgnoreCase(columnFive) && columnFive.equalsIgnoreCase(columnEight)) {
                isWon = true;
                index--;
            }
        }
    }

    private void checkColumnOne() {
        String columnOne = gameBoard[0][0];
        String columnFour = gameBoard[1][0];
        String columnSeven = gameBoard[2][0];
        if (Stream.of(columnOne, columnFour, columnSeven).noneMatch(s -> s.equals(" "))) {
            if (columnOne.equalsIgnoreCase(columnFour) && columnFour.equalsIgnoreCase(columnSeven)) {
                isWon = true;
                index--;
            }
        }
    }

    private void checkNumber(int position) throws InvalidPositionException {
        if (position < 1 || position > 9) throw new InvalidPositionException("Invalid position! ");
    }

    public void checkTie(){
        int count = 0;
        for (int i = 0; i < gameBoard.length; i++) for (int j = 0; j < gameBoard[i].length; j++) if (Objects.equals(gameBoard[i][j], " ")) count++;
        if (count == 0) isTie = true;
    }

    public boolean isWon() {
        return isWon;
    }
    public boolean isTie() {
        return isTie;
    }

    public String[][] displayGameBoard() {
        return gameBoard;
    }

    private void resetCurrentPlayer() {
        index++;
        if (index == players.length) index = 0;
    }

    public void giveSymbol() {
        players[0].setSymbol("X");
        players[1].setSymbol("0");
    }

}

