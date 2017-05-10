package triplebyte;

import java.text.RuleBasedCollator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;



/**
 * Created by 212595974 on 5/9/2017.
 */
enum Content{
    Empty,Cross,Nought
        }
/**
 * a class represents a board
 */
class Board{
    Content[][] model = null;
    //components of separator line of board
    final String LINE_CELL = "---";
    final String LINE_UNIT = "-";
    final String CELL_SEPARATOR = "|";
    private String rowSeparator = null;

    /**
     * constructor of Board that initiates model of board, as well as row separator
     * @param row
     * @param col
     */
    public Board(int row,int col){
        if(row<0||col<0)throw new IllegalArgumentException("please provide valid row and col with values greater than 0.");
        if(row!=col)throw new IllegalArgumentException("It should has same rows and columns");
        this.model = new Content[row][col];
        resetGame();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<col;i++){
            sb.append(LINE_CELL).append(LINE_UNIT);
        }
        rowSeparator = sb.toString();
    }

    /**
     * print cell according to cell's content stored in model
     * @param content
     */
    public  void printCell(Content content) {
        switch (content) {
            case Empty:  System.out.print("   "); break;
            case Nought: System.out.print(" O "); break;
            case Cross:  System.out.print(" X "); break;
        }
    }
    /**
     * print board to display model on board
     */
    public  void printBoard() {
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
                System.out.println(rowSeparator); // print horizontal partition
            }
        }
        //go to new line after printing
        System.out.println();
    }
    public void setContent(int row, int col, Content content){
        if(row<0||row>model.length||col<0||col>model[0].length)throw new ArrayIndexOutOfBoundsException();
        //only update it when it's empty
        if(model[row][col]==Content.Empty) {
            model[row][col] = content;
        }
    }
    public void resetGame(){
        for(int i=0;i<model.length;i++)
            for(int j=0;j<model[0].length;j++)
                model[i][j] = Content.Empty;
    }

}
class Person{
    String name;
    public Person(String name){
        this.name = name;
    }
    public String getName(){return name;}
}
class Game{
    Person player1,player2, nextPlayer;
    Board board;
    public Game(Person player1, Person player2,Board board){
        this.player1 = player1;
        this.player2 = player2;
        nextPlayer = player1;
        this.board = board;
    }
    public Board getBoard(){
        return board;
    }

    public Person getPlayer1() {
        return player1;
    }

    public Person getPlayer2() {
        return player2;
    }

    /**
     * assume player1 always play cross
     * @param col
     * @param row
     * @return
     */
    public void play(int col,int row){
        if(nextPlayer==player1){
            board.setContent(col,row,Content.Cross);
            nextPlayer = player2;
        }
        else{
            board.setContent(col,row,Content.Nought);
            nextPlayer = player1;
        }

    }

}
public class TicTacToe {
    //map for fast finding game according to player
    Map<Person,Game> player1Game = new HashMap<Person,Game>();
    Map<Person,Game> player2Game = new HashMap<Person,Game>();

    public TicTacToe(){

    }

    public void startNewGame(Person player1, Person player2, int dimension){
        Board board = new Board(dimension,dimension);
        Game g = new Game(player1,player2,board);
        player1Game.put(player1,g);
        player2Game.put(player2,g);
    }
    public void play(Person p,int col,int row){
        if(player1Game.containsKey(p)&&player1Game.get(p).nextPlayer == p){
            player1Game.get(p).play(col,row);
        }
        if(player2Game.containsKey(p)&&player2Game.get(p).nextPlayer == p){
            player2Game.get(p).play(col,row);
        }
    }
    public  void printBoard(Game game) {
        game.getBoard().printBoard();
    }
    public  void printBoard(Person player) {
        if(player1Game.containsKey(player)){player1Game.get(player).getBoard().printBoard();}
        if(player2Game.containsKey(player)){player2Game.get(player).getBoard().printBoard();}
    }
    public void leaveGame(Person player){
            if(player1Game.containsKey(player)){player1Game.remove(player);}
            if(player2Game.containsKey(player)){player2Game.remove(player);}
    }

    public static void main(String[] args){
    TicTacToe o = new TicTacToe();
    Person p1 = new Person("P1");
        Person p2 = new Person("P2");
    o.startNewGame(p1,p2,3);
    o.play(p1,0,0);
    o.play(p2,1,1);
    o.printBoard(p1);
}

}
