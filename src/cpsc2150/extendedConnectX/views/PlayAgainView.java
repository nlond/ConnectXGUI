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

        centerTopPanel.setBackground(Color.red);
        centerMidPanel.setBackground(Color.lightGray);

        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        centerPanel.add(centerMidPanel, BorderLayout.CENTER);
        centerPanel.add(centerLowPanel, BorderLayout.SOUTH);

        JLabel label = new JLabel("Player " + whoWon + " Has Won!");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        centerTopPanel.add(label, gbc);

        gbc.gridy = 1;
        JLabel label2 = new JLabel("Do you want to play again?");
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        centerTopPanel.add(label2, gbc);

        // -------------------- yes and no buttons ---------------------------
        centerMidPanel.setLayout(new FlowLayout());

        JButton yesBtn = new JButton("Yes");
        JButton noBtn = new JButton("No");

        // button settings


        centerMidPanel.add(yesBtn);
        centerMidPanel.add(Box.createHorizontalStrut(10));
        centerMidPanel.add(noBtn);

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
