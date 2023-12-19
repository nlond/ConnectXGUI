package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Bennett McDowell - bennettmcdowell
    Nathan Londhe - nlond
    Laurel Kay - lakay986
    Andrew Wenstrand - awenstr

 */

/**
 * The BoardPosition class holds data pertaining to the position of a token in the connectX game
 * Each token has a row and column
 *
 * @invariant  getNumRows() > Row > 0 AND  getNumCols() > Column > 0
 *
 */
public class BoardPosition
{
    private int Row;
    private int Column;

    /**
     * Parameterized Constructor for the BoardPosition class.
     * Sets the Row and Column fields to the values passed in
     *
     * @param aRow the row of the position of the object
     * @param aColumn the column of the position of the object
     *
     * @pre aRow > 0 AND aColumn > 0
     *
     * @post Row = aRow AND Column = aColumn
     */
    public BoardPosition(int aRow, int aColumn)
    {
        Row = aRow;
        Column = aColumn;
    }

    /**
     * Returns the value of the item stored in the Row field
     *
     * @return an integer representing the row the BoardPosition is in.
     *
     * @pre None
     *
     * @post getRow = Row AND Row = #Row AND Column = #Column
     */
    public int getRow()
    {
        return Row;
    }

    /**
     * Returns the value of the item stored in the Column field
     *
     * @return an integer representing the Column field of this BoardPosition
     *
     * @pre None
     *
     * @post getColumn = Column AND Row = #Row AND Column = #Column
     */
    public int getColumn()
    {
        return Column;
    }

    /**
     * Compares the current object to the object passed in to see if they are equal
     *
     * @param obj the object to compare to
     *
     * @return a boolean value, true if the two objects are equal, false otherwise
     *
     * @pre none
     *
     * @post Compares the current object and the object passed in, returning true if they are the same position AND Row = #Row AND Column = #Column
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof BoardPosition) {
            BoardPosition pos = (BoardPosition) obj;
            return (Row == pos.getRow() && Column == pos.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Converts the board position to a string in the format of "<row>,<column>"
     *
     * @return the string representation of the position
     *
     * @pre None
     *
     * @post Will return the string representation of the position formatted as "<row>,<column>" AND Row = #Row AND Column = #Column
     */
    @Override
    public String toString()
    {
        return (Row + "," + Column);
    }
}