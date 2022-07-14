package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardMem extends absGameBoard {

    private Map<Character, List<BoardPosition>> myMap;
    int colMax;
    int rowMax;
    int toWin;

    /**
     * @ensures: that gameboard will be empty during each new call of it
     */
    public GameBoardMem(int row, int col, int towin)
    {
        colMax = col;
        rowMax = row;
        toWin = towin;
        myMap = new HashMap<>();
    }

    /**
     *
     * @param pos >= (0,0) && pos <= (rowMax,colMax)
     * @return true iff(positon is == ' ')
     * @ensures: that the space the player would like to manipulate is good to be messed with
     */
    @Override
    public boolean checkSpace(BoardPosition pos) {
        boolean open = false;



        if( 0 <= pos.getCol() && pos.getCol() < colMax && 0 <= pos.getRow() && pos.getRow() < rowMax)
        {
            if(whatsAtPos(pos) ==  ' ')
                open = true;
        }
        return open;
    }


    /**
     *
     * @param marker must be valid board positoin
     * @param player must be valid peice
     * @ensures: game board is updated as the player would like it to be
     */
    @Override
    public void placeMarker(BoardPosition marker, char player)
    {
        if(whatsAtPos(marker) == ' ') {
            List<BoardPosition> lst;
            if (myMap.containsKey(player)) {
                lst = myMap.remove(player);
            } else {
                lst = new ArrayList<>();
            }
            lst.add(marker);
            myMap.put(player, lst);
        }
    }

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
        char player = whatsAtPos(lastPos);
        return (checkHorizontalWin(lastPos, player) || checkDiagonalWin(lastPos, player) || checkVerticalWin(lastPos, player));
    }



    /**
     *
     * @return true iff(all free spaces have been filled and no winner was diclaried)
     * @ensures: that the game will end and that the players will be alerted so they can start a new one
     */
    @Override
    public boolean checkForDraw() {
        boolean isDraw = false;
        int numOpen = 0;

        for(int x = 0; x < rowMax; x++)
        {
            for (int y = 0; y < colMax; y++)
            {
                BoardPosition temp = new BoardPosition(x,y);
                if(whatsAtPos(temp) == ' ')
                    numOpen++;
            }
        }

        if(numOpen == 0)
            isDraw = true;

        return isDraw;
    }

    /**
     *
     * @param lastPos valid position that just had an item put into it
     * @param player the peice that was just placed
     * @return true iff( the peice bing tested has 5 or more identical player peices conected horizontilly)
     *          false iff(num conected < 5 || other player peice or ' ' is found blocking)
     * @ensures: winner is found and declaried so game can end.
     */
    @Override
    public boolean checkHorizontalWin(BoardPosition lastPos, char player) {
       boolean win = false;
       int inRow = 1;
       BoardPosition checker = new BoardPosition(lastPos.getRow(), lastPos.getCol()+1);

       while(isPlayerAtPos(checker,player))
       {
           inRow++;
           checker = new BoardPosition(checker.getRow(), checker.getCol() +1 );
       }

        checker = new BoardPosition(lastPos.getRow(), lastPos.getCol()-1);

        while(isPlayerAtPos(checker,player))
        {
            inRow++;
            checker = new BoardPosition(checker.getRow(), checker.getCol() -1 );
        }

        if(inRow >= getNumToWin())
            win = true;

        return win;
    }

    /**
     *
     * @param lastPos valid position that just had an item put into it
     * @param player the peice that was just placed
     * @return true iff(number of inline peices is >= 5 and is continus)
     * @ensures winner will be anounced after 5 inline vertical peices where layed.
     */
    @Override
    public boolean checkVerticalWin(BoardPosition lastPos, char player) {
        boolean win = false;
        int inRow = 1;
        BoardPosition checker = new BoardPosition(lastPos.getRow()+1, lastPos.getCol());

        while(isPlayerAtPos(checker,player))
        {
            inRow++;
            checker = new BoardPosition(checker.getRow() + 1, checker.getCol());
        }

        checker = new BoardPosition(lastPos.getRow() - 1, lastPos.getCol());

        while(isPlayerAtPos(checker,player))
        {
            inRow++;
            checker = new BoardPosition(checker.getRow() - 1, checker.getCol());
        }

        if(inRow >= getNumToWin())
            win = true;

        return win;
    }


    /**
     *
     * @param lastPos vallid position that just had an item put into it
     * @param player the peice that was just placed at lastPos
     * @return true iff(number inline >= 5)
     * @ensures that a winner will be anounced if you have 5 diagnal peices in line.
     *
     */
    @Override
    public boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        boolean win = false;
        int inRow = 1;
        BoardPosition checker = new BoardPosition(lastPos.getRow()+1, lastPos.getCol()+1);

        while(isPlayerAtPos(checker,player))
        {
            inRow++;
            checker = new BoardPosition(checker.getRow() + 1, checker.getCol()+1);
        }

        checker = new BoardPosition(lastPos.getRow()-1, lastPos.getCol()-1);

        while(isPlayerAtPos(checker,player))
        {
            inRow++;
            checker = new BoardPosition(checker.getRow() - 1, checker.getCol()-1);
        }
        if(inRow >= getNumToWin())
            win = true;

        checker = new BoardPosition(lastPos.getRow()+1, lastPos.getCol()-1);
        inRow = 1;

        while(isPlayerAtPos(checker,player))
        {
            inRow++;
            checker = new BoardPosition(checker.getRow() + 1, checker.getCol()-1);
        }

        checker = new BoardPosition(lastPos.getRow()-1, lastPos.getCol()+1);

        while(isPlayerAtPos(checker,player))
        {
            inRow++;
            checker = new BoardPosition(checker.getRow() -1, checker.getCol()+1);
        }

        if(inRow >= getNumToWin())
            win = true;

        return win;

    }

    /**
     *
     * @param pos vallid position
     * @return what is at the postion asked for
     */
    @Override
    public char whatsAtPos(BoardPosition pos) {


        for(Character player: myMap.keySet())
        {
           for(BoardPosition currentPos: myMap.get(player))
           {
               if(pos.equals(currentPos))
               {
                   return player;
               }
           }
        }
        return ' ';
    }



    /**
     *
     * @param pos valid position that we will be cheked
     * @param player will be x or o
     * @return true iff(player is equal to the character at the positon in question)
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        boolean isPlayerOn = false;
        if(whatsAtPos(pos) == player)
            isPlayerOn = true;

        return isPlayerOn;
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
        return toWin;
    }
}
