package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, rightPressed, leftPressed, isRunning;
	public boolean keyInputEnable = true;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int	code = e.getKeyCode();
		
		if(keyInputEnable) {
			if(code == KeyEvent.VK_W) {
				isRunning = true;
				upPressed = true;
			}
			else if(code == KeyEvent.VK_S) {
				isRunning = true;
				downPressed = true;
			}
			else if(code == KeyEvent.VK_D) {
				isRunning = true;
				rightPressed = true;
			}
			else if(code == KeyEvent.VK_A) {
				isRunning = true;
				leftPressed = true;
			}
		}
		//System.out.println(keyInputEnable);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			isRunning = false;
			upPressed = false;
		}
		else if(code == KeyEvent.VK_S) {
			isRunning = false;
			downPressed = false;
		}
		else if(code == KeyEvent.VK_D) {
			isRunning = false;
			rightPressed = false;
		}
		else if(code == KeyEvent.VK_A) {
			isRunning = false;
			leftPressed = false;
		}
		//System.out.println(keyInputEnable);
	}
}
