package Main;

import java.awt.Dimension;


import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import tile.TileManager;

import static utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel implements Runnable {
	
	//SCREENSETTINGS
	final int originalTileSize = 16; //16x16tile
	final int scale = 3; //the image will appear larger on the screen
	
	public final int tileSize = originalTileSize*scale; //48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12; 
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	KeyHandler keyH = new KeyHandler();
	Thread gamethread;
	Player player = new Player(this, keyH);
	TileManager tileM = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public int distance;
	
	//player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	//frames per second
	int FPS = 60;
	
	public boolean HitFromAbove, HitFromBelow, HitFromRight, HitFromLeft;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true); // this will improve rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true); // GamePanel will be "focused" to receive key input
		
	}
	
	public void startGameThread() {
		
		gamethread = new Thread(this);
		gamethread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		double drawInterval = 1000/FPS;
		double nextDrawTime = System.currentTimeMillis() + drawInterval;
		
		while(gamethread != null) {
			
			//Update information (character position,etc)
			update();
			CheckCollision();
			//repaint the image on the screen
			repaint();
			
			try 
			{
				double remainingTime = nextDrawTime - System.currentTimeMillis();
				
				if(remainingTime < 0) 
				{
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();	
			}
		}
	}
	
	public void update() {
		
		//System.out.println(player.CollisionOn);
		//System.out.println(Col2 + " " + Row2);
		player.update();
	}
	
	public void CheckCollision() {
		
		int Col = player.directionBox.y / this.tileSize;
		int Row = player.directionBox.x / this.tileSize;
		int Col2 = (player.directionBox.y + player.directionBox.height) / this.tileSize;
		int Row2 = (player.directionBox.x + player.directionBox.width) / this.tileSize;
		
		if(tileM.SolidArea[Col][Row]!=null && tileM.SolidArea[Col2][Row2] == null) {
			player.CollisionOn = cChecker.CheckTile(player.solidArea, tileM.SolidArea[Col][Row]);
			if(player.CollisionOn) {
				if(player.solidArea.y + player.solidArea.height < tileM.SolidArea[Col][Row].y)
					HitFromAbove = true;
			}
		}
		if(tileM.SolidArea[Col][Row]==null && tileM.SolidArea[Col2][Row2] != null) {
			player.CollisionOn = cChecker.CheckTile(player.solidArea, tileM.SolidArea[Col2][Row2]);
		}
		if(tileM.SolidArea[Col][Row]!=null && tileM.SolidArea[Col2][Row2] != null) {
			player.CollisionOn = cChecker.CheckTile(player.solidArea, tileM.SolidArea[Col][Row]);
		}
		if(tileM.SolidArea[Col][Row]==null && tileM.SolidArea[Col2][Row2] == null) {
			player.CollisionOn = false;
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; // Allow more sophisticated setting
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
		
	}
	
}
