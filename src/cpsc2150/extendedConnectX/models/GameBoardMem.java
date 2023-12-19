package cpsc2150.extendedConnectX.models;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * The GameBoardMem class controls the functions of a connectX game,
 * dropping tokens, and checking winning conditions
 *
 * @invariant MIN_SIZE <= row <= MAX_SIZE AND MIN_SIZE <= column <= MAX_SIZE
 * AND MIN_WIN <= numToWin >= MAX_WIN AND board = HashMap<Character, List<BoardPosition>>
 * AND [there can not be a blank space between two player tokens in the columns]
 *
 * @corresponds: aRow = row AND aCol = column self = board
 */
public class GameBoardMem extends AbsGameBoard {
    private int row;
    private int column;
    private int numToWin;
    private HashMap<Character, List<BoardPosition>> board;

    /**
     * Constructor for the GameBoardMem object that creates a blank hash map game board
     *
     * @pre None
     *
     * @post will create a hashmap for characters with values of ' ' AND row = aRow
     * AND column = aCol AND numToWin = numWin
     */
    public GameBoardMem(int aRow, int aCol, int numWin)
    {
        row = aRow;
        column = aCol;
        numToWin = numWin;

        board = new HashMap<Character, List<BoardPosition>>();
    }

    /**
     * Returns the number of rows in the board
     *
     * @return the number of rows in the board
     *
     * @pre None
     *
     * @post getNumRows = row AND self = #self
     */
    public int getNumRows()
    {
        return row;
    }

    /**
     * Returns the number of columns in the board
     *
     * @return the number of columns in the board
     *
     * @pre None
     *
     * @post getNumColumns = column AND self = #self
     */
    public int getNumColumns()
    {
        return column;
    }

    /**
     * Returns the number of tokens in a row needed to win the game
     *
     * @return the number of tokens in a row needed to win the game
     *
     * @pre None
     *
     * @post getNumToWin = numToWin AND self = #self
     */
    public int getNumToWin()
    {
        return numToWin;
    }

    /**
     * Checks and returns what character in at the BoardPosition position
     *
     * @return returns the char that is currently in pos
     *
     * @pre None
     *
     * @post whatsAtPos = player OR " " AND self = #self
     *
     * @param pos The BoardPosition to check for a player char or blank space
     */
    public char whatsAtPos(BoardPosition pos)
    {
        //iterates through each key(player) in the hashmap
        for(Character player : board.keySet()){
            //gets the list of a players boardpositions using the key
            List<BoardPosition> tokens = board.get(player);

            //checks list for the board position
            if(tokens.contains(pos)){
                return player;
            }
        }
        //not found in any players list of moves
        return ' ';
    }

    /**
     * Checks a board position and returns true if player is there, false otherwise
     *
     * @return true if player is in pos, false otherwise
     *
     * @pre none
     *
     * @post isPlayerAtPos = true or false AND self = #self
     *
     * @param pos BoardPosition to check for player in
     * @param player player token to check for in pos
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        List<BoardPosition> playerPositions = board.get(player);
        if(playerPositions == null || !playerPositions.contains(pos)){
            return false;
        }
        return true;
    }

    /**
     * Places the player token into the lowest available row in a column number passed in
     *
     * @pre getNumColumns() > c >= 0 AND column has space to place a token
     *
     * @post [a token is placed at the specified column in the lowest available row]
     * AND self = #self
     *
     * @param p the player token to be placed on the game board
     * @param c the column number the token will be placed in
     */
    public void dropToken(char p, int c)
    {
        for(int row = 0; row < getNumRows(); row++){
            BoardPosition check = new BoardPosition(row, c);
            if(whatsAtPos(check) == ' '){
                if(board.containsKey(p)){
                    board.get(p).add(check);
                }
                else{
                    List<BoardPosition> tokens = new ArrayList<>();
                    tokens.add(check);
                    board.put(p, tokens);
                }
                return;
            }
        }
    }
}