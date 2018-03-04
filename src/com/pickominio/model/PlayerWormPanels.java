package com.pickominio.model;

import javax.swing.*;
import java.awt.*;

public class PlayerWormPanels extends JFrame {
    public JPanel PlayerWormsPaneMain;
    public JPanel PlayerWormsPaneTitle;
    public JPanel PlayerWormsPaneWorms;

    public JTextField PlayerWormsTextField;

    public PlayerWormPanels(String playername, int playernumber, Font titlefont) {
        PlayerWormsPaneMain = new JPanel();
        PlayerWormsPaneMain.setOpaque(false);
        PlayerWormsPaneTitle = new JPanel();
        PlayerWormsPaneTitle.setOpaque(false);
        PlayerWormsPaneWorms = new JPanel();
        PlayerWormsPaneWorms.setOpaque(false);
        PlayerWormsPaneMain.setLayout(new BorderLayout());
        PlayerWormsPaneMain.setPreferredSize(new Dimension(800, 135));
        PlayerWormsPaneTitle.setLayout(new FlowLayout());
        PlayerWormsPaneTitle.setPreferredSize(new Dimension(200, 35));
        PlayerWormsPaneWorms.setLayout(new FlowLayout());
        PlayerWormsPaneWorms.setPreferredSize(new Dimension(800, 100));
        PlayerWormsTextField = new JTextField("          " + playernumber + ". " + playername + "'s Worms" + "          ");
        PlayerWormsTextField.setOpaque(false);
        PlayerWormsTextField.setEditable(false);
        PlayerWormsTextField.setFont(titlefont);
        PlayerWormsTextField.setHorizontalAlignment(JTextField.CENTER);
        PlayerWormsTextField.setBackground(null);
        PlayerWormsTextField.setBorder(null);
        PlayerWormsPaneTitle.add(PlayerWormsTextField);
        PlayerWormsPaneMain.add(PlayerWormsPaneTitle, BorderLayout.NORTH);
        PlayerWormsPaneMain.add(PlayerWormsPaneWorms, BorderLayout.CENTER);
    }
}
