package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    //number of players
    public int numPlayer = 0;
    public int whosTurn = 0;
    public boolean gameOver = false;

    public static final int MAX_PLAYERS = 10;

    private static char[] PLAYER_CHARACTER = {'X', 'O', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I'};

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;

        // Some code is needed here.
        numPlayer = np;
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        BoardPosition place = new BoardPosition(row,col);

        if(gameOver)
        {
            gameOver = false;
            newGame();
        }

        if(curGame.checkSpace(place)) {
            curGame.placeMarker(place, PLAYER_CHARACTER[whosTurn]);
            screen.setMarker(row, col, PLAYER_CHARACTER[whosTurn]);

            if (curGame.checkForWinner(place)) {
                screen.setMessage(PLAYER_CHARACTER[whosTurn] + " Won the Game!!!!!");
                gameOver = true;
                return;
            }


            if (whosTurn == numPlayer - 1) {
                whosTurn = 0;
            } else {
                whosTurn++;
            }

            screen.setMessage("It is " + PLAYER_CHARACTER[whosTurn] + "'s turn.");

            if (curGame.checkForDraw() && curGame.checkForWinner(place) == false) {
                screen.setMessage("Looks like its a draw!");
                gameOver = true;
            }
        }
        else
        {
            screen.setMessage("Sorry, that spots taken Player " + PLAYER_CHARACTER[whosTurn]);
        }


    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}