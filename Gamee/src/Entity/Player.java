package Entity;

import Main.KeyHandler;



import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Main.GamePanel;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	private BufferedImage[][] playerIdle;
	private BufferedImage[][] playerRun;
	public Rectangle solidArea, directionBox;
	private int aniTick, aniIndex, aniSpeed = 25, count = 0;
	public boolean onHitbox = false;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		solidArea = new Rectangle(0, 0, 25, 32);
		directionBox = new Rectangle(0, 0, 25, 32);
		setDefaultValues();
		importImg("/player/playerModel1.png");
		loadAnimation();
	}
	
	public void setDefaultValues() {
		x = 0;
		y = 0;
		speed = 3;
	}
	
	public void loadAnimation() {
		playerIdle = new BufferedImage[4][4];
		playerRun = new BufferedImage[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 2; j++)
				playerIdle[i][j] = img.getSubimage(j*48, i*48, 48, 48);
			for(int x = 2; x < 4; x++) {
				playerRun[i][count] = img.getSubimage(x*48, i*48, 48, 48);
				count++;
				if(count == 2)
					count = 0;
			}
		}
	}
	
	public void setDirectionBox() {
		switch(this.playerDirection) {
		case RIGHT:
			directionBox.x = solidArea.x + solidArea.width;
			directionBox.y = solidArea.y;
			break;
		case DOWN:
			directionBox.x = solidArea.x;
			directionBox.y = solidArea.y + solidArea.height;
			break;
		case LEFT:
			directionBox.x = solidArea.x - directionBox.width;
			directionBox.y = solidArea.y;
			break;
		case UP:
			directionBox.x = solidArea.x;
			directionBox.y = solidArea.y - directionBox.height;
		}
	}
		
	public void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= 2)
				aniIndex = 0;
		}
	}
	
	public void update() {
		
		this.solidArea.x = x + 60;
		this.solidArea.y = y + 64;
			
		if(!onHitbox) {
			if(keyH.upPressed) {
				this.playerDirection = UP;
			}
			if(keyH.downPressed) {
				this.playerDirection = DOWN;
			}
			if(keyH.rightPressed) {
				this.playerDirection = RIGHT;
			}
			if(keyH.leftPressed) {
				this.playerDirection = LEFT;
			}
		}
		
		if(!CollisionOn && ((keyH.upPressed) || (keyH.downPressed) || (keyH.rightPressed) || (keyH.leftPressed))) {
			switch (this.playerDirection) {
			case UP:
				y -= speed;
				break;
			case DOWN:
				y += speed;
				break;
			case RIGHT:
				x += speed;
				break;
			case LEFT:
				x -= speed;
				break;
			}
		}
		if(CollisionOn) {
			if(playerDirection == UP) {
				solidArea.y = y + 64 + 4;
			}
			if(playerDirection == DOWN) {
				solidArea.y = y + 64 - 3;
			}
			if(playerDirection == RIGHT) {
				solidArea.x = x + 60 - 3;
			}
			if(playerDirection == LEFT) {
				solidArea.x = x + 60 + 3;
			}
		}
		System.out.println(solidArea.x + " " + solidArea.y);
		setDirectionBox();
	}
	
	public void draw(Graphics2D g2) {
		
		updateAnimationTick();
		if(!keyH.isRunning)
			g2.drawImage(playerIdle[playerDirection][aniIndex], x, y, gp.tileSize*3, gp.tileSize*3, null);	
		else g2.drawImage(playerRun[playerDirection][aniIndex], x, y, gp.tileSize*3, gp.tileSize*3, null);
		g2.drawRect(solidArea.x, solidArea.y, 25, 32); // hitbox standard coor = x + 60, y + 60, 25, 32
		g2.drawRect(directionBox.x, directionBox.y, 25, 32);
		g2.drawRect(this.x, this.y, gp.tileSize*3, gp.tileSize*3);
	}
	
}
