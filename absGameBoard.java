package cpsc2150.extendedTicTacToe;

public abstract class absGameBoard implements IGameBoard {
    /**
     *
     * @return the string representaion of the board
     * @requres a board to exist no other needs to be met itll allow us to print even blank boards
     * @ensures players know what will be going on in the game.
     */
    @Override
    public String toString() {

        int row = getNumRows();
        int col = getNumCol();
        StringBuilder build = new StringBuilder();

        //This will build the top row allowing us to make the frame
        for(int a = 0; a < col; a++)
        {
            if(a == 0)
            {
                build.append("  ");
            }
            if(a<10)
                build.append("| ").append(a);
            else
                build.append("|"). append(a);
        }

        build.append("|\n");

        //This loop will build the frame and fill the grid with the inputs.
        for(int i = 0; i < row; i++)
        {

            if(i < 10)
                build.append(" ").append(i);
            else
                build.append(i);

            for(int j = 0; j<col; j++)
            {
                BoardPosition pos1 = new BoardPosition(i,j);
                build.append("| ").append(whatsAtPos(pos1));
            }
            build.append("|\n");
        }
        return build.toString();
    }
}
