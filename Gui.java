package Version4;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Gui extends JFrame {
	
	public ActivePlayerActions currentplayeractions;
	public PlayerWorms currentplayerworms;
	public static boolean EndPlayerTurn = false;
	private int ActivePlayerCount = 0;
	private HandlerClass handler;
	private ActiveDiceHandlerClass activedicehandler;
	private Grill MainGrill;	
	private Dice dice;
	private int NUMBEROFPLAYERS;
	private String inputvalue;
	//public static boolean FreezeDiceButtonAvailable = true;
	public static boolean TakeWormFromGrillButtonAvailable = false;
	public static boolean TakeWormFromPlayerButtonAvailable = false;
	private static int WinningPlayerScore;
	private static String WinningPlayerName;
	private int ActiveDiceFaceRemainingCheck;
	private static boolean ActiveDiceButtonsAreGreen=true;
	private int activedicenumberselected=0;
	private static boolean listenfordicenumber=true;
	
	ArrayList<JButton> GrillWormButtons = new ArrayList<JButton>();
	ArrayList<JButton> ActiveDiceButtons = new ArrayList<JButton>();
	ArrayList<JButton> FrozenDiceButtons = new ArrayList<JButton>();
	ArrayList<JButton> PlayerWormButtons = new ArrayList<JButton>();
	ArrayList<createPlayerWormPanels> PlayerWormPanelArray = new ArrayList<createPlayerWormPanels>();
	ArrayList<PlayerWorms> PlayerWormsArray = new ArrayList<PlayerWorms>();
	ArrayList<ActivePlayerActions> ActivePlayerActionsArray = new ArrayList<ActivePlayerActions>();
	ArrayList<Integer> ScoreArray = new ArrayList<Integer>();
	
	private ImageIcon DiceImageOnePip = new ImageIcon(getClass().getResource("DiceFaceOne.png"));
	private Image DiceImageOnePipImage = DiceImageOnePip.getImage();
	private ImageIcon DiceImageTwoPip = new ImageIcon(getClass().getResource("DiceFaceTwo.png"));
	private Image DiceImageTwoPipImage = DiceImageTwoPip.getImage();
	private ImageIcon DiceImageThreePip = new ImageIcon(getClass().getResource("DiceFaceThree.png"));
	private Image DiceImageThreePipImage = DiceImageThreePip.getImage();
	private ImageIcon DiceImageFourPip = new ImageIcon(getClass().getResource("DiceFaceFour.png"));
	private Image DiceImageFourPipImage = DiceImageFourPip.getImage();
	private ImageIcon DiceImageFivePip = new ImageIcon(getClass().getResource("DiceFaceFive.png"));
	private Image DiceImageFivePipImage = DiceImageFivePip.getImage();
	private ImageIcon DiceImageWorm = new ImageIcon(getClass().getResource("OneWormTile.png"));
	private Image DiceImageWormImage = DiceImageWorm.getImage();
	
	private ImageIcon OneWormTileIcon = new ImageIcon(getClass().getResource("OneWormTile.png"));
	private Image OneWormTileImage = OneWormTileIcon.getImage();
	private ImageIcon TwoWormTileIcon = new ImageIcon(getClass().getResource("TwoWormTile.png"));
	private Image TwoWormTileImage = TwoWormTileIcon.getImage();
	private ImageIcon ThreeWormTileIcon = new ImageIcon(getClass().getResource("ThreeWormTile.png"));
	private Image ThreeWormTileImage = ThreeWormTileIcon.getImage();
	private ImageIcon FourWormTileIcon = new ImageIcon(getClass().getResource("FourWormTile.png"));
	private Image FourWormTileImage = FourWormTileIcon.getImage();
	
	//private JButton RollDiceButton;
	private JButton RollDiceButtonGray;
	private JButton RollDiceButtonGreen;
	//private JButton FreezeDiceButton;
	//private JButton FreezeDiceButtonGray;
	//private JButton FreezeDiceButtonGreen;
	//private JButton TakeWormFromGrillButton;
	private JButton TakeWormFromGrillButtonGreen;
	private JButton TakeWormFromGrillButtonGray;
	//private JButton TakeWormFromPlayerButton;
	private JButton TakeWormFromPlayerButtonGreen;
	private JButton TakeWormFromPlayerButtonGray;
	
	//private JTextField GameStatusTitlePaneTextField;
	//private JTextField GameStatusOutputPaneTextField;
	private JTextField ActiveDiceTextField;
	private JTextField FrozenDiceTextField;
	private JTextField DiceSumTitlePaneTextField;
	private JTextField DiceSumOutputPaneTextField;
	private JTextField OptionPaneTextField;
	private JTextField GrillPaneTextField;
	
	public static Font TitleMessageFont = new Font("SansSerif", Font.BOLD, 20);
	
	private JPanel MainPane;
	private JPanel GamePane;
	//private JPanel GameStatusPaneMain;
	//private JPanel GameStatusPaneTitle;
	//private JPanel GameStatusPaneOutput;
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
	
	public Gui(){
		super("Pickomino");
		MainGrill = new Grill();	
		dice = new Dice();;
		while(NUMBEROFPLAYERS<1 || NUMBEROFPLAYERS>7){
				try{
					NUMBEROFPLAYERS = Integer.valueOf(JOptionPane.showInputDialog("How many players are there? Enter a value between 1 and 7"));
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 7!");
				}
		}
		for(int x=0; x<NUMBEROFPLAYERS; x++){
			PlayerWormsArray.add(new PlayerWorms(JOptionPane.showInputDialog(null, String.format("What is Player %s's name?", x+1))));
			ActivePlayerActionsArray.add(new ActivePlayerActions(PlayerWormsArray.get(x)));
			ScoreArray.add(x,0);
		}		
		currentplayerworms = PlayerWormsArray.get(ActivePlayerCount);
		currentplayeractions = ActivePlayerActionsArray.get(ActivePlayerCount);
		
		MainPane = new JPanel(new BorderLayout());
        MainPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
		GamePane = new JPanel();
		GamePane.setLayout(new GridBagLayout());
		
//		GameStatusPaneMain = new JPanel();
//		GameStatusPaneTitle = new JPanel();
//		GameStatusPaneOutput = new JPanel();
//		GameStatusPaneMain.setLayout(new BorderLayout());
//		GameStatusPaneMain.setPreferredSize(new Dimension(600,50));
//		GameStatusPaneTitle.setLayout(new FlowLayout());
//		GameStatusPaneTitle.setPreferredSize(new Dimension(200,25));
//		GameStatusPaneOutput.setLayout(new FlowLayout());
//		GameStatusPaneOutput.setPreferredSize(new Dimension(400,25));
//		GameStatusTitlePaneTextField = new JTextField("     " + currentplayerworms.playername +  " is up" + "     ");
//		GameStatusPaneTitle.add(GameStatusTitlePaneTextField);		
//		GameStatusOutputPaneTextField = new JTextField("     " + "Game Status Update Here" + "     ");
//		GameStatusPaneOutput.add(GameStatusOutputPaneTextField);
//		GameStatusPaneMain.add(GameStatusPaneTitle, BorderLayout.NORTH);
//		GameStatusPaneMain.add(GameStatusPaneOutput, BorderLayout.CENTER);
		
		GrillPaneMain = new JPanel();
		GrillPaneTitle = new JPanel();
		GrillPaneWorms = new JPanel();
		GrillPaneMain.setLayout(new BorderLayout());
		GrillPaneMain.setPreferredSize(new Dimension(800,125));
		GrillPaneTitle.setLayout(new BorderLayout());
		GrillPaneTitle.setPreferredSize(new Dimension(800,25));
		GrillPaneWorms.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		GrillPaneWorms.setPreferredSize(new Dimension(800,100));
		for(int x=0; x<Grill.grillworms.size(); x++){
			GrillWormButtons.add(new JButton(Grill.grillworms.get(x).toString(), setWormTileImage(Grill.grillworms.get(x))));
			GrillWormButtons.get(x).setVerticalTextPosition(SwingConstants.TOP);
			GrillWormButtons.get(x).setHorizontalTextPosition(SwingConstants.CENTER);
			GrillWormButtons.get(x).setPreferredSize(new Dimension(60,80));
			GrillPaneWorms.add(GrillWormButtons.get(x));
		}
		GrillPaneTextField = new JTextField("          " + "Worms On The Grill" + "          ");
		GrillPaneTextField.setFont(TitleMessageFont);
		GrillPaneTextField.setHorizontalAlignment(JTextField.CENTER);
		GrillPaneTextField.setBackground(null);
		GrillPaneTextField.setBorder(null);
		GrillPaneTitle.add(GrillPaneTextField);
		GrillPaneMain.add(GrillPaneTitle, BorderLayout.NORTH);
		GrillPaneMain.add(GrillPaneWorms, BorderLayout.CENTER);		
	
		ActiveDicePaneMain = new JPanel();
		ActiveDicePaneTitle = new JPanel();
		ActiveDicePaneDice = new JPanel();
		ActiveDicePaneMain.setLayout(new BorderLayout());
		ActiveDicePaneMain.setPreferredSize(new Dimension(600,60));
		ActiveDicePaneTitle.setLayout(new BorderLayout());
		ActiveDicePaneTitle.setPreferredSize(new Dimension(800,25));
		ActiveDicePaneDice.setLayout(new FlowLayout());
		ActiveDicePaneDice.setPreferredSize(new Dimension(400,35));
		ActiveDiceTextField = new JTextField("          " + "Active Dice" + "          ");
		ActiveDiceTextField.setFont(TitleMessageFont);
		ActiveDiceTextField.setHorizontalAlignment(JTextField.CENTER);
		ActiveDiceTextField.setBackground(null);
		ActiveDiceTextField.setBorder(null);
		ActiveDicePaneTitle.add(ActiveDiceTextField);
		performActiveDicePaneupdate();
		ActiveDicePaneMain.add(ActiveDicePaneTitle, BorderLayout.NORTH);
		ActiveDicePaneMain.add(ActiveDicePaneDice, BorderLayout.CENTER);
		
		FrozenDicePaneMain = new JPanel();
		FrozenDicePaneTitle = new JPanel();
		FrozenDicePaneDice = new JPanel();
		FrozenDicePaneMain.setLayout(new BorderLayout());
		FrozenDicePaneMain.setPreferredSize(new Dimension(600,60));
		FrozenDicePaneTitle.setLayout(new BorderLayout());
		FrozenDicePaneTitle.setPreferredSize(new Dimension(200,25));
		FrozenDicePaneDice.setLayout(new FlowLayout());
		FrozenDicePaneDice.setPreferredSize(new Dimension(400,35));
		FrozenDiceTextField = new JTextField("          " + "Frozen Dice" + "          ");
		FrozenDiceTextField.setFont(TitleMessageFont);
		FrozenDiceTextField.setHorizontalAlignment(JTextField.CENTER);
		FrozenDiceTextField.setBackground(null);
		FrozenDiceTextField.setBorder(null);
		FrozenDicePaneTitle.add(FrozenDiceTextField);
		performFrozenDicePaneupdate();
		FrozenDicePaneMain.add(FrozenDicePaneTitle, BorderLayout.NORTH);
		FrozenDicePaneMain.add(FrozenDicePaneDice, BorderLayout.CENTER);
		
		DiceSumPaneMain = new JPanel();
		DiceSumPaneTitle = new JPanel();
		DiceSumPaneOutput = new JPanel();
		DiceSumPaneMain.setLayout(new BorderLayout());
		//DiceSumPaneMain.setPreferredSize(new Dimension(600,30));
		DiceSumPaneTitle.setLayout(new FlowLayout());
		//DiceSumPaneTitle.setPreferredSize(new Dimension(200,15));
		DiceSumPaneOutput.setLayout(new FlowLayout());
		//DiceSumPaneOutput.setPreferredSize(new Dimension(400,15));
		DiceSumTitlePaneTextField = new JTextField("     " + "Sum of All Dice" + "     ");
		DiceSumTitlePaneTextField.setFont(TitleMessageFont);
		DiceSumTitlePaneTextField.setHorizontalAlignment(JTextField.CENTER);
		DiceSumTitlePaneTextField.setBackground(null);
		DiceSumTitlePaneTextField.setBorder(null);
		DiceSumPaneTitle.add(DiceSumTitlePaneTextField);		
		DiceSumOutputPaneTextField = new JTextField("     " + Dice.dicesum + "     ");
		DiceSumPaneOutput.add(DiceSumOutputPaneTextField);
		DiceSumPaneMain.add(DiceSumPaneTitle, BorderLayout.PAGE_START);
		DiceSumPaneMain.add(DiceSumPaneOutput, BorderLayout.PAGE_END);
		
		OptionPaneMain = new JPanel();
		OptionPaneTitle = new JPanel();
		OptionPaneOptions = new JPanel();
		OptionPaneMain.setLayout(new BorderLayout());
		OptionPaneMain.setPreferredSize(new Dimension(600,60));
		OptionPaneTitle.setLayout(new FlowLayout());
		OptionPaneTitle.setPreferredSize(new Dimension(200,25));
		OptionPaneOptions.setLayout(new FlowLayout());
		OptionPaneOptions.setPreferredSize(new Dimension(400,35));
		OptionPaneTextField = new JTextField("          " + "Player Options" + "          ");
		OptionPaneTextField.setFont(TitleMessageFont);
		OptionPaneTextField.setHorizontalAlignment(JTextField.CENTER);
		OptionPaneTextField.setBackground(null);
		OptionPaneTextField.setBorder(null);
		OptionPaneTitle.add(OptionPaneTextField);
		//RollDiceButton = new JButton("Roll Dice Again");;
		//RollDiceButton.setBackground(Color.GRAY);
		RollDiceButtonGreen = new JGradientButton("Roll Dice Again", Color.GREEN);
		RollDiceButtonGray = new JGradientButton("Roll Dice Again", Color.GRAY);
		//FreezeDiceButton = new JButton("Freeze Dice Grouping");
		//FreezeDiceButtonGreen = new JGradientButton("Freeze Dice Grouping", Color.GREEN);
		//FreezeDiceButtonGray = new JGradientButton("Freeze Dice Grouping", Color.GRAY);
		//TakeWormFromGrillButton = new JButton("Take Worm From Grill");
		TakeWormFromGrillButtonGreen = new JGradientButton("Take Worm From Grill", Color.GREEN);
		TakeWormFromGrillButtonGray = new JGradientButton("Take Worm From Grill", Color.GRAY);
		//TakeWormFromPlayerButton = new JButton("Take Worm From Player");
		TakeWormFromPlayerButtonGreen = new JGradientButton("Take Worm From Player", Color.GREEN);
		TakeWormFromPlayerButtonGray = new JGradientButton("Take Worm From Player", Color.GRAY);
		//OptionPaneOptions.add(RollDiceButton);
		OptionPaneOptions.add(RollDiceButtonGray);
		//OptionPaneOptions.add(FreezeDiceButtonGreen);
		OptionPaneOptions.add(TakeWormFromGrillButtonGreen);
		if(NUMBEROFPLAYERS>1){
			OptionPaneOptions.add(TakeWormFromPlayerButtonGreen);
		}
		OptionPaneMain.add(OptionPaneTitle, BorderLayout.NORTH);
		OptionPaneMain.add(OptionPaneOptions, BorderLayout.CENTER);
		
//		for(int x=0; x<NUMBEROFPLAYERS; x++){
//			PlayerWormPanelArray.add(new createPlayerWormPanels(PlayerWormsArray.get(x).playername, x+1));
//		}		
		
        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.ipadx = 0;
//        gbc.ipady = 0;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1.0;
//        gbc.weighty = 0.5;
//		GamePane.add(GameStatusPaneMain, gbc);
		
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
			
		for(int x=0; x<NUMBEROFPLAYERS; x++){
			PlayerWormPanelArray.add(new createPlayerWormPanels(PlayerWormsArray.get(x).playername, x+1));
			if(ActivePlayerCount==x){
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
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,600 + 125*NUMBEROFPLAYERS);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Icon mainimage = new ImageIcon(getClass().getResource("b.png"));
		//Icon hoverimage = new ImageIcon(getClass().getResource("x.png"));
		//custom = new JButton("Custom", mainimage);
		//custom.setRolloverIcon(hoverimage);
		//add(custom);
		
		handler = new HandlerClass();
		for(int x=0; x<Grill.grillworms.size(); x++){
			GrillWormButtons.get(x).addActionListener(handler);
		}
		//RollDiceButton.addActionListener(handler);
		RollDiceButtonGreen.addActionListener(handler);
		RollDiceButtonGray.addActionListener(handler);
		//FreezeDiceButtonGreen.addActionListener(handler);
		//FreezeDiceButtonGray.addActionListener(handler);
		TakeWormFromGrillButtonGreen.addActionListener(handler);
		TakeWormFromGrillButtonGray.addActionListener(handler);
		TakeWormFromPlayerButtonGreen.addActionListener(handler);
		TakeWormFromPlayerButtonGray.addActionListener(handler);
			
	}
	
	public class ActiveDiceHandlerClass implements ActionListener {
		
		private int setdicenumber;
		
		public ActiveDiceHandlerClass(int dicenumber){
			setdicenumber=dicenumber;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			activedicenumberselected=0;
			if(listenfordicenumber){
				activedicenumberselected=setdicenumber;
				listenfordicenumber=false;
			}
			
			if(activedicenumberselected!=0){
				if(Dice.FrozenDiceList.contains(activedicenumberselected)==false){
					currentplayeractions.performFreezeDice(activedicenumberselected);
					//FreezeDiceButtonAvailable=false;
					currentplayeractions.dicerollavailable=true;
					TakeWormFromPlayerButtonAvailable=true;
					TakeWormFromGrillButtonAvailable=true;
					ActiveDiceButtonsAreGreen=false;
				}
				else if(Dice.FrozenDiceList.contains(activedicenumberselected)){
					JOptionPane.showMessageDialog(null, "You already froze that number, pick another number!");
					listenfordicenumber=true;
				}
			}
			performGamePanelUpdate();		
			//JOptionPane.showMessageDialog(null, "Dice number " + setdicenumber + " was pressed");
		}
		
	}
	
	public class HandlerClass implements ActionListener {
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand()==RollDiceButtonGreen.getText() || event.getActionCommand()==RollDiceButtonGray.getText()){
				if(currentplayeractions.dicerollavailable && TakeWormFromGrillButtonAvailable && TakeWormFromPlayerButtonAvailable){
					currentplayeractions.performRollDice();
					//FreezeDiceButtonAvailable=false;
					performGamePanelUpdate();
					activedicenumberselected=0;
					listenfordicenumber=true;
					performGamePanelUpdate();
					if(Dice.bunk==true){
						JOptionPane.showMessageDialog(null, "You have bunked!");
						currentplayeractions.performPlayerBunk();
					}
					ActiveDiceFaceRemainingCheck=0;
					for(int x=0; x<6; x++){
						if(Dice.ActiveDiceList.contains(x+1)){
							ActiveDiceFaceRemainingCheck++;
						}
					}
					if(ActiveDiceFaceRemainingCheck==1){
						Dice.sumDiceLastChance();
						boolean lastchancewormsteal=false;
						if(NUMBEROFPLAYERS>1){	
							for(int x=0; x<NUMBEROFPLAYERS; x++){
								if(ActivePlayerCount!=x){
									if(PlayerWormsArray.get(x).playerwormsarraylist.get(0)==Dice.lastchancedicesum && (Dice.ActiveDiceList.contains(6) || Dice.ActiveDiceList.contains(6))){
										lastchancewormsteal=true;
									}
								}
							}
						}
					}
					if(ActiveDiceFaceRemainingCheck==1 && Dice.lastchancedicesum<Grill.grillworms.get(0)){
						JOptionPane.showMessageDialog(null, "You cannot roll anymore and you cannot take a worm; you have bunked!");
						currentplayeractions.performPlayerBunk();
					}
					else if(ActiveDiceFaceRemainingCheck==1 && Dice.ActiveDiceList.contains(6)==false && Dice.FrozenDiceList.contains(6)==false){
						JOptionPane.showMessageDialog(null, "You cannot roll anymore and you cannot take a worm; you have bunked!");
						currentplayeractions.performPlayerBunk();
					}
					ActiveDiceButtonsAreGreen=true;
					currentplayeractions.dicerollavailable=false;
					TakeWormFromPlayerButtonAvailable=false;
					TakeWormFromGrillButtonAvailable=false;
					//FreezeDiceButtonAvailable=true;
//					listenfordicenumber=true;
//					ActiveDiceButtonsAreGreen=true;
					performGamePanelUpdate();
					performEndOfGameCheck();
				}
				else if(currentplayeractions.dicerollavailable==true && TakeWormFromGrillButtonAvailable==false && TakeWormFromGrillButtonAvailable==false){
					Dice.restartAllDice();
//					FreezeDiceButtonAvailable=true;
					ActiveDiceButtonsAreGreen=true;
					TakeWormFromGrillButtonAvailable=false;
					TakeWormFromPlayerButtonAvailable=false;
					currentplayeractions.dicerollavailable=false;
					performGamePanelUpdate();
					performEndOfGameCheck();
				}
				else if(currentplayeractions.dicerollavailable==false && TakeWormFromGrillButtonAvailable==false && TakeWormFromGrillButtonAvailable==false){
					JOptionPane.showMessageDialog(null, String.format("You must select a number to freeze from the active dice"));
				}
				else{
					JOptionPane.showMessageDialog(null, String.format("You must freeze dice or take a worm!"));
				}
			}
//			else if(event.getActionCommand()==FreezeDiceButtonGreen.getText() || event.getActionCommand()==FreezeDiceButtonGray.getText()){
//				if(FreezeDiceButtonAvailable){
//					ActiveDiceButtonsAreGreen=true;
//					currentplayeractions.dicerollavailable=false;
//					TakeWormFromPlayerButtonAvailable=false;
//					TakeWormFromGrillButtonAvailable=false;
//					FreezeDiceButtonAvailable=false;
//					performGamePanelUpdate();
//					activedicenumberselected=0;
//					listenfordicenumber=true;
//				}
//				else if(currentplayeractions.dicerollavailable==false && FreezeDiceButtonAvailable==false && TakeWormFromGrillButtonAvailable==false && TakeWormFromGrillButtonAvailable==false){
//					JOptionPane.showMessageDialog(null, String.format("You must select a number to freeze from the active dice"));
//				}
//				else if(currentplayeractions.dicerollavailable && FreezeDiceButtonAvailable==false && TakeWormFromGrillButtonAvailable==false && TakeWormFromPlayerButtonAvailable==false){
//					JOptionPane.showMessageDialog(null, "You must first reroll the dice!");
//				}
//				else{
//					JOptionPane.showMessageDialog(null, String.format("You can only freeze one grouping per roll"));
//				}
//				performGamePanelUpdate();
//				performEndOfGameCheck();
//			}
			else if(event.getActionCommand()==TakeWormFromGrillButtonGreen.getText() || event.getActionCommand()==TakeWormFromGrillButtonGray.getText()){
				if(TakeWormFromGrillButtonAvailable){
					if(Dice.FrozenDiceList.contains(6) && Dice.dicesum>=Grill.grillworms.get(0)){
						if(Grill.CheckIfPrizeWormIsOnGrill(Dice.dicesum)){
							currentplayeractions.performTakeWormFromGrill(Dice.dicesum);
							currentplayeractions.dicerollavailable=true;
							TakeWormFromPlayerButtonAvailable=false;
							TakeWormFromGrillButtonAvailable=false;
							listenfordicenumber=true;
							//FreezeDiceButtonAvailable=false;
							EndPlayerTurn=true;
						}
						else{
							int takewormvalue=Integer.valueOf(JOptionPane.showInputDialog("Which worm would you like to take from the grill?"));
							if(Grill.CheckIfPrizeWormIsOnGrill(takewormvalue) && Dice.dicesum>=takewormvalue){
								currentplayeractions.performTakeWormFromGrill(takewormvalue);
								currentplayeractions.dicerollavailable=true;
								TakeWormFromPlayerButtonAvailable=false;
								TakeWormFromGrillButtonAvailable=false;
//								FreezeDiceButtonAvailable=false;
								listenfordicenumber=true;
								EndPlayerTurn=true;
							}
						}
					}
					else if(Dice.FrozenDiceList.contains(6) && Dice.dicesum<Grill.grillworms.get(0)){
						JOptionPane.showMessageDialog(null, String.format("You do no have enough to take any worms from the grill!"));
					}
					else{
						JOptionPane.showMessageDialog(null, String.format("You do no have any worm dice (W) frozen, so you cannot take from the grill!"));
					}
				}
				else if(currentplayeractions.dicerollavailable==false && TakeWormFromGrillButtonAvailable==false && TakeWormFromGrillButtonAvailable==false){
					JOptionPane.showMessageDialog(null, String.format("You must select a number to freeze from the active dice"));
				}
				else{
					JOptionPane.showMessageDialog(null, "You must roll the dice first!");
				}
				performGamePanelUpdate();
				performEndOfGameCheck();
			}
			else if(event.getActionCommand()==TakeWormFromPlayerButtonGreen.getText() || event.getActionCommand()==TakeWormFromPlayerButtonGray.getText()){
				if(TakeWormFromPlayerButtonAvailable){
					if(Dice.FrozenDiceList.contains(6)){
						int AttackedPlayer=Integer.valueOf(JOptionPane.showInputDialog("Which player would you like to take a worm from? (Enter their Number)"));
						if(1<=AttackedPlayer && AttackedPlayer<=NUMBEROFPLAYERS){
							if(PlayerWormsArray.get(AttackedPlayer-1).playerwormsarraylist.size()>0 && ActivePlayerCount!=AttackedPlayer-1){
								if(Dice.dicesum==PlayerWormsArray.get(AttackedPlayer-1).playerwormsarraylist.get(0)){
									currentplayeractions.performTakeWormFromPlayer(PlayerWormsArray.get(AttackedPlayer-1).playerwormsarraylist.get(0));
									PlayerWormsArray.get(AttackedPlayer-1).playerwormsarraylist.remove(0);
									currentplayeractions.dicerollavailable=true;
									TakeWormFromPlayerButtonAvailable=false;
									TakeWormFromGrillButtonAvailable=false;
									listenfordicenumber=true;
//									FreezeDiceButtonAvailable=false;
									EndPlayerTurn=true;
								}
								else {
									JOptionPane.showMessageDialog(null, String.format("You dice sum does not match the other player's top worm!"));
								}
							}
							else if(ActivePlayerCount==AttackedPlayer-1){
								JOptionPane.showMessageDialog(null, String.format("You cannot steal from yourself!"));
							}
							else{
								JOptionPane.showMessageDialog(null, String.format("That player has no worms to take!"));
							}
						}
						else{
							JOptionPane.showMessageDialog(null, String.format("Please enter a valid number!"));
						}
					}
					else{
						JOptionPane.showMessageDialog(null, String.format("You do no have any worm dice (W) frozen, so you cannot take from the other player!"));
					}
				}
				else if(currentplayeractions.dicerollavailable==false && TakeWormFromGrillButtonAvailable==false && TakeWormFromGrillButtonAvailable==false){
					JOptionPane.showMessageDialog(null, String.format("You must select a number to freeze from the active dice"));
				}
				else{
					JOptionPane.showMessageDialog(null, "You must roll the dice first!");
				}
				performGamePanelUpdate();
				performEndOfGameCheck();
			}
			else{
				JOptionPane.showMessageDialog(null, String.format("%s", event.getActionCommand()));
			}
		}
	}
	
	  private Image getScaledImage(Image srcImg, int w, int h){
		    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = resizedImg.createGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(srcImg, 0, 0, w, h, null);
		    g2.dispose();
		    return resizedImg;
		}
	
	private Icon setDiceImage(int DiceFaceNumber){
		if(DiceFaceNumber==1){
			//getScaledImage(DiceImageOnePip, 100, 100);
			return new ImageIcon(getScaledImage(DiceImageOnePipImage,50,50));
		}
		else if(DiceFaceNumber==2){
			return new ImageIcon(getScaledImage(DiceImageTwoPipImage,50,50));
		}
		else if(DiceFaceNumber==3){
			return new ImageIcon(getScaledImage(DiceImageThreePipImage,50,50));
		}
		else if(DiceFaceNumber==4){
			return new ImageIcon(getScaledImage(DiceImageFourPipImage,50,50));
		}
		else if(DiceFaceNumber==5){
			return new ImageIcon(getScaledImage(DiceImageFivePipImage,50,50));
		}
		else if(DiceFaceNumber==6){
			return new ImageIcon(getScaledImage(DiceImageWormImage,50,50));
		}
		else{
			JOptionPane.showMessageDialog(null, String.format("There was an dice image error!"));
		}
		return null;
	}
	
	private Icon setWormTileImage(int WormTileNumber){
		if(WormTileNumber<25){
			//getScaledImage(DiceImageOnePip, 100, 100);
			return new ImageIcon(getScaledImage(OneWormTileImage,50,50));
		}
		else if(WormTileNumber>=25 && WormTileNumber<=28){
			return new ImageIcon(getScaledImage(TwoWormTileImage,50,50));
		}
		else if(WormTileNumber>=29 && WormTileNumber<=32){
			return new ImageIcon(getScaledImage(ThreeWormTileImage,50,50));
		}
		else if(WormTileNumber>32){
			return new ImageIcon(getScaledImage(FourWormTileImage,50,50));
		}
		else{
			JOptionPane.showMessageDialog(null, String.format("There was an worm tile image error!"));
		}
		return null;
	}
	

	
	private void performGamePanelUpdate(){
		//performGameStatusUpdate();
		performGrillPaneupdate();
		performOptionPaneupdate();
		Dice.sumDice();
		performDiceSumPaneupdate();
		performActiveDicePaneupdate();
		performFrozenDicePaneupdate();
		performPlayerWormsPaneupdate();
		MainPane.validate();
		MainPane.repaint();
		performCurrentPlayerCheck();
	}
	
	private void performCurrentPlayerCheck(){
		if(EndPlayerTurn){
			if(ActivePlayerCount<NUMBEROFPLAYERS-1){
				ActivePlayerCount++;
			}
			else{
				ActivePlayerCount=0;
			}
			currentplayerworms = PlayerWormsArray.get(ActivePlayerCount);
			currentplayeractions = ActivePlayerActionsArray.get(ActivePlayerCount);
			EndPlayerTurn = false;
//			listenfordicenumber=true;
//			ActiveDiceButtonsAreGreen=true;
//			currentplayeractions.dicerollavailable=false;
//			TakeWormFromPlayerButtonAvailable=false;
//			TakeWormFromGrillButtonAvailable=false;
			performGamePanelUpdate();
		}
	}
	
//	private void performGameStatusUpdate(){
//		GameStatusTitlePaneTextField.setText("     " + currentplayerworms.playername + " is up" + "     ");
//		GameStatusOutputPaneTextField.setText("     " + "Game Status Here" + "     ");
//	}
	
	private void performGrillPaneupdate(){
		GrillWormButtons.clear();
		GrillPaneWorms.removeAll();
		for(int x=0; x<Grill.grillworms.size(); x++){
			GrillWormButtons.add(new JButton(Grill.grillworms.get(x).toString(), setWormTileImage(Grill.grillworms.get(x))));
			GrillWormButtons.get(x).setVerticalTextPosition(SwingConstants.TOP);
			GrillWormButtons.get(x).setHorizontalTextPosition(SwingConstants.CENTER);
			GrillWormButtons.get(x).setPreferredSize(new Dimension(60,80));
			GrillWormButtons.get(x).addActionListener(handler);
			GrillPaneWorms.add(GrillWormButtons.get(x));
		}

	}
	
	private void performOptionPaneupdate(){
		OptionPaneOptions.removeAll();
		if(currentplayeractions.dicerollavailable){
			//RollDiceButton.setBackground(null);
			OptionPaneOptions.add(RollDiceButtonGreen);
		}
		else if(currentplayeractions.dicerollavailable==false){
			//RollDiceButton.setBackground(Color.GRAY);
			OptionPaneOptions.add(RollDiceButtonGray);
		}
//		if(FreezeDiceButtonAvailable){
//			//FreezeDiceButton.setBackground(null);
//			OptionPaneOptions.add(FreezeDiceButtonGreen);
//		}
//		else if(FreezeDiceButtonAvailable==false){
//			//FreezeDiceButton.setBackground(Color.GRAY);
//			OptionPaneOptions.add(FreezeDiceButtonGray);
//		}
		if(TakeWormFromGrillButtonAvailable){
			//FreezeDiceButton.setBackground(null);
			OptionPaneOptions.add(TakeWormFromGrillButtonGreen);
		}
		else if(TakeWormFromGrillButtonAvailable==false){
			//FreezeDiceButton.setBackground(Color.GRAY);
			OptionPaneOptions.add(TakeWormFromGrillButtonGray);
		}
		//OptionPaneOptions.add(RollDiceButton);
		//OptionPaneOptions.add(FreezeDiceButton);
		//OptionPaneOptions.add(TakeWormFromGrillButtonGreen);
		if(NUMBEROFPLAYERS>1){
			if(TakeWormFromPlayerButtonAvailable){
				//FreezeDiceButton.setBackground(null);
				OptionPaneOptions.add(TakeWormFromPlayerButtonGreen);
			}
			else if(TakeWormFromPlayerButtonAvailable==false){
				//FreezeDiceButton.setBackground(Color.GRAY);
				OptionPaneOptions.add(TakeWormFromPlayerButtonGray);
			}
			//OptionPaneOptions.add(TakeWormFromPlayerButton);
		}
	}
	
	private void performDiceSumPaneupdate(){
		DiceSumOutputPaneTextField.setText("     " + Dice.dicesum + "     ");
	}
	private void performActiveDicePaneupdate(){
		ActiveDiceButtons.clear();
		ActiveDicePaneDice.removeAll();
		for(int x=0; x<Dice.ActiveDiceList.size(); x++){
			if(ActiveDiceButtonsAreGreen && Dice.FrozenDiceList.contains(Dice.ActiveDiceList.get(x))==false){
				ActiveDiceButtons.add(new JGradientButton(Color.GREEN));
			}
			else{
				ActiveDiceButtons.add(new JButton());
			}
			ActiveDiceButtons.get(x).setIcon(setDiceImage(Dice.ActiveDiceList.get(x)));
			ActiveDiceButtons.get(x).setPreferredSize(new Dimension(60,60));
			ActiveDiceButtons.get(x).addActionListener(new ActiveDiceHandlerClass((Dice.ActiveDiceList.get(x))));
			ActiveDicePaneDice.add(ActiveDiceButtons.get(x));
		}
	}

	private void performFrozenDicePaneupdate(){
		FrozenDiceButtons.clear();
		FrozenDicePaneDice.removeAll();
		for(int x=0; x<Dice.FrozenDiceList.size(); x++){
//			if(Dice.FrozenDiceList.get(x)==6){
//				FrozenDiceButtons.add(new JButton("W"));
//			}
//			else{
				FrozenDiceButtons.add(new JButton(setDiceImage(Dice.FrozenDiceList.get(x))));
//			}
			FrozenDiceButtons.get(x).setPreferredSize(new Dimension(60,60));
			FrozenDicePaneDice.add(FrozenDiceButtons.get(x));
		}	
	}

	private void performPlayerWormsPaneupdate(){
		for(int x=0; x<NUMBEROFPLAYERS; x++){	
			PlayerWormPanelArray.get(x).PlayerWormsPaneWorms.removeAll();
			PlayerWormButtons.clear();
			if(ActivePlayerCount==x){
				PlayerWormPanelArray.get(x).PlayerWormsTextField.setBackground(Color.GREEN);
			}
			else{
				PlayerWormPanelArray.get(x).PlayerWormsTextField.setBackground(null);
			}
			for(int y=0; y<PlayerWormsArray.get(x).playerwormsarraylist.size(); y++){
				//PlayerWormButtons.add(new JButton(PlayerWormsArray.get(x).playerwormsarraylist.get(y).toString()));
				//PlayerWormPanelArray.get(x).PlayerWormsPaneWorms.add(PlayerWormButtons.get(y));
				PlayerWormButtons.add(new JButton(PlayerWormsArray.get(x).playerwormsarraylist.get(y).toString(), setWormTileImage(PlayerWormsArray.get(x).playerwormsarraylist.get(y))));
				PlayerWormButtons.get(y).setVerticalTextPosition(SwingConstants.TOP);
				PlayerWormButtons.get(y).setHorizontalTextPosition(SwingConstants.CENTER);
				PlayerWormButtons.get(y).setPreferredSize(new Dimension(60,80));
				PlayerWormPanelArray.get(x).PlayerWormsPaneWorms.add(PlayerWormButtons.get(y));
			}
		}
	}	
	
	private void performEndOfGameCheck(){
		Grill.checkEndOfGameCondition();
		if(Grill.endofgame){
			WinningPlayerScore=0;
			for(int x=0; x<NUMBEROFPLAYERS; x++){
				ScoreArray.set(x, 0);
				for(int y=0; y<PlayerWormsArray.get(x).playerwormsarraylist.size(); y++){
					int wormvalue=0;
					if(PlayerWormsArray.get(x).playerwormsarraylist.get(y)<25){
						wormvalue=1;
					}
					else if(PlayerWormsArray.get(x).playerwormsarraylist.get(y)>=25 && PlayerWormsArray.get(x).playerwormsarraylist.get(y)<=28){
						wormvalue=2;
					}
					else if(PlayerWormsArray.get(x).playerwormsarraylist.get(y)>=29 && PlayerWormsArray.get(x).playerwormsarraylist.get(y)<=32){
						wormvalue=3;
					}
					else if(PlayerWormsArray.get(x).playerwormsarraylist.get(y)>32){
						wormvalue=4;
					}
					ScoreArray.set(x, ScoreArray.get(x)+wormvalue);
				}
				if(WinningPlayerScore<ScoreArray.get(x)){
					WinningPlayerScore=ScoreArray.get(x);
					WinningPlayerName=PlayerWormsArray.get(x).playername;
				}
			}	
			JOptionPane.showMessageDialog(null, String.format("Game Over!\n The Winner Is %s with %s points", WinningPlayerName, WinningPlayerScore));
			System.out.println("Game Over");
		}
	}
	
}

class createPlayerWormPanels extends JFrame{
	public JPanel PlayerWormsPaneMain;
	public JPanel PlayerWormsPaneTitle;
	public JPanel PlayerWormsPaneWorms;
	
	public JTextField PlayerWormsTextField;
	
	createPlayerWormPanels(String playername, int playernumber){
	PlayerWormsPaneMain = new JPanel();
	PlayerWormsPaneTitle = new JPanel();
	PlayerWormsPaneWorms = new JPanel();
	PlayerWormsPaneMain.setLayout(new BorderLayout());
	PlayerWormsPaneMain.setPreferredSize(new Dimension(800,135));
	PlayerWormsPaneTitle.setLayout(new FlowLayout());
	PlayerWormsPaneTitle.setPreferredSize(new Dimension(200,35));
	PlayerWormsPaneWorms.setLayout(new FlowLayout());
	PlayerWormsPaneWorms.setPreferredSize(new Dimension(800,100));
	PlayerWormsTextField = new JTextField("          " + playernumber + ". " + playername + "'s Worms" + "          ");
	PlayerWormsTextField.setFont(Gui.TitleMessageFont);
	PlayerWormsTextField.setHorizontalAlignment(JTextField.CENTER);
	PlayerWormsTextField.setBackground(null);
	PlayerWormsTextField.setBorder(null);
	PlayerWormsPaneTitle.add(PlayerWormsTextField);
	PlayerWormsPaneMain.add(PlayerWormsPaneTitle, BorderLayout.NORTH);
	PlayerWormsPaneMain.add(PlayerWormsPaneWorms, BorderLayout.CENTER);
	}
	
}