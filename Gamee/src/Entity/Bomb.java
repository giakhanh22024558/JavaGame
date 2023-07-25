package Entity;

import java.awt.Graphics2D;
import Main.KeyHandler;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Main.GamePanel;

public class Bomb extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	BufferedImage[] Bomb;
	Rectangle solidArea;
	Player p;
	private int aniTick, aniIndex, aniIndex2 = 4, aniSpeed = 10;
	double ExploseTick = 0, ExploseTime = 250;
	public boolean bombExplose = false;
	public boolean ExploseEnd = false;
	int second = 3;
	
	public Bomb(GamePanel gp, KeyHandler keyH, Player p) {
		this.gp = gp;
		this.keyH = keyH;
		this.p = p;
		solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
		importImg("/player/BombOn(52x56).png");
		loadAnimation();
	}
	
	public void loadAnimation() {
		Bomb = new BufferedImage[9];
		for(int i = 0; i < 9; i++) {
			Bomb[i] = img.getSubimage(i*52, 0, 52, 56);
		}
	}
	
	public void setBombState() {
		ExploseTick++;
		if(ExploseTick >= ExploseTime && !bombExplose) {
			bombExplose = true;
		}
		//System.out.println(ExploseTick);
	}
	
	public void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			if(!bombExplose) {
				aniIndex++;
				if(aniIndex >= 4)
					aniIndex = 0;
			}
			else if(bombExplose) {
				aniIndex2++;
				if(aniIndex2 >= 9) {
					bombExplose = false;
					keyH.setBomb = false;
					ExploseEnd = true;
					aniIndex2 = 4;
					aniIndex = 0;
					ExploseTick = 0;
				}
			}
		}
		System.out.println(ExploseEnd);
	}
	
	public void draw(Graphics2D g2) {
		
		setBombState();
		updateAnimationTick();
		if(!ExploseEnd) {
			if(!bombExplose)
				g2.drawImage(Bomb[aniIndex], x, y, gp.tileSize*3, gp.tileSize*3, null);
			else if(bombExplose) 
				g2.drawImage(Bomb[aniIndex2], x, y, gp.tileSize*3, gp.tileSize*3, null);
		}
	}
}
