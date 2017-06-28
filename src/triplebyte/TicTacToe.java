package triplebyte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 212595974 on 5/9/2017.
 */
enum Content {
    Empty, Cross, Nought
}

/**
 * game state
 */
enum GameState {
    PLAYING, DRAW, CROSS_WON, NOUGHT_WON
}
/**
 * a class represents a generic board for marking Xs and Os
 */
class Board {
    Content[][] model = null;
    //components of separator line of board
    final String LINE_CELL = "---";
    final String LINE_UNIT = "-";
    final String CELL_SEPARATOR = "|";
    private String rowSeparator = null;
    private Person winder;

    /**
     * constructor of Board that initiates model of board, as well as row separator
     *
     * @param row
     * @param col
     */
    public Board(int row, int col) {
        if (row < 0 || col < 0)
            throw new IllegalArgumentException("please provide valid row and col with values greater than 0.");
        if (row != col) throw new IllegalArgumentException("It should has same rows and columns");
        this.model = new Content[row][col];
        resetGame();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < col; i++) {
            sb.append(LINE_CELL).append(LINE_UNIT);
        }
        rowSeparator = sb.toString();
    }

    /**
     * print cell according to cell's content stored in model
     *
     * @param content
     */
    public void printCell(Content content) {
        switch (content) {
            case Empty:
                System.out.print("   ");
                break;
            case Nought:
                System.out.print(" O ");
                break;
            case Cross:
                System.out.print(" X ");
                break;
        }
    }

    /**
     * print board to display model on board
     */
    public void printBoard() {
        for (int row = 0; row < model.length; row++) {
            //print one data row
            for (int col = 0; col < model[0].length; col++) {
                printCell(model[row][col]); // print each of the cells
                //do not print | for last element so that it'd be open
                if (col != model[0].length - 1) {
                    System.out.print(CELL_SEPARATOR);   // print vertical separator
                }
            }
            //new row for separator line
            System.out.println();

            if (row != model.length - 1) {
                //rowSeparator="-----------";
                System.out.println(rowSeparator); // print horizontal partition
            }
        }
        //go to new line after printing
        System.out.println();
    }

    public void setCell(int row, int col, Content content) {
        if (row < 0 || row > model.length || col < 0 || col > model[0].length)
            throw new ArrayIndexOutOfBoundsException();
        //only update it when it's empty
        if (model[row][col] == Content.Empty) {
            model[row][col] = content;
        }
    }
    public Content getCell(int row, int col){
        return model[row][col];
    }
    public void resetGame() {
        for (int i = 0; i < model.length; i++)
            for (int j = 0; j < model[0].length; j++)
                model[i][j] = Content.Empty;
    }
    public int getRows(){return model.length;}
    public int getColss(){return model[0].length;}
}

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class Instruction{
    int col,row;
    Content value;
    String playerName;
    public Instruction(String player,int col, int row,Content value){
        this.col = col;
        this.row = row;
        this.value = value;
        this.playerName = player;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Content getValue() {
        return value;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
/**
 * a game is about two persons and one board,
 * plus sequence of plays
 */
abstract class  Game {
    Person player1, player2, nextPlayer;
    protected Board board;
    protected GameState state = GameState.PLAYING;
    List<Instruction> steps = new ArrayList<>();//like logging, for replay purpose
    public Game(Person player1, Person player2, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        nextPlayer = player1;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Person getPlayer1() {
        return player1;
    }

    public Person getPlayer2() {
        return player2;
    }

    public Person getNextPlayer() {
        return nextPlayer;
    }

    /**
     * assume player1 always play cross
     *
     * @param col
     * @param row
     * @return
     */
    public void play(int col, int row) {
        if (nextPlayer == player1) {
            board.setCell(col, row, Content.Cross);
            steps.add(new Instruction(nextPlayer.getName(),col,row,Content.Cross));
            nextPlayer = player2;
        } else {
            board.setCell(col, row, Content.Nought);
            steps.add(new Instruction(nextPlayer.getName(),col,row,Content.Nought));
            nextPlayer = player1;
        }

    }

    public Person getAnotherPlayer(Person player) {
        if (player1==player)return player2;
        if (player2==player)return player1;
        return null;
    }
    public void replay(){}

    /**
     * tell if there is a winning pattern based on current coordination
     * @param value
     * @param currentRow
     * @param currentCol
     * @return
     */
    abstract public boolean hasWon(Content value, int currentRow, int currentCol);
    abstract public boolean isDraw();
    abstract public void updateState(Content cValue,int cRow, int cCol);
    public GameState getState(){return state;}
}
class TicTecTeoGame extends Game{
    public TicTecTeoGame(Person player1,Person player2){
        super(player1,player2, new Board(3,3));
    }

    @Override
    public boolean hasWon(Content value, int currentRow, int currentCol) {
        {
            return (board.getCell(currentRow,0) == value         // 3-in-the-row
                    && board.getCell(currentRow,1) == value
                    && board.getCell(currentRow,2) == value
                    || board.getCell(0,currentCol) == value      // 3-in-the-column
                    && board.getCell(1,currentCol) == value
                    && board.getCell(2,currentCol) == value
                    || currentRow == currentCol            // 3-in-the-diagonal
                    && board.getCell(0,0) == value
                    && board.getCell(1,1) == value
                    && board.getCell(2,2) == value
                    || currentRow + currentCol == 2  // 3-in-the-opposite-diagonal
                    && board.getCell(0,2) == value
                    && board.getCell(1,1) == value
                    && board.getCell(2,0) == value);
        }
    }


    /**
     * it does not predict a draw
     */
    @Override
    public boolean isDraw() {
        for (int row = 0; row < getBoard().getRows(); ++row) {
            for (int col = 0; col < getBoard().getColss(); ++col) {
                if (getBoard().getCell(row,col) == Content.Empty) {
                    return false;  // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // no empty cell, it's a draw
    }

    @Override
    public void updateState(Content cValue,int cRow,int cCol) {
        if (hasWon(cValue, cRow, cCol)) {  // check if winning move
            state = (cValue== Content.Cross) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (isDraw()) {  // check for draw
            state = GameState.DRAW;
        }
    }
}
public class TicTacToe {
    //map for fast finding game according to player
    Map<Person, Game> player1Game = new HashMap<Person, Game>();
    Map<Person, Game> player2Game = new HashMap<Person, Game>();

    public TicTacToe() {

    }

    public void startNewGame(Person player1, Person player2) {
        //tic tec toe is 3x3 board
        Game g = new TicTecTeoGame(player1, player2);
        player1Game.put(player1, g);
        player2Game.put(player2, g);
    }

    public Game getGame(Person player) {
        if (player1Game.containsKey(player)) return player1Game.get(player);
        if (player2Game.containsKey(player)) return player2Game.get(player);
        return null;
    }

    public void play(Game g, int col, int row) {
        g.play(col, row);
    }

    public void printBoard(Game game) {
        game.getBoard().printBoard();
    }

    public void printBoard(Person player) {
        if (player1Game.containsKey(player)) {
            player1Game.get(player).getBoard().printBoard();
        }
        if (player2Game.containsKey(player)) {
            player2Game.get(player).getBoard().printBoard();
        }
    }


    public void leaveGame(Person player) {
        Person another;
        if (player1Game.containsKey(player)) {
            another = player1Game.get(player).getAnotherPlayer(player);
            player1Game.remove(player);
            player2Game.remove(another);
        }
        if (player2Game.containsKey(player)) {
            another = player2Game.get(player).getAnotherPlayer(player);
            player2Game.remove(player);
            player1Game.remove(another);
        }
    }

    public static void main(String[] args) {
        TicTacToe o = new TicTacToe();
        Person p1 = new Person("P1");
        Person p2 = new Person("P2");
        o.startNewGame(p1, p2);
        Game g = o.getGame(p1);
        o.play(g, 0, 0);
        o.play(g, 1, 1);
        o.play(g, 0, 1);
        o.play(g, 1, 0);
        o.play(g, 0, 2);
        o.printBoard(g);
        System.out.println("has winner?"+g.hasWon(Content.Cross,0,1));
    }

}
