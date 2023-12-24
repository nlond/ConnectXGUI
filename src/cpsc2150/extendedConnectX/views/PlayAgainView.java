package cpsc2150.extendedConnectX.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayAgainView extends JFrame implements ActionListener {

    public PlayAgainView(char whoWon) {

        super("Play Again?");

        this.setLayout(new BorderLayout());

        // create 5 panels
        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        // set each panel to a different color
        topPanel.setBackground(Color.RED);
        leftPanel.setBackground(Color.GREEN);
        rightPanel.setBackground(Color.YELLOW);
        bottomPanel.setBackground(Color.MAGENTA);
        centerPanel.setBackground(Color.BLUE);

        // set size of panels
        topPanel.setPreferredSize(new Dimension(100, 50));
        leftPanel.setPreferredSize(new Dimension(50, 100));
        rightPanel.setPreferredSize(new Dimension(50, 100));
        bottomPanel.setPreferredSize(new Dimension(100, 50));
        centerPanel.setPreferredSize(new Dimension(100, 100));

        this.add(topPanel, BorderLayout.NORTH); // attaches panel to the top of this
        this.add(leftPanel, BorderLayout.WEST); // attaches to the left of this
        this.add(rightPanel, BorderLayout.EAST); // attaches to the right of this
        this.add(bottomPanel, BorderLayout.SOUTH); // attaches to bottom of this
        this.add(centerPanel, BorderLayout.CENTER); // attaches to the center of frame

        // new panel inside of center panel -----------------------------------

        centerPanel.setLayout(new BorderLayout()); // makes the center panel have a layout

        JPanel centerTopPanel = new JPanel(); // create a new panel
        centerTopPanel.setBackground(Color.black);

        //frame settings
        this.setSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean playAgain() {

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
