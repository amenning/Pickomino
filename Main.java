package Version8_stable;
public class Main {
	
	public static boolean startgame = false;
	
	public static void main (String[] arg) {
			
		WelcomeScreenGui welcomego = new WelcomeScreenGui();
		
		while(startgame==false){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		welcomego.dispose();
		
		Gui gamego = new Gui();
			
	}
}