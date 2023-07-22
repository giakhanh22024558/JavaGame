package Main;

import Entity.Entity;
import static utils.Constants.PlayerConstants.*;

import java.awt.Rectangle;

public class CollisionChecker {
	
	GamePanel gp;
	CollisionChecker(GamePanel gp){
		this.gp = gp;
	}
	
	public boolean CheckTile(Rectangle object1, Rectangle object2) {
		
		int left_a = object1.x;
	    int right_a = object1.x + object1.width;
	    int top_a = object1.y;
	    int bottom_a = object1.y + object1.height;

	    int left_b = object2.x;
	    int right_b = object2.x + object2.width;
	    int top_b = object2.y;
	    int bottom_b = object2.y + object2.height;

	    if(right_a >= left_b && left_a <= right_b && bottom_a >= top_b && top_a <= bottom_b)
	    	return true;
		return false;
	}
	
}
