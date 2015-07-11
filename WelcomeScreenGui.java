package Version6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WelcomeScreenGui extends JFrame {
		
	private ImageIcon CoverImage = new ImageIcon(getClass().getResource("PickominoBoxCoverImage.png"));
	private Image CoverImageImage = CoverImage.getImage();
	
	private JPanel WelcomePaneMain;
	private JPanel WelcomeMessagePane;
	private JPanel CoverImagePane;
	private JPanel StartButtonPane;
	
	private JTextField WelcomeMessageTextField;
	
	//private JButton StartButton;
	private JButton CoverImageButton;
	private JGradientButton StartButton;
	
	public WelcomeScreenGui(){
		super("Pickomino");
		
		WelcomePaneMain = new JPanel(new BorderLayout());
        WelcomePaneMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
		WelcomeMessagePane = new JPanel(new BorderLayout());		
		WelcomeMessageTextField = new JTextField("Welcome to Pickomino!  Please Press Start to Begin!");
		WelcomeMessageTextField.setEditable(false);
		Font WelcomeMessageFont = new Font("SansSerif", Font.BOLD, 20);
		WelcomeMessageTextField.setFont(WelcomeMessageFont);
		WelcomeMessageTextField.setHorizontalAlignment(JTextField.CENTER);
		WelcomeMessagePane.add(WelcomeMessageTextField, BorderLayout.CENTER);
		
		CoverImagePane = new JPanel(new BorderLayout());
		CoverImageButton = new JButton(new ImageIcon(CoverImageImage));
		CoverImagePane.add(CoverImageButton, BorderLayout.CENTER);
		
		StartButtonPane = new JPanel(new BorderLayout());
		
		//StartButton = JGradientButton.newInstance("Start Button", Color.GREEN);
		StartButton = new JGradientButton("Start Button", Color.GREEN);
		StartButtonPane.add(StartButton, BorderLayout.CENTER);
		
		WelcomePaneMain.add(WelcomeMessagePane, BorderLayout.NORTH);
		WelcomePaneMain.add(CoverImagePane, BorderLayout.CENTER);
		WelcomePaneMain.add(StartButtonPane, BorderLayout.SOUTH);
		
		add(WelcomePaneMain);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,650);
		setLocationRelativeTo(null);
		setVisible(true);
		
		HandlerClass handler = new HandlerClass();
		StartButton.addActionListener(handler);
		
	}
	
	private class HandlerClass implements ActionListener {
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand()==StartButton.getText()){
				//JOptionPane.showMessageDialog(null, "Start Button Pushed");
				Main.startgame=true;
				dispose();
			}
		}
	}	
		
}