package Version10_inprogress;
public class Main {
	
	public static boolean startgame = false;
	
	public static void main (String[] arg) {
			
		WelcomeScreenGui welcomego = new WelcomeScreenGui();
		
//		while(startgame==false){
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		welcomego.dispose();
		
		Grill MainGrill = new Grill();	
		Dice dice = new Dice();
			
		MainGameGui gamego = new MainGameGui();
			
	}
}