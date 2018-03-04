package com.pickominio.controller;

import com.pickominio.model.*;
import com.pickominio.service.ActivePlayerActions;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JFrame {

    private ActivePlayerActions currentplayeractions;

    private static int ActivePlayerCount = 0;
    private static int NUMBEROFPLAYERS = 0;
    private static int activedicenumberselected = 0;
    private static int activegrillwormnumberselected = 0;
    private static int activeplayerwormnumberselected = 0;
    private static int ActiveDiceFaceRemainingCheck = 0;
    private static int AttackedPlayer = 99;
    private static int WinningPlayerScore = 0;

    private static String WinningPlayerName;

    private static boolean EndPlayerTurn = false;
    private static boolean TakeWormFromGrillAvailable = false;
    private static boolean TakeWormFromPlayerAvailable = false;
    private static boolean ActiveDiceButtonsAreGreen = true;
    private static boolean listenfordicenumber = true;
    private static boolean listenforgrillwormnumber = false;
    private static boolean listenforplayerwormnumber = false;
    private static boolean lastchancewormsteal = false;

    private ArrayList<JButton> GrillWormButtons = new ArrayList<JButton>();
    private ArrayList<JButton> ActiveDiceButtons = new ArrayList<JButton>();
    private ArrayList<JButton> FrozenDiceButtons = new ArrayList<JButton>();
    private ArrayList<JButton> PlayerWormButtons = new ArrayList<JButton>();
    private ArrayList<PlayerWormPanels> PlayerWormPanelArray = new ArrayList<PlayerWormPanels>();
    private ArrayList<PlayerWorms> PlayerWormsArray = new ArrayList<PlayerWorms>();
    private ArrayList<ActivePlayerActions> ActivePlayerActionsArray = new ArrayList<ActivePlayerActions>();
    private ArrayList<Integer> ScoreArray = new ArrayList<Integer>();

    private ImageIcon DiceImageOnePip = new ImageIcon(getClass().getResource("/images/DiceFaceOne.png"));
    private Image DiceImageOnePipImage = DiceImageOnePip.getImage();
    private ImageIcon DiceImageTwoPip = new ImageIcon(getClass().getResource("/images/DiceFaceTwo.png"));
    private Image DiceImageTwoPipImage = DiceImageTwoPip.getImage();
    private ImageIcon DiceImageThreePip = new ImageIcon(getClass().getResource("/images/DiceFaceThree.png"));
    private Image DiceImageThreePipImage = DiceImageThreePip.getImage();
    private ImageIcon DiceImageFourPip = new ImageIcon(getClass().getResource("/images/DiceFaceFour.png"));
    private Image DiceImageFourPipImage = DiceImageFourPip.getImage();
    private ImageIcon DiceImageFivePip = new ImageIcon(getClass().getResource("/images/DiceFaceFive.png"));
    private Image DiceImageFivePipImage = DiceImageFivePip.getImage();
    private ImageIcon DiceImageWorm = new ImageIcon(getClass().getResource("/images/OneWormTile.png"));
    private Image DiceImageWormImage = DiceImageWorm.getImage();

    private ImageIcon OneWormTileIcon = new ImageIcon(getClass().getResource("/images/OneWormTile.png"));
    private Image OneWormTileImage = OneWormTileIcon.getImage();
    private ImageIcon TwoWormTileIcon = new ImageIcon(getClass().getResource("/images/TwoWormTile.png"));
    private Image TwoWormTileImage = TwoWormTileIcon.getImage();
    private ImageIcon ThreeWormTileIcon = new ImageIcon(getClass().getResource("/images/ThreeWormTile.png"));
    private Image ThreeWormTileImage = ThreeWormTileIcon.getImage();
    private ImageIcon FourWormTileIcon = new ImageIcon(getClass().getResource("/images/FourWormTile.png"));
    private Image FourWormTileImage = FourWormTileIcon.getImage();

    private ImageIcon BackgroundImageIcon = new ImageIcon(getClass().getResource("/images/BackgroundImage.png"));

    private JButton RollDiceButtonGray;
    private JButton RollDiceButtonGreen;

    private JTextField ActiveDiceTextField;
    private JTextField FrozenDiceTextField;
    private JTextField DiceSumTitlePaneTextField;
    private JTextField DiceSumOutputPaneTextField;
    private JTextField OptionPaneTextField;
    private JTextField GrillPaneTextField;

    private static Font TitleMessageFont = new Font("SansSerif", Font.BOLD, 20);

    private ImagePanel MainPane;
    private JPanel GamePane;
    private JPanel GrillPaneMain;
    private JPanel GrillPaneTitle;
    private JPanel GrillPaneWorms;
    private JPanel ActiveDicePaneMain;
    private JPanel ActiveDicePaneTitle;
    private JPanel ActiveDicePaneDice;
    private JPanel FrozenDicePaneMain;
    private JPanel FrozenDicePaneTitle;
    private JPanel FrozenDicePaneDice;
    private JPanel DiceSumPaneMain;
    private JPanel DiceSumPaneTitle;
    private JPanel DiceSumPaneOutput;
    private JPanel OptionPaneMain;
    private JPanel OptionPaneTitle;
    private JPanel OptionPaneOptions;

    public Game() {
        super("Pickomino");

        Grill MainGrill = new Grill();
        Dice dice = new Dice();

        NUMBEROFPLAYERS = 0;
        while (NUMBEROFPLAYERS < 1 || NUMBEROFPLAYERS > 7) {
            try {
                NUMBEROFPLAYERS = Integer.valueOf(JOptionPane.showInputDialog("How many players are there? Enter a value between 1 and 7"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 7!");
            }
        }

        for (int x = 0; x < NUMBEROFPLAYERS; x++) {
            PlayerWormsArray.add(new PlayerWorms(JOptionPane.showInputDialog(null, String.format("What is Player %s's name?", x + 1))));
            ActivePlayerActionsArray.add(new ActivePlayerActions(PlayerWormsArray.get(x)));
            ScoreArray.add(0);
        }

        currentplayeractions = ActivePlayerActionsArray.get(ActivePlayerCount);

        MainPane = new ImagePanel(BackgroundImageIcon);
        MainPane.setLayout(new BorderLayout());

        GamePane = new JPanel();
        GamePane.setLayout(new GridBagLayout());
        GamePane.setOpaque(false);

        GrillPaneMain = new JPanel();
        GrillPaneMain.setOpaque(false);
        GrillPaneTitle = new JPanel();
        GrillPaneTitle.setOpaque(false);
        GrillPaneWorms = new JPanel();
        GrillPaneWorms.setOpaque(false);
        GrillPaneMain.setLayout(new BorderLayout());
        GrillPaneMain.setPreferredSize(new Dimension(800, 125));
        GrillPaneTitle.setLayout(new BorderLayout());
        GrillPaneTitle.setPreferredSize(new Dimension(800, 25));
        GrillPaneWorms.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        GrillPaneWorms.setPreferredSize(new Dimension(800, 100));
        for (int x = 0; x < Grill.getGrillWormsSize(); x++) {
            GrillWormButtons.add(new JButton(String.valueOf(Grill.getGrillWormsValue(x)), setWormTileImage(Grill.getGrillWormsValue(x))));
            GrillWormButtons.get(x).setVerticalTextPosition(SwingConstants.TOP);
            GrillWormButtons.get(x).setHorizontalTextPosition(SwingConstants.CENTER);
            GrillWormButtons.get(x).setPreferredSize(new Dimension(60, 80));
            GrillPaneWorms.add(GrillWormButtons.get(x));
        }
        GrillPaneTextField = new JTextField("          " + "Worms On The Grill" + "          ");
        GrillPaneTextField.setEditable(false);
        GrillPaneTextField.setOpaque(false);
        GrillPaneTextField.setFont(TitleMessageFont);
        GrillPaneTextField.setHorizontalAlignment(JTextField.CENTER);
        GrillPaneTextField.setBackground(null);
        GrillPaneTextField.setBorder(null);
        GrillPaneTitle.add(GrillPaneTextField);
        GrillPaneMain.add(GrillPaneTitle, BorderLayout.NORTH);
        GrillPaneMain.add(GrillPaneWorms, BorderLayout.CENTER);

        ActiveDicePaneMain = new JPanel();
        ActiveDicePaneMain.setOpaque(false);
        ActiveDicePaneTitle = new JPanel();
        ActiveDicePaneTitle.setOpaque(false);
        ActiveDicePaneDice = new JPanel();
        ActiveDicePaneDice.setOpaque(false);
        ActiveDicePaneMain.setLayout(new BorderLayout());
        ActiveDicePaneMain.setPreferredSize(new Dimension(600, 70));
        ActiveDicePaneTitle.setLayout(new BorderLayout());
        ActiveDicePaneTitle.setPreferredSize(new Dimension(800, 25));
        ActiveDicePaneDice.setLayout(new FlowLayout());
        ActiveDicePaneDice.setPreferredSize(new Dimension(400, 45));
        ActiveDiceTextField = new JTextField("          " + "Active Dice" + "          ");
        ActiveDiceTextField.setOpaque(false);
        ActiveDiceTextField.setEditable(false);
        ActiveDiceTextField.setFont(TitleMessageFont);
        ActiveDiceTextField.setHorizontalAlignment(JTextField.CENTER);
        ActiveDiceTextField.setBackground(null);
        ActiveDiceTextField.setBorder(null);
        ActiveDicePaneTitle.add(ActiveDiceTextField);
        performActiveDicePaneupdate();
        ActiveDicePaneMain.add(ActiveDicePaneTitle, BorderLayout.NORTH);
        ActiveDicePaneMain.add(ActiveDicePaneDice, BorderLayout.CENTER);

        FrozenDicePaneMain = new JPanel();
        FrozenDicePaneMain.setOpaque(false);
        FrozenDicePaneTitle = new JPanel();
        FrozenDicePaneTitle.setOpaque(false);
        FrozenDicePaneDice = new JPanel();
        FrozenDicePaneDice.setOpaque(false);
        FrozenDicePaneMain.setLayout(new BorderLayout());
        FrozenDicePaneMain.setPreferredSize(new Dimension(600, 70));
        FrozenDicePaneTitle.setLayout(new BorderLayout());
        FrozenDicePaneTitle.setPreferredSize(new Dimension(200, 25));
        FrozenDicePaneDice.setLayout(new FlowLayout());
        FrozenDicePaneDice.setPreferredSize(new Dimension(400, 45));
        FrozenDiceTextField = new JTextField("          " + "Frozen Dice" + "          ");
        FrozenDiceTextField.setOpaque(false);
        FrozenDiceTextField.setEditable(false);
        FrozenDiceTextField.setFont(TitleMessageFont);
        FrozenDiceTextField.setHorizontalAlignment(JTextField.CENTER);
        FrozenDiceTextField.setBackground(null);
        FrozenDiceTextField.setBorder(null);
        FrozenDicePaneTitle.add(FrozenDiceTextField);
        performFrozenDicePaneupdate();
        FrozenDicePaneMain.add(FrozenDicePaneTitle, BorderLayout.NORTH);
        FrozenDicePaneMain.add(FrozenDicePaneDice, BorderLayout.CENTER);

        DiceSumPaneMain = new JPanel();
        DiceSumPaneMain.setOpaque(false);
        DiceSumPaneTitle = new JPanel();
        DiceSumPaneTitle.setOpaque(false);
        DiceSumPaneOutput = new JPanel();
        DiceSumPaneOutput.setOpaque(false);
        DiceSumPaneMain.setLayout(new BorderLayout());
        DiceSumPaneTitle.setLayout(new FlowLayout());
        DiceSumPaneOutput.setLayout(new FlowLayout());
        DiceSumTitlePaneTextField = new JTextField("     " + "Sum of All Dice" + "     ");
        DiceSumTitlePaneTextField.setOpaque(false);
        DiceSumTitlePaneTextField.setEditable(false);
        DiceSumTitlePaneTextField.setFont(TitleMessageFont);
        DiceSumTitlePaneTextField.setHorizontalAlignment(JTextField.CENTER);
        DiceSumTitlePaneTextField.setBackground(null);
        DiceSumTitlePaneTextField.setBorder(null);
        DiceSumPaneTitle.add(DiceSumTitlePaneTextField);
        DiceSumOutputPaneTextField = new JTextField("     " + Dice.getDiceSum() + "     ");
        DiceSumOutputPaneTextField.setEditable(false);
        DiceSumPaneOutput.add(DiceSumOutputPaneTextField);
        DiceSumPaneMain.add(DiceSumPaneTitle, BorderLayout.PAGE_START);
        DiceSumPaneMain.add(DiceSumPaneOutput, BorderLayout.PAGE_END);

        OptionPaneMain = new JPanel();
        OptionPaneMain.setOpaque(false);
        OptionPaneTitle = new JPanel();
        OptionPaneTitle.setOpaque(false);
        OptionPaneOptions = new JPanel();
        OptionPaneOptions.setOpaque(false);
        OptionPaneMain.setLayout(new BorderLayout());
        OptionPaneMain.setPreferredSize(new Dimension(600, 70));
        OptionPaneTitle.setLayout(new FlowLayout());
        OptionPaneTitle.setPreferredSize(new Dimension(200, 35));
        OptionPaneOptions.setLayout(new FlowLayout());
        OptionPaneOptions.setPreferredSize(new Dimension(400, 35));
        OptionPaneTextField = new JTextField("          " + "Player Options" + "          ");
        OptionPaneTextField.setOpaque(false);
        OptionPaneTextField.setEditable(false);
        OptionPaneTextField.setFont(TitleMessageFont);
        OptionPaneTextField.setHorizontalAlignment(JTextField.CENTER);
        OptionPaneTextField.setBackground(null);
        OptionPaneTextField.setBorder(null);
        OptionPaneTitle.add(OptionPaneTextField);
        RollDiceButtonGreen = new JGradientButton("Roll Dice Again", Color.GREEN);
        RollDiceButtonGray = new JGradientButton("Roll Dice Again", Color.GRAY);
        OptionPaneOptions.add(RollDiceButtonGray);
        OptionPaneMain.add(OptionPaneTitle, BorderLayout.NORTH);
        OptionPaneMain.add(OptionPaneOptions, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        GamePane.add(GrillPaneMain, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        GamePane.add(OptionPaneMain, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.01;
        GamePane.add(DiceSumPaneMain, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        GamePane.add(ActiveDicePaneMain, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        GamePane.add(FrozenDicePaneMain, gbc);

        for (int x = 0; x < NUMBEROFPLAYERS; x++) {
            PlayerWormPanelArray.add(new PlayerWormPanels(PlayerWormsArray.get(x).getPlayerName(), x + 1, TitleMessageFont));
            if (ActivePlayerCount == x) {
                PlayerWormPanelArray.get(x).PlayerWormsTextField.setOpaque(true);
                PlayerWormPanelArray.get(x).PlayerWormsTextField.setBackground(Color.GREEN);
            }
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 6 + x;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            GamePane.add(PlayerWormPanelArray.get(x).PlayerWormsPaneMain, gbc);
        }

        MainPane.add(GamePane, BorderLayout.CENTER);
        add(MainPane);
        //setUndecorated(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600 + 125 * NUMBEROFPLAYERS);
        setLocationRelativeTo(null);
        setVisible(true);

        RollDiceButtonGreen.addActionListener(new RollDiceButtonHandlerClass());
        RollDiceButtonGray.addActionListener(new RollDiceButtonHandlerClass());

    }

    public static void setEndPlayerTurn(boolean setboolean) {
        EndPlayerTurn = setboolean;
    }

    private class ActiveDiceHandlerClass implements ActionListener {

        private int setdicenumber;

        private ActiveDiceHandlerClass(int dicenumber) {
            setdicenumber = dicenumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            activedicenumberselected = 0;
            if (listenfordicenumber) {
                activedicenumberselected = setdicenumber;
                listenfordicenumber = false;
            }

            if (activedicenumberselected != 0) {
                if (Dice.doesFrozenDiceListContainValue(activedicenumberselected) == false) {
                    currentplayeractions.performFreezeDice(activedicenumberselected);
                    if (Dice.getActiveDiceListSize() > 0) {
                        currentplayeractions.setDiceRollAvailable(true);
                    }
                    TakeWormFromPlayerAvailable = true;
                    TakeWormFromGrillAvailable = true;
                    ActiveDiceButtonsAreGreen = false;
                } else if (Dice.doesFrozenDiceListContainValue(activedicenumberselected)) {
                    JOptionPane.showMessageDialog(null, "You already froze that number, pick another number!");
                    listenfordicenumber = true;
                }
            }
            performGamePanelUpdate();
        }

    }

    private class GrillWormsHandlerClass implements ActionListener {

        private int setwormnumber;

        private GrillWormsHandlerClass(int wormnumber) {
            setwormnumber = wormnumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            activedicenumberselected = 0;
            if (listenforgrillwormnumber) {
                activegrillwormnumberselected = setwormnumber;
            }

            if (activegrillwormnumberselected != 0) {
                if (Grill.doesGrillWormsContainValue(Dice.getDiceSum()) && activegrillwormnumberselected == Dice.getDiceSum()) {
                    currentplayeractions.performTakeWormFromGrill(activegrillwormnumberselected);
                    Grill.RemovePrizeWormFromGrill(activegrillwormnumberselected);
                    currentplayeractions.setDiceRollAvailable(true);
                    listenforgrillwormnumber = false;
                    TakeWormFromPlayerAvailable = false;
                    TakeWormFromGrillAvailable = false;
                    listenfordicenumber = false;
                    listenforgrillwormnumber = false;
                    listenforplayerwormnumber = false;
                    EndPlayerTurn = true;
                } else if (Grill.doesGrillWormsContainValue(Dice.getDiceSum()) == false && Dice.getDiceSum() >= activegrillwormnumberselected) {
                    currentplayeractions.performTakeWormFromGrill(activegrillwormnumberselected);
                    Grill.RemovePrizeWormFromGrill(activegrillwormnumberselected);
                    currentplayeractions.setDiceRollAvailable(true);
                    listenforgrillwormnumber = false;
                    TakeWormFromPlayerAvailable = false;
                    TakeWormFromGrillAvailable = false;
                    listenfordicenumber = false;
                    listenforgrillwormnumber = false;
                    listenforplayerwormnumber = false;
                    EndPlayerTurn = true;
                } else {
                    JOptionPane.showMessageDialog(null, "You cannot select that worm!");
                }
            }
            performGamePanelUpdate();
        }

    }

    private class PlayerWormsHandlerClass implements ActionListener {

        private int setwormnumber;
        private int setattackedplayernumber;

        private PlayerWormsHandlerClass(int playernumber, int wormnumber) {
            setwormnumber = wormnumber;
            setattackedplayernumber = playernumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            activedicenumberselected = 0;
            AttackedPlayer = 99;
            if (listenforplayerwormnumber) {
                activeplayerwormnumberselected = setwormnumber;
                AttackedPlayer = setattackedplayernumber;
            }

            if (activeplayerwormnumberselected != 0 && AttackedPlayer != 99) {
                if (Dice.getDiceSum() == PlayerWormsArray.get(AttackedPlayer).getPlayerWormsArrayListWormValue(0)) {
                    currentplayeractions.performTakeWormFromPlayer(PlayerWormsArray.get(AttackedPlayer).getPlayerWormsArrayListWormValue(0));
                    PlayerWormsArray.get(AttackedPlayer).RemoveStolenWormFromPlayer();
                    currentplayeractions.setDiceRollAvailable(true);
                    TakeWormFromPlayerAvailable = false;
                    TakeWormFromGrillAvailable = false;
                    listenfordicenumber = false;
                    listenforplayerwormnumber = false;
                    listenforgrillwormnumber = false;
                    EndPlayerTurn = true;
                }
            }
            performGamePanelUpdate();
        }

    }


    private class RollDiceButtonHandlerClass implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand() == RollDiceButtonGreen.getText() || event.getActionCommand() == RollDiceButtonGray.getText()) {
                if (currentplayeractions.getDiceRollAvailable() && TakeWormFromGrillAvailable && TakeWormFromPlayerAvailable) {
                    currentplayeractions.performRollDice();
                    performGamePanelUpdate();
                    activedicenumberselected = 0;
                    listenfordicenumber = true;
                    listenforgrillwormnumber = true;
                    listenforplayerwormnumber = true;
                    performGamePanelUpdate();

                    if (Dice.getBunk() == true) {
                        JOptionPane.showMessageDialog(null, "You have bunked!");
                        currentplayeractions.performPlayerBunk();
                    }

                    ActiveDiceFaceRemainingCheck = 0;

                    for (int x = 0; x < 6; x++) {
                        if (Dice.doesActiveDiceListContainValue(x + 1)) {
                            ActiveDiceFaceRemainingCheck++;
                        }
                    }

                    if (ActiveDiceFaceRemainingCheck == 1) {
                        Dice.sumDiceLastChance();
                    }

                    lastchancewormsteal = false;
                    if (NUMBEROFPLAYERS > 1) {
                        for (int z = 0; z < NUMBEROFPLAYERS; z++) {
                            if (ActivePlayerCount != z && PlayerWormsArray.get(z).getPlayerWormsArrayListSize() > 0) {
                                if (PlayerWormsArray.get(z).getPlayerWormsArrayListWormValue(0) == Dice.getLastChanceDiceSum()) {
                                    if (Dice.doesActiveDiceListContainValue(6) || Dice.doesFrozenDiceListContainValue(6)) {
                                        lastchancewormsteal = true;
                                    }
                                }
                            }
                        }
                    }

                    if (lastchancewormsteal == false) {
                        if (ActiveDiceFaceRemainingCheck == 1 && Dice.getLastChanceDiceSum() < Grill.getGrillWormsValue(0)) {
                            JOptionPane.showMessageDialog(null, "You cannot roll anymore and you cannot take a worm; you have bunked!");
                            currentplayeractions.performPlayerBunk();
                        } else if (ActiveDiceFaceRemainingCheck == 1 && Dice.doesActiveDiceListContainValue(6) == false && Dice.doesFrozenDiceListContainValue(6) == false) {
                            JOptionPane.showMessageDialog(null, "You cannot roll anymore and you cannot take a worm; you have bunked!");
                            currentplayeractions.performPlayerBunk();
                        }
                    }

                    ActiveDiceButtonsAreGreen = true;
                    currentplayeractions.setDiceRollAvailable(false);
                    TakeWormFromPlayerAvailable = false;
                    TakeWormFromGrillAvailable = false;
                    performGamePanelUpdate();
                } else if (currentplayeractions.getDiceRollAvailable() && TakeWormFromGrillAvailable == false && TakeWormFromGrillAvailable == false) {
                    Dice.restartAllDice();
                    listenfordicenumber = true;
                    ActiveDiceButtonsAreGreen = true;
                    TakeWormFromGrillAvailable = false;
                    TakeWormFromPlayerAvailable = false;
                    currentplayeractions.setDiceRollAvailable(false);
                    performGamePanelUpdate();
                } else if (currentplayeractions.getDiceRollAvailable() == false && TakeWormFromGrillAvailable == false && TakeWormFromGrillAvailable == false) {
                    JOptionPane.showMessageDialog(null, String.format("You must select a number to freeze from the active dice"));
                } else {
                    JOptionPane.showMessageDialog(null, String.format("You must freeze dice or take a worm!"));
                }
            }
        }
    }

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    private Icon setDiceImage(int DiceFaceNumber) {
        if (DiceFaceNumber == 1) {
            return new ImageIcon(getScaledImage(DiceImageOnePipImage, 50, 50));
        } else if (DiceFaceNumber == 2) {
            return new ImageIcon(getScaledImage(DiceImageTwoPipImage, 50, 50));
        } else if (DiceFaceNumber == 3) {
            return new ImageIcon(getScaledImage(DiceImageThreePipImage, 50, 50));
        } else if (DiceFaceNumber == 4) {
            return new ImageIcon(getScaledImage(DiceImageFourPipImage, 50, 50));
        } else if (DiceFaceNumber == 5) {
            return new ImageIcon(getScaledImage(DiceImageFivePipImage, 50, 50));
        } else if (DiceFaceNumber == 6) {
            return new ImageIcon(getScaledImage(DiceImageWormImage, 50, 50));
        } else {
            JOptionPane.showMessageDialog(null, String.format("There was an dice image error!"));
        }
        return null;
    }

    private Icon setWormTileImage(int WormTileNumber) {
        if (WormTileNumber < 25) {
            return new ImageIcon(getScaledImage(OneWormTileImage, 50, 50));
        } else if (WormTileNumber >= 25 && WormTileNumber <= 28) {
            return new ImageIcon(getScaledImage(TwoWormTileImage, 50, 50));
        } else if (WormTileNumber >= 29 && WormTileNumber <= 32) {
            return new ImageIcon(getScaledImage(ThreeWormTileImage, 50, 50));
        } else if (WormTileNumber > 32) {
            return new ImageIcon(getScaledImage(FourWormTileImage, 50, 50));
        } else {
            JOptionPane.showMessageDialog(null, String.format("There was an worm tile image error!"));
        }
        return null;
    }

    private void performGamePanelUpdate() {
        Dice.sumDice();
        performGrillPaneupdate();
        performOptionPaneupdate();
        performDiceSumPaneupdate();
        performActiveDicePaneupdate();
        performFrozenDicePaneupdate();
        performPlayerWormsPaneupdate();
        MainPane.validate();
        MainPane.repaint();
        performCurrentPlayerCheck();
        performEndOfGameCheck();
    }

    private void performCurrentPlayerCheck() {
        if (EndPlayerTurn) {
            if (ActivePlayerCount < NUMBEROFPLAYERS - 1) {
                ActivePlayerCount++;
            } else {
                ActivePlayerCount = 0;
            }
            currentplayeractions = ActivePlayerActionsArray.get(ActivePlayerCount);
            EndPlayerTurn = false;
            performGamePanelUpdate();
        }
    }

    private void performGrillPaneupdate() {
        GrillWormButtons.clear();
        GrillPaneWorms.removeAll();
        for (int x = 0; x < Grill.getGrillWormsSize(); x++) {
            if (listenforgrillwormnumber == true && Grill.doesGrillWormsContainValue(Dice.getDiceSum()) && Grill.getGrillWormsValue(x) == Dice.getDiceSum() && Dice.doesFrozenDiceListContainValue(6)) {
                GrillWormButtons.add(new JGradientButton(String.valueOf(Grill.getGrillWormsValue(x)), Color.GREEN));
                GrillWormButtons.get(x).setIcon(setWormTileImage(Grill.getGrillWormsValue(x)));
                GrillWormButtons.get(x).setBorder(new LineBorder(Color.GREEN, 4));
                GrillWormButtons.get(x).addActionListener(new GrillWormsHandlerClass(Grill.getGrillWormsValue(x)));
            } else if (listenforgrillwormnumber == true && Grill.doesGrillWormsContainValue(Dice.getDiceSum()) == false && Grill.getGrillWormsValue(x) < Dice.getDiceSum() && Dice.doesFrozenDiceListContainValue(6)) {
                GrillWormButtons.add(new JGradientButton(String.valueOf(Grill.getGrillWormsValue(x)), Color.GREEN));
                GrillWormButtons.get(x).setIcon(setWormTileImage(Grill.getGrillWormsValue(x)));
                GrillWormButtons.get(x).setBorder(new LineBorder(Color.GREEN, 4));
                GrillWormButtons.get(x).addActionListener(new GrillWormsHandlerClass(Grill.getGrillWormsValue(x)));
            } else {
                GrillWormButtons.add(new JButton(String.valueOf(Grill.getGrillWormsValue(x)), setWormTileImage(Grill.getGrillWormsValue(x))));
            }
            GrillWormButtons.get(x).setVerticalTextPosition(SwingConstants.TOP);
            GrillWormButtons.get(x).setHorizontalTextPosition(SwingConstants.CENTER);
            GrillWormButtons.get(x).setPreferredSize(new Dimension(60, 80));
            GrillPaneWorms.add(GrillWormButtons.get(x));
        }

    }

    private void performOptionPaneupdate() {
        OptionPaneOptions.removeAll();
        if (currentplayeractions.getDiceRollAvailable()) {
            OptionPaneOptions.add(RollDiceButtonGreen);
        } else if (currentplayeractions.getDiceRollAvailable() == false) {
            OptionPaneOptions.add(RollDiceButtonGray);
        }
    }

    private void performDiceSumPaneupdate() {
        DiceSumOutputPaneTextField.setText("     " + Dice.getDiceSum() + "     ");
        DiceSumOutputPaneTextField.setEditable(false);
    }

    private void performActiveDicePaneupdate() {
        ActiveDiceButtons.clear();
        ActiveDicePaneDice.removeAll();
        for (int x = 0; x < Dice.getActiveDiceListSize(); x++) {
            if (ActiveDiceButtonsAreGreen && Dice.doesFrozenDiceListContainValue(Dice.getActiveDiceListValue(x)) == false) {
                ActiveDiceButtons.add(new JGradientButton(Color.GREEN));
                ActiveDiceButtons.get(x).setBorder(new LineBorder(Color.GREEN, 4));
            } else {
                ActiveDiceButtons.add(new JButton());
            }
            ActiveDiceButtons.get(x).setIcon(setDiceImage(Dice.getActiveDiceListValue(x)));
            ActiveDiceButtons.get(x).setPreferredSize(new Dimension(60, 60));
            ActiveDiceButtons.get(x).addActionListener(new ActiveDiceHandlerClass((Dice.getActiveDiceListValue(x))));
            ActiveDicePaneDice.add(ActiveDiceButtons.get(x));
        }
    }

    private void performFrozenDicePaneupdate() {
        FrozenDiceButtons.clear();
        FrozenDicePaneDice.removeAll();
        for (int x = 0; x < Dice.getFrozenDiceListSize(); x++) {
            FrozenDiceButtons.add(new JButton(setDiceImage(Dice.getFrozenDiceListValue(x))));
            FrozenDiceButtons.get(x).setPreferredSize(new Dimension(60, 60));
            FrozenDicePaneDice.add(FrozenDiceButtons.get(x));
        }
    }

    private void performPlayerWormsPaneupdate() {
        for (int x = 0; x < NUMBEROFPLAYERS; x++) {
            PlayerWormPanelArray.get(x).PlayerWormsPaneWorms.removeAll();
            PlayerWormButtons.clear();
            if (ActivePlayerCount == x) {
                PlayerWormPanelArray.get(x).PlayerWormsTextField.setOpaque(true);
                PlayerWormPanelArray.get(x).PlayerWormsTextField.setBackground(Color.GREEN);
            } else {
                PlayerWormPanelArray.get(x).PlayerWormsTextField.setBackground(null);
                PlayerWormPanelArray.get(x).PlayerWormsTextField.setOpaque(false);
            }
            for (int y = 0; y < PlayerWormsArray.get(x).getPlayerWormsArrayListSize(); y++) {
                if (PlayerWormsArray.get(x).getPlayerWormsArrayListSize() > 0 && NUMBEROFPLAYERS > 1) {
                    if (listenforplayerwormnumber == true && PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(0) == Dice.getDiceSum() && y == 0 && x != ActivePlayerCount && Dice.doesFrozenDiceListContainValue(6)) {
                        PlayerWormButtons.add(new JGradientButton(String.valueOf(PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y)), Color.GREEN));
                        PlayerWormButtons.get(y).setIcon(setWormTileImage(PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y)));
                        PlayerWormButtons.get(y).setBorder(new LineBorder(Color.GREEN, 4));
                        PlayerWormButtons.get(y).addActionListener(new PlayerWormsHandlerClass(x, Grill.getGrillWormsValue(x)));
                    } else {
                        PlayerWormButtons.add(new JButton(String.valueOf(PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y)), setWormTileImage(PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y))));
                    }
                } else {
                    PlayerWormButtons.add(new JButton(String.valueOf(PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y)), setWormTileImage(PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y))));
                }
                PlayerWormButtons.get(y).setVerticalTextPosition(SwingConstants.TOP);
                PlayerWormButtons.get(y).setHorizontalTextPosition(SwingConstants.CENTER);
                PlayerWormButtons.get(y).setPreferredSize(new Dimension(60, 80));
                PlayerWormPanelArray.get(x).PlayerWormsPaneWorms.add(PlayerWormButtons.get(y));
            }
        }
    }

    private void performEndOfGameCheck() {
        Grill.checkEndOfGameCondition();
        if (Grill.getEndOfGame()) {
            WinningPlayerScore = 0;
            for (int x = 0; x < NUMBEROFPLAYERS; x++) {
                ScoreArray.set(x, 0);
                for (int y = 0; y < PlayerWormsArray.get(x).getPlayerWormsArrayListSize(); y++) {
                    int wormvalue = 0;
                    if (PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y) < 25) {
                        wormvalue = 1;
                    } else if (PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y) >= 25 && PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y) <= 28) {
                        wormvalue = 2;
                    } else if (PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y) >= 29 && PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y) <= 32) {
                        wormvalue = 3;
                    } else if (PlayerWormsArray.get(x).getPlayerWormsArrayListWormValue(y) > 32) {
                        wormvalue = 4;
                    }
                    ScoreArray.set(x, ScoreArray.get(x) + wormvalue);
                }
                if (WinningPlayerScore < ScoreArray.get(x)) {
                    WinningPlayerScore = ScoreArray.get(x);
                    WinningPlayerName = PlayerWormsArray.get(x).getPlayerName();
                }
            }
            JOptionPane.showMessageDialog(null, String.format("Game Over!\n The Winner Is %s with %s worms", WinningPlayerName, WinningPlayerScore));
        }
    }

}

