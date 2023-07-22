package Entity;

import static utils.Constants.PlayerConstants.DOWN;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity {
	
	public int x, y;
	public int speed;
	public int playerDirection = DOWN;
	public BufferedImage img;
	public boolean CollisionOn;
	
	public void importImg(String id) {
		try {
			img = ImageIO.read(getClass().getResourceAsStream(id));
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
}
