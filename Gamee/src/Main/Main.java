package Main;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("BomberCatGame");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); // Causes this window to fit the preferred size and layout of its subcomponent(gamePanel) 
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
		
	}
}
