package cpsc2150.extendedTicTacToe;

/**
 * This object will controll what is esentally the brain of the bard game holding the board and its funcitons
 * @Defines:
 *          gameboard - this is the board that we will play on
 * @Initialization Ensures: that gameboard will be fresh every time an instance of the board is called.
 *
 * @Constraints: positoins entered have to be valid inputs and free open space void of '|' or a letter
 */
public interface IGameBoard {

    //This function will check if the cords given by the player are valid and
    //have nothing in that space.

    /**
     *
     * @param pos >= (0,0) && pos <= (7,7)
     * @return true if the position the player entered is a valid spot
     *
     */
    boolean checkSpace(BoardPosition pos);

    //This will place the player at the place

    /**
     *
     * @param marker must be valid board positoin
     * @param player must be O or X
     * @post player is placed at posiont marker on gameboard
     */
    void placeMarker(BoardPosition marker, char player);

    //Draws are checked for by looking though and seeing if any empty spaces have yet to be taken.

    /**
     *
     * @return true iff(all spots are filled and checkWin is false)
     */
    boolean checkForDraw();

    //This function will check for a horizontal win by moving the Col player by two in both directions.
    boolean checkForWinner(BoardPosition last);
    /**
     *
     * @param lastPos valid position that just had an item put into it
     * @param player the peice that was just placed
     * @return true iff(there are 5 or more of the same player in a row conected to one another)
     */
    boolean checkHorizontalWin(BoardPosition lastPos, char player);

    //This function will check for a horizontal win by moving the Col player by two in both directions.

    /**
     *
     * @param lastPos valid position that just had an item put into it
     * @param player the peice that was just placed
     * @return true iff(there are 5 or more of the sma eplayer in a row contected to one another)
     */
    boolean checkVerticalWin(BoardPosition lastPos, char player);

    //this will check for 5 in a row diagnally. This is doffernet from the other two because it needed
    //seperate checkers for each diagnoal direction.

    /**
     *
     * @param lastPos vallid position that just had an item put into it
     * @param player the peice that was just placed at lastPos
     * @return true iff(there are 5 or more of the same player in a row to one another)
     */
    boolean checkDiagonalWin(BoardPosition lastPos, char player);

    /**
     *
     * @param pos vallid position
     * @return this will return what is at pos
     */
    char whatsAtPos(BoardPosition pos);

    /**
     *
     * @param pos valid position that we will be cheked
     * @param player will be x or o
     * @return true iff(player is at pos)
     */
    boolean isPlayerAtPos(BoardPosition pos, char player);

    /**
     *
     * @return the number or rows in the page
     */
    public int getNumRows();

    /**
     *
     * @return the number of cols in the board
     * */
    public int getNumCol();

    /**
     *
     * @return the number of inline obj that you need to win.
     */
    public int getNumToWin();
}
