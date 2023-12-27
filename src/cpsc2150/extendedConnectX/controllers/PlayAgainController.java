package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.views.PlayAgainView;
import cpsc2150.extendedConnectX.views.SetupView;

/**
 * This class is the controller for the play again screen. It contains two methods which process the
 * yes and no buttons that appear on the view. If the player selects yes, the play again screen is closed
 * and the set-up screen is initialized. If the player selects no, the play again screen is just closed and the
 * program ends.
 */
public class PlayAgainController {

    /**
     * <p>
     * This stores the setup view associated with this controller.
     * </p>
     */
    private PlayAgainView view;

    /**
     * <p>
     * This creates a new setup controller.
     * </p>
     *
     * @param v
     *      The setup view associated with this controller.
     */
    public PlayAgainController(PlayAgainView v) {
        view = v;
    }

    /**
     * <p>
     * This processes the yes button click.
     * </p>
     *
     * @post a new set-up screen is initialized to play the game again
     */
    public void yesButtonClick() {

        view.dispose();

        //start back at the set-up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }

    /**
     * <p>
     * This processes the no button click.
     * </p>
     */
    public void noButtonClick() {
        view.dispose();
    }
}
