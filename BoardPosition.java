package cpsc2150.extendedTicTacToe;

public class BoardPosition {


    int row;
    int col;
    //This will construct BoardPosition objects
    public BoardPosition(int r, int c) {

        //These statments will set the value the player wanted to their
        //correct element values

            row = r;
            col = c;

    }

    //this will return the value col
    public int getCol()
    {
      return col;
    }

    //this will return the value row
    public int getRow()
    {
        return row;
    }

    //this will return row and col in a string format
    //e.x. <5, 3>
    @Override
    public String toString() {
        String cords = "<" + row +", " + col+ ">";
        return cords;
    }

    //this will see if two BoardPosiion obj are the same.
    public boolean equals(BoardPosition obj) {
        if(this.col == obj.getCol() && this.row == obj.getRow())
        {
            return true;
        }
        return false;
    }
}
