package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Bennett McDowell - bennettmcdowell
    Nathan Londhe - nlond
    Laurel Kay - lakay986
    Andrew Wenstrand - awenstr

 */

/**
 * The GameBoard class controls the functions of a connectX game,
 * dropping tokens, and checking winning conditions
 *
 * @invariant MIN_SIZE <= row <= MAX_SIZE AND MIN_SIZE <= column <= MAX_SIZE
 * AND MIN_WIN <= numToWin >= MAX_WIN AND BoardField = char[][]
 * AND [there can not be a blank space between two player tokens in the columns]
 *
 * @corresponds: aRow = row AND aCol = column
 */

public class GameBoard extends AbsGameBoard
{
    private int row;
    private int column;
    private char[][] BoardField;
    private int numToWin;

    /**
     * Constructor for the GameBoard object that creates a blank 2D array game board
     *
     * @pre None
     *
     * @post will create a row x col 2D array of characters with values of ' ' AND row = aRow
     * AND column = aCol AND numToWin = numWin
     */
    public GameBoard(int aRow, int aCol, int numWin)
    {
        row = aRow;
        column = aCol;
        BoardField = new char[row][column];
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < column ; j++)
            {
                BoardField[i][j] = ' ';
            }
        }
        numToWin = numWin;
    }

    /**
     * Checks and returns what character in at the BoardPosition position
     *
     * @return returns the char that is currently in pos
     *
     * @pre None
     *
     * @post whatsAtPos = X or O or " " AND self = #self
     *
     * @param pos The BoardPosition to check for a player char or blank space
     */
    public char whatsAtPos(BoardPosition pos)
    {
        int atRow = getNumRows()-1-pos.getRow();
        int atCol = pos.getColumn();

        if (atRow < 0 || atCol < 0 || atRow >= getNumRows() || atCol >= getNumColumns()) {
            return ' ';
        } else {
            return BoardField[atRow][atCol];
        }
    }

    /**
     * Places the player token into the lowest available row in a column number passed in
     *
     * @pre p = X or O AND  getNumColumns() > c >= 0 AND column has space to place a token
     *
     * @post [places a token in colum c] AND self = #self
     *
     * @param p the player token to be placed on the game board
     * @param c the column number the token will be placed in
     */
    public void dropToken(char p, int c)
    {
        for (int row = 0; row < getNumRows(); row++){
            BoardPosition check = new BoardPosition(row, c);
            if(whatsAtPos(check) == ' '){
                BoardField[getNumRows()-1-row][c] = p;
                return;
            }
        }
    }

    /**
     * Returns the number of rows in the GameBoard
     *
     * @return the number of rows in the GameBoard
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
     * Returns the number of columns in the GameBoard
     *
     * @return the number of columns in the GameBoard
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
}