package cpsc2150.extendedConnectX.views;

import cpsc2150.extendedConnectX.controllers.PlayAgainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains the code that displays the screen that prompts the user if
 * they want to play the game again
 * <p>
 */
public class PlayAgainView extends JFrame implements ActionListener {

    private JButton yesBtn, noBtn;
    private PlayAgainController controller;

    /**
     * <p>
     * This constructor creates a screen for asking if the player if they want to play again or not
     * </p>
     *
     * @param whoWon
     *       character of the player that won the game that will be displayed on the screen
     *
     * @param isTie
     *       boolean. If this variable is true, then the game resulted in a tie
     *
     * @post [ a functional screen with yes and no options ]
     */
    public PlayAgainView(char whoWon, boolean isTie) {

        super("Play Again?");

        this.setLayout(new BorderLayout());

        // create 5 panels
        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        // set size of panels
        topPanel.setPreferredSize(new Dimension(100, 100));
        leftPanel.setPreferredSize(new Dimension(50, 100));
        rightPanel.setPreferredSize(new Dimension(50, 100));
        bottomPanel.setPreferredSize(new Dimension(100, 100));
        centerPanel.setPreferredSize(new Dimension(100, 100));

        this.add(topPanel, BorderLayout.NORTH); // attaches panel to the top of this
        this.add(leftPanel, BorderLayout.WEST); // attaches to the left of this
        this.add(rightPanel, BorderLayout.EAST); // attaches to the right of this
        this.add(bottomPanel, BorderLayout.SOUTH); // attaches to bottom of this
        this.add(centerPanel, BorderLayout.CENTER); // attaches to the center of frame

        // ------------------- text inside center panel ------------------------------------

        centerPanel.setLayout(new BorderLayout()); // makes the center panel have a layout

        JPanel centerTopPanel = new JPanel(); // create a new panel

        centerTopPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // Add some space between components

        JPanel centerMidPanel = new JPanel();
        JPanel centerLowPanel = new JPanel();

        centerTopPanel.setPreferredSize(new Dimension(100, 100));
        centerMidPanel.setPreferredSize(new Dimension(100, 5));
        centerLowPanel.setPreferredSize(new Dimension(100, 100));

        centerTopPanel.setBackground(Color.lightGray);
        centerMidPanel.setBackground(Color.lightGray);

        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        centerPanel.add(centerMidPanel, BorderLayout.CENTER);
        centerPanel.add(centerLowPanel, BorderLayout.SOUTH);

        // if the game resulted in a tie, text should display that
        if (isTie) {
            JLabel tieText = new JLabel("It is a Tie!");
            tieText.setFont(new Font("Arial", Font.BOLD, 20));
            centerTopPanel.add(tieText, gbc);
        }
        // if no tie, the player who won the game is displayed
        else {
            JLabel winnerText = new JLabel("Player " + whoWon + " Has Won!");
            winnerText.setFont(new Font("Arial", Font.BOLD, 20));
            centerTopPanel.add(winnerText, gbc);
        }

        gbc.gridy = 1;
        JLabel promptLabel = new JLabel("Do you want to play again?");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 20));
        centerTopPanel.add(promptLabel, gbc);

        // -------------------- yes and no buttons ---------------------------
        centerMidPanel.setLayout(new FlowLayout());

        yesBtn = new JButton("Yes");
        noBtn = new JButton("No");

        // button settings
        yesBtn.setFont(new Font("Arial", Font.BOLD, 10));
        noBtn.setFont(new Font("Arial", Font.BOLD, 10));

        yesBtn.setFocusable(false);
        noBtn.setFocusable(false);

        centerMidPanel.add(yesBtn);
        centerMidPanel.add(Box.createHorizontalStrut(10));
        centerMidPanel.add(noBtn);

        yesBtn.addActionListener(this);
        noBtn.addActionListener(this);

        //frame settings
        this.setSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * <p>
     * This method registers argument as observer/listener of this; this must be done first,
     * before any other methods of this class are called.
     * </p>
     *
     * @param c
     *      Controller to register
     *
     * @pre [ c is a valid controller for this view ]
     * @post this.controller = c
     */
    public void registerObserver(PlayAgainController c) {
        controller = c;
    }

    /**
     * <p>
     * This is the callback method that gets called as part of the
     * observer pattern.
     * </p>
     *
     * @param e
     *      The event on the screen that is observed
     *
     * @post [ button events will be sent to the controller ]
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == yesBtn) {
            controller.yesButtonClick();
        }
        else if (e.getSource() == noBtn) {
            controller.noButtonClick();
        }

    }
}
