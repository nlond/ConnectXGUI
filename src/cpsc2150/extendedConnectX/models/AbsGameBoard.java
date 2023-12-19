package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Bennett McDowell - bennettmcdowell
    Nathan Londhe - nlond
    Laurel Kay - lakay986
    Andrew Wenstrand - awenstr

 */
/**
 * The AbsGameBoard abstract class contains the toString() method
 * which prints the entire game board to the terminal
 *
 * @invariant getNumColumns() > 0 AND getNumRows() > 0
 *
 * @corresponds: aRow = row AND aCol = column
 */


public abstract class AbsGameBoard implements IGameBoard {
    /**
     * Converts the game board to a string containing the entire game board
     *
     * @return a string representing the game board
     *
     * @pre None
     *
     * @post [Will return the string representation of the game board] AND self = #self
     */
    @Override
    public String toString()
    {
        //adds the top row of col numbers
        String ret = "";
        for (int m = 0; m < getNumColumns(); m++){
            ret += "|" + String.format("%2d", m);

        }
        ret += "|\n";
        //adds last pipe

        //adds current board
        for (int i = getNumRows()-1; i >= 0; i--){
            for (int j = 0; j < getNumColumns(); j++){
                BoardPosition spot = new BoardPosition(i, j);
                ret += "|" + whatsAtPos(spot) + " ";
            }
            ret += "|\n";
        }

        return ret;
    }
}