import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreenGui extends JFrame {
		
	private ImageIcon CoverImage = new ImageIcon(getClass().getResource("PickominoBoxCoverImage.png"));
	private Image CoverImageImage = CoverImage.getImage();
	
	private JPanel WelcomePaneMain;
	private JPanel WelcomeMessagePane;
	private JPanel CoverImagePane;
	private JPanel StartButtonPane;
	
	private JTextField WelcomeMessageTextField;
	private Font WelcomeMessageFont = new Font("SansSerif", Font.BOLD, 20);
	
	private JButton CoverImageButton;
	private JGradientButton StartButton;
	
	public WelcomeScreenGui(){
		super("Pickomino: Version 10");
		
		WelcomePaneMain = new JPanel(new BorderLayout());
        WelcomePaneMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
		WelcomeMessagePane = new JPanel(new BorderLayout());		
		WelcomeMessageTextField = new JTextField("Welcome to Pickomino!  Version 10");
		WelcomeMessageTextField.setEditable(false);
		WelcomeMessageTextField.setFont(WelcomeMessageFont);
		WelcomeMessageTextField.setHorizontalAlignment(JTextField.CENTER);
		WelcomeMessagePane.add(WelcomeMessageTextField, BorderLayout.CENTER);
		
		CoverImagePane = new JPanel(new BorderLayout());
		CoverImageButton = new JButton(new ImageIcon(CoverImageImage));
		CoverImagePane.add(CoverImageButton, BorderLayout.CENTER);
		
		StartButtonPane = new JPanel(new BorderLayout());
		StartButton = new JGradientButton("Start Button", Color.GREEN);
		StartButtonPane.add(StartButton, BorderLayout.CENTER);
		
		WelcomePaneMain.add(WelcomeMessagePane, BorderLayout.SOUTH);
		WelcomePaneMain.add(CoverImagePane, BorderLayout.CENTER);
		//WelcomePaneMain.add(StartButtonPane, BorderLayout.SOUTH);
		
		add(WelcomePaneMain);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setSize(525,525);
		setLocationRelativeTo(null);
		setVisible(true);
		
		StartButtonHandlerClass startbuttonhandler = new StartButtonHandlerClass();
		StartButton.addActionListener(startbuttonhandler);
		
	}
	
	private class StartButtonHandlerClass implements ActionListener {
		public void actionPerformed(ActionEvent event){
			if(event.getActionCommand()==StartButton.getText()){
				Main.startgame=true;
			}
		}
	}	
		
}