package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.views.PlayAgainView;
import cpsc2150.extendedConnectX.views.SetupView;

public class PlayAgainController {

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

    public void yesButtonClick() {

        view.dispose();

        //start back at the set-up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }

    public void noButtonClick() {
        view.dispose();
    }
}
