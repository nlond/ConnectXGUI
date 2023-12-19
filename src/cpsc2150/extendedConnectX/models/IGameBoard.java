package cpsc2150.extendedConnectX.models;

/**
 * IGameBoard represents a GameBoard that will be used to play with two players
 *
 * @initialization Ensures: aRow >= 0 AND aCol >= 0 AND BoardField[][] == ''
 *
 * @defines:
 * [A game board used to play connectX with how ever many players, rows, columns, and amount to win the user defines]
 *
 * @constraints:
 * aRow >= 0 AND aCol >= 0 AND [there cannot be an empty space '' below a placed player token]
 */
public interface IGameBoard {
    // Primary methods
    public char whatsAtPos(BoardPosition pos);
    public int getNumRows();
    public int getNumColumns();
    public int getNumToWin();
    public void dropToken(char p, int c);

    //constants in place of magic numbers
    int MAX_SIZE = 100;
    int MIN_SIZE = 3;
    int MIN_PLAYERS = 2;
    int MAX_PLAYERS = 10;
    int MIN_WIN = 3;
    int MAX_WIN = 25;

    // Secondary methods

    /**
     * Checks a board position and returns true if player p is there, false otherwise
     *
     * @return true if player p is in pos, false otherwise
     *
     * @pre player = X or O
     *
     * @post isPlayerAtPos = true or false AND self = #self
     *
     * @param pos BoardPosition to check for player in
     * @param player player token to check for in pos
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        return player == whatsAtPos(pos);
    }

    /**
     * Checks if a column in the game board can accept another token or not
     *
     * @pre getNumColumns() > c >= 0
     *
     * @post IFF [the top most row of column c is empty] THEN true
     * OW false
     * self = #self
     *
     * @param c is taken as the column number
     * @return a boolean if the column can accept another token or not
     */
    public default boolean checkIfFree(int c)
    {
        return (whatsAtPos(new BoardPosition(getNumRows() - 1, c)) == ' ');
    }



    /**
     * checks if the previous move resulted in a win
     *
     * @param c integer representing the column to check the most recent token in
     *
     * @pre 0 <= c <= numCols, a turn must have been played
     *
     * @return a boolean value representing if a win has occurred
     *
     * @post IFF (checkDiagWin() AND checkHorizWin() AND checkVertWin()) THEN true
     * OW return false
     * self = #self
     */
    public default boolean checkForWin(int c)
    {
        int row = 0;
        while(row < getNumRows() && whatsAtPos(new BoardPosition(row, c)) != ' ') {
            row++;
        }
        if(row == 0) {
            return false;
        }

        BoardPosition pos = new BoardPosition(row - 1, c);

        char p = whatsAtPos(pos);

        if(checkVertWin(pos, p)) {
            return true;
        } else if(checkHorizWin(pos, p)) {
            return true;
        } else if(checkDiagWin(pos, p)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks if a tie has occurred
     *
     * @pre none
     *
     * @return a boolean value representing if the board is full
     *
     * @post IFF [checkIfFree(col) returns false for every column] THEN true
     * ELSE IF [any column does have free space will return false] THEN true
     * OW return false
     * self = #self
     */
    public default boolean checkTie()
    {
        int col;
        for(col = 0; col < getNumColumns(); col++){
            //if any column has open space return false
            if(checkIfFree(col)){
                return false;
            }
        }
        return true;
    }

    /**
     * checks if a horizontal win has occurred
     *
     * @param pos a board position object representing the last token placed
     * @param p char representing the players symbol of their token
     * @pre token p in position pos should not be a blank space, and p must be a valid player symbol.
     *
     * @return a boolean value representing if a win has occurred
     *
     * @post IFF [token p in position pos resulted in a horizantal line of tokens sufficient to win the game]
     * OW return false
     * self = #self
     */
    public default boolean checkHorizWin(BoardPosition pos, char p)
    {
        int row = pos.getRow();
        int col = pos.getColumn();

        int count = 0;

        // Check to the left of the last placed token
        for (int i = 0; i < getNumToWin(); i++) {
            if (col - i < 0 || whatsAtPos(new BoardPosition(row, col - i)) != p) {
                break;
            }
            count++;
        }

        // Check to the right of the last placed token
        for (int i = 1; i < getNumToWin(); i++) {
            if (col + i >= getNumColumns() || whatsAtPos(new BoardPosition(row, col + i)) != p) {
                break;
            }
            count++;
        }

        return count >= getNumToWin();
    }

    /**
     * checks if a vertical win has occurred
     *
     * @param pos a board position representing the last token placed by player p
     * @param p a char representing a players token
     * @pre token p in position pos should not be a blank space, and p must be a valid player symbol.
     *
     * @return a boolean value representing if a win has occurred
     *
     * @post IFF [token p in position pos resulted in a vertical line of tokens sufficient to win the game]
     * OW return false
     * self = #self
     */
    public default boolean checkVertWin(BoardPosition pos, char p)
    {
        int row = pos.getRow();
        int col = pos.getColumn();

        int count = 0;


        for (int i = 0; i < getNumToWin(); i++) {
            if (whatsAtPos(new BoardPosition(row - i, col)) == p) {
                count++;
            }
        }
        if (count >= getNumToWin()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Checks the last place token to see if the player has a diagonal win
     *
     * @return true if there is a diagonal win, false if not
     *
     * @pre p = X or O
     *
     * @post IFF [position pos(last token) resulted in a diagonal win]
     * OW returns false
     * self = #self
     *
     * @param pos the BoardPosition where the last token was placed
     * @param p the player token that was placed last
     */
    public default boolean checkDiagWin(BoardPosition pos, char p)
    {
        int row = pos.getRow();
        int col = pos.getColumn();

        int count = 0;

        // Check diagonal from northeast to southwest
        for (int i = 0; i < getNumToWin(); i++) {
            if (row - i < 0 || col - i < 0 || whatsAtPos(new BoardPosition(row - i, col - i)) != p) {
                break;
            }
            count++;
        }

        for (int i = 1; i < getNumToWin(); i++) {
            if (row + i >= getNumRows() || col + i >= getNumColumns() || whatsAtPos(new BoardPosition(row + i, col + i)) != p) {
                break;
            }
            count++;
        }

        if (count >= getNumToWin()) {
            return true;
        }

        count = 0;

        // Check diagonal from northwest to southeast
        for (int i = 0; i < getNumToWin(); i++) {
            if (row - i < 0 || col + i >= getNumColumns() || whatsAtPos(new BoardPosition(row - i, col + i)) != p) {
                break;
            }
            count++;
        }

        for (int i = 1; i < getNumToWin(); i++) {
            if (row + i >= getNumRows() || col - i < 0 || whatsAtPos(new BoardPosition(row + i, col - i)) != p) {
                break;
            }
            count++;
        }

        return count >= getNumToWin();
    }
}