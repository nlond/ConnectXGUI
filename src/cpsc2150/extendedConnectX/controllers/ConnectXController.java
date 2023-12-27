package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.models.*;
import cpsc2150.extendedConnectX.views.*;

/**
 * The controller class will handle communication between our View and our Model ({@link IGameBoard})
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your your {@link BoardPosition} class, {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class ConnectXController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private ConnectXView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;
    
    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    /**
     * <p>
     * An integer that serves as the iterator that keeps track of who's turn it is
     * </p>
     */
    private int playerItr;

    /**
     * <p>
     * The different player tokens that are available for this game (10 of them).
     * </p>
     */
    char[] players = {'X', 'O', 'R', 'T', 'P', 'Q', 'W', 'K', 'S', 'Z'};

    private int playerTurn;
    private char winnerPlayer;
    private boolean isTie = false;

    /**
     * <p>
     * This creates a controller for running the Extended ConnectX game
     * </p>
     * 
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     * 
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public ConnectXController(IGameBoard model, ConnectXView view, int np) {

        // currGame has the correct number of rows, cols, and nums to win
        // it also contains the methods for the whole game
        this.curGame = model;

        // update the view by calling functions from screen
        this.screen = view;
        this.numPlayers = np;
        this.playerItr = 0;
        this.playerTurn = 1;
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     * 
     * @param col 
     *      The column of the activated button
     * 
     * @post [ will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int col) {
        // basically create a game loop with backend code except without a loop
        // since this is called

        // go back to first player's turn after every player has gone
        if (playerItr == numPlayers) {
            playerItr = 0;
        }

        // iterator for displaying who's turn it is
        if (playerTurn == numPlayers) {
            playerTurn = 0;
        }

        screen.setMessage("It is " + players[playerTurn] + "'s turn");

        // 1.) check for a win or a tie
            // a.) if no:
                // 1.) check if column is free
                // 2.) drop token
                // 3.) go to next player's turn
        if (!curGame.checkForWin(col)) {
            if (curGame.checkIfFree(col)) {

                // find bottom-most space available and set the token
                for (int row = 0; row < curGame.getNumRows(); row++) {

                    char atPos = curGame.whatsAtPos(new BoardPosition(row, col));
                    if (atPos == ' ') {
                        curGame.dropToken(players[playerItr], col);
                        screen.setMarker(row, col, players[playerItr]);
                        break;
                    }
                }

                //System.out.println(curGame.toString());
            }
        }

        // b.) if yes, re-prompt player for a new game
        if (curGame.checkForWin(col)) {
            winnerPlayer = players[playerItr];
            this.newGame(isTie);
        }
        else if (curGame.checkTie()) {
            isTie = true;
            this.newGame(isTie);
        }

        playerItr++;
        playerTurn++;
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     * 
     * @post [ a new game gets started ]
     */
    private void newGame(boolean gameTied) {
        //close the current screen
        screen.dispose();

        // create another JFrame class where it has two buttons, one for playing again and one for not
        PlayAgainView play = new PlayAgainView(winnerPlayer, gameTied);
        PlayAgainController controller = new PlayAgainController(play);
        play.registerObserver(controller);
    }
}