package cpsc2150.extendedTicTacToe;

public class GameBoard extends absGameBoard implements IGameBoard {


    //This will make sure that the board will be reset each time

    int rowMax;
    int colMax;
    int toW;

    char[][] gameboard;

    /**
     * @ensures: that gameboard will be empty during each new call of it
     */
    public GameBoard(int rows, int cols, int toWin)
    {
        int rowM = rows;
        int colM = cols;

        rowMax = rowM;
        colMax = colM;
        toW = toWin;

        gameboard = new char[rowMax][colMax];


        for(int row = 0; row < rowMax; row++)
        {
            for(int col = 0; col < colMax ; col++)
            {
                gameboard[row][col] = ' ';
            }
        }




    }


     //This function will check if the cords given by the player are valid and
    //have nothing in that space.

    /**
     *
     * @param pos >= (0,0) && pos <= (rowMax,colMax)
     * @return true iff(positon is == ' ')
     * @ensures: that the space the player would like to manipulate is good to be messed with
     */
    @Override
    public boolean checkSpace(BoardPosition pos)
    {
        boolean open = false;

        if( 0 <= pos.getCol() && pos.getCol() < colMax && 0 <= pos.getRow() && pos.getRow() < rowMax)
        {
            if ((gameboard[pos.getRow()][pos.getCol()] == ' ')) {
                open = true;
            }
        }
        return open;
    }

    //This will place the player at the place

    /**
     *
     * @param marker must be valid board positoin
     * @param player must be valid peice
     * @ensures: game board is updated as the player would like it to be
     */
    @Override
    public void placeMarker(BoardPosition marker, char player)
    {
        gameboard[marker.getRow()][marker.getCol()] = player;
    }
    //This will check to see if either player has won.

    /**
     *
     * @param lastPos is valid and is the most reacent placement of a player peice
     * @return true iif(either of the other true check calls are equal to true)
     * @ensures that the players will recive a victory if they have earend one.
     *
     */
    @Override
    public boolean checkForWinner(BoardPosition lastPos)
    {
        //This will set the player to X and do a check if it has any win options
        char player = gameboard[lastPos.getRow()][lastPos.getCol()];

        boolean win = checkDiagonalWin(lastPos, player) || checkHorizontalWin(lastPos, player) || checkVerticalWin(lastPos, player);

        return win;
    }
    //Draws are checked for by looking though and seeing if any empty spaces have yet to be taken.

    /**
     *
     * @return true iff(all free spaces have been filled and no winner was diclaried)
     * @ensures: that the game will end and that the players will be alerted so they can start a new one
     */
    @Override
    public boolean checkForDraw()
    {
        //if an empty Space exist it'll be recorded here
        int emptySpace = 0;
        //This finds empty spaces. Since there will always be an empty space at true <0, 0> I had to
        //start looking in column one first.
        for (int x = 0; x < rowMax; x++) {
            for (int y = 0; y < colMax; y++) {

                //if space exist add it to empty space
                if(gameboard[x][y] == ' ')
                {
                    emptySpace++;
                }
            }

        }

        //if emptySpace is > 0 then it is shown that cat is false and doesn't exist

        if(emptySpace != 0)
        {
            return false;
        }
        //if empty space is 0 then a cat exists.
        return true;

    }

    //This function will check for a horizontal win by moving the Col player by two in both directions.

    /**
     *
     * @param lastPos valid position that just had an item put into it
     * @param player the peice that was just placed
     * @return true iff( the peice bing tested has 5 or more identical player peices conected horizontilly)
     *          false iff(num conected < 5 || other player peice or ' ' is found blocking)
     * @ensures: winner is found and declaried so game can end.
     */
    @Override
    public boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        boolean result = false;
        int rowIn = lastPos.getRow();
        int colIn = lastPos.getCol();
        int inLine = 1;
        int holder = colIn;

        //This checks right so long as the varible column is withen bounds if it
        //is no longer in bounds then it leaves.
        //we add 2 to increment it to the next num and minus 2 becuase we need to get lower bound
        colIn++;
        while(colIn < colMax)
        {

            //if the peice to the side is same as the player then it will
            //add one to the number in line with it.
            //if it is not the same as player then it will exit the loop
            if(gameboard[rowIn][colIn] == player)
            {
                inLine++;
            }
            else
            {
                colIn = 1000;
            }
            colIn++;
        }
        //This checks left so long as the varible column is withen bounds if it
        //is no longer in bounds then it leaves.
        colIn = holder; //reset the value
        colIn--;
        while(colIn >= 0)
        {

            //if the peice to the side is same as the player then it will
            //add one to the number in line with it.
            //if it is not the same as player then it will exit the loop
            if(gameboard[rowIn][colIn] == player)
            {
                inLine++;
            }
            else
            {
                colIn = -1;
            }
            //increments the value
            colIn--;
        }

        //if there are five or more in a rwo then a win is returned.
        if(inLine >= getNumToWin()) {
            result = true;
        }
        //if five where not found in a row then false is returned
        return result;
    }

    //This function will check for a horizontal win by moving the Col player by two in both directions.

    /**
     *
     * @param lastPos valid position that just had an item put into it
     * @param player the peice that was just placed
     * @return true iff(number of inline peices is >= 5 and is continus)
     * @ensures winner will be anounced after 5 inline vertical peices where layed.
     */
    @Override
    public boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        boolean result = false;
        int rowIn = lastPos.getRow();
        int colIn = lastPos.getCol();
        int inLine = 1;
        int holder = rowIn;

        //This checks down so long as the varible column is withen bounds if it
        //is no longer in bounds then it leaves.
        rowIn++;
        while(rowIn < rowMax)
        {
            //if the peice to the side is same as the player then it will
            //add one to the number in line with it.
            //if it is not the same as player then it will exit the loop
            if(gameboard[rowIn][colIn] == player)
            {
                inLine++;
            }
            else
            {
                rowIn = 1000;
            }
            //increments the vlaue
            rowIn++;
        }

        //reset and increment the value
        rowIn = holder;
        rowIn--;

        //This checks down so long as the variable column is withen bounds if it
        //is no longer in bounds then it leaves.
        while(rowIn >= 0)
        {
            //if the peice to the side is same as the player then it will
            //add one to the number in line with it.
            //if it is not the same as player then it will exit the loop
            if(gameboard[rowIn][colIn] == player)
            {
                inLine++;
            }
            else
            {
                rowIn = -1;
            }
            //increments the value
            rowIn--;
        }

        //if five or more have been found in a line then ill return that a win was found
        //if five where not found then false is reuturned
        if(inLine >=getNumToWin()) {
            result = true;
        }
        return result;
    }

    //this will check for 5 in a row diagnally. This is doffernet from the other two because it needed
    //seperate checkers for each diagnoal direction.

    /**
     *
     * @param lastPos vallid position that just had an item put into it
     * @param player the peice that was just placed at lastPos
     * @return true iff(number inline >= 5)
     * @ensures that a winner will be anounced if you have 5 diagnal peices in line.
     *
     */
    @Override
    public boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        boolean result = false;
        int rowIn = lastPos.getRow();
        int colIn = lastPos.getCol();
        int inLine = 1;
        int holderR = rowIn;
        int holderC = colIn;


        //This checks down and to the right so long as the variable column is withen bounds if it
        //is no longer in bounds then it leaves.
        rowIn++;
        colIn++;
        while(rowIn < rowMax && colIn < colMax)
        {

            //if the peice to the side is same as the player then it will
            //add one to the number in line with it.
            //if it is not the same as player then it will exit the loop
            if(gameboard[rowIn][colIn] == player)
            {
                inLine++;
            }
            else
            {
                rowIn = 1000;
            }
            //incroment
            rowIn++;
            colIn++;
        }

        //row and col are reset and then incromented
        rowIn = holderR;
        colIn = holderC;
        rowIn--;
        colIn--;
        //This checks down and to the left so long as the variable column is withen bounds if it
        //is no longer in bounds then it leaves.
        while(rowIn >= 0 && colIn >= 0)
        {

            //if the peice to the side is same as the player then it will
            //add one to the number in line with it.
            //if it is not the same as player then it will exit the loop
            if(gameboard[rowIn][colIn] == player)
            {
                inLine++;
            }
            else
            {
                rowIn = -1;
            }
            //incrementation
            rowIn--;
            colIn--;
        }
        //This if block will check if there was a win found in the first diagonal
        //if its found then a win is returned
        //if its false then inLine is set back to one and the other diagonal is checked
        if(inLine >= getNumToWin())
        {
            return true;
        }
        else
        {
            inLine = 1;
        }

        //This resets the rows and columns and then increments them
        rowIn = holderR;
        colIn = holderC;
        rowIn--;
        colIn++;
        //This checks up and to the right so long as the variable column is withen bounds if it
        //is no longer in bounds then it leaves.
        while(rowIn >= 0 && colIn < colMax)
        {

            //if the peice to the side is same as the player then it will
            //add one to the number in line with it.
            //if it is not the same as player then it will exit the loop
            if(gameboard[rowIn][colIn] == player)
            {
                inLine++;
            }
            else
            {
                rowIn = -1;
            }
            //increments row and col
            rowIn--;
            colIn++;
        }
        //This resets the rows and columns and then increments them
        rowIn = holderR;
        colIn = holderC;
        rowIn++;
        colIn--;
        //This checks down and to the left so long as the variable column is withen bounds if it
        //is no longer in bounds then it leaves.
        while(rowIn < rowMax && colIn >= 0)
        {

            //if the peice to the side is same as the player then it will
            //add one to the number in line with it.
            //if it is not the same as player then it will exit the loop
            if(gameboard[rowIn][colIn] == player)
            {
                inLine++;
            }
            else
            {
                rowIn = 1000;
            }
            //increments row and col
            rowIn++;
            colIn--;
        }

        //This is the final inLine check for the last diagnal and looks to see if 5 where in a row
        //if its ture the a win is retunred
        //if its not then false is returned.
        if(inLine >=getNumToWin()) {
            return true;
        }
        return result;
    }

    /**
     *
     * @param pos vallid position
     * @return what is at the postion asked for
     */
    @Override
    public char whatsAtPos(BoardPosition pos)
    {
//returns what is in the GameBoard at position pos
//If no marker is there it returns a blank space char ‘ ‘

        return gameboard[pos.getRow()][pos.getCol()];
    }

    /**
     *
     * @param pos valid position that we will be cheked
     * @param player will be x or o
     * @return true iff(player is equal to the character at the positon in question)
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
//returns true if the player is at pos, otherwise it returnsfalse
//Note: this method will be implemented very similarly to
// whatsAtPos, but it’s asking a different question. We only know they
// will be similar because we know GameBoard will contain a 2D array. If
// the data structure were to change in the future these two methods
// could end up being radically different.
        return gameboard[pos.getRow()][pos.getCol()] == player;
    }

    /**
     *
     * @return the number of playable rows
     */
    @Override
    public int getNumRows() {
        return rowMax;
    }

    /**
     *
     * @return the number of playable coulombs
     */
    @Override
    public int getNumCol() {
        return colMax;
    }

    /**
     *
     * @return the number of inline peices you need to win
     */
    @Override
    public int getNumToWin() {
        return toW;
    }



}
