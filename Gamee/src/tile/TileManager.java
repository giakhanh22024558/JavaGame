package tile;

import java.awt.Graphics2D;


import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import Main.GamePanel;

import static utils.Constants.TileConstants.*;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	public Rectangle SolidArea[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		SolidArea = new Rectangle[100][100];
		getTileImage();
		loadMap();
		GetHitBox();
	}
	
	public void getTileImage() {
		
		try {
			
			//0 = grass
			tile[GRASS] = new Tile();
			tile[GRASS].img = ImageIO.read(getClass().getResourceAsStream("/tiles/Grasstile.png"));
			
			//1 = hill tile
			tile[HILL] = new Tile();
			tile[HILL].img = ImageIO.read(getClass().getResourceAsStream("/tiles/Hillstile.png"));
			
			//2 = fence grass
			tile[FENCE] = new Tile();
			tile[FENCE].img = ImageIO.read(getClass().getResourceAsStream("/tiles/FencesGrass.png"));
			
			//3 = dirt
			tile[DIRT] = new Tile();
			tile[DIRT].img = ImageIO.read(getClass().getResourceAsStream("/tiles/TilledDirt.png"));
			
			//4 = water
			tile[WATER] = new Tile();
			tile[WATER].img = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
			
			tile[TREE] = new Tile();
			tile[TREE].img = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree(sand).png"));
			
			tile[BUSH] = new Tile();
			tile[BUSH].img = ImageIO.read(getClass().getResourceAsStream("/tiles/bush(sand).png"));
			
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void GetHitBox() {
		int col = 0, row = 0, x =0, y = 0, index = 0, index2 = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			if(tileNum == HILL || tileNum == FENCE || tileNum == WATER || 
					tileNum == TREE || tileNum == BUSH) 
			{
				SolidArea[row][col] = new Rectangle(x, y, gp.tileSize, gp.tileSize);
			}
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
			
		}
	}
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/mapTile01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0, row = 0; 
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}
		catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
		int col = 0, row = 0, x =0, y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			//g2.drawImage(tile[tileNum].img, x, y, gp.tileSize, gp.tileSize, null);
			if(tileNum == HILL || tileNum == FENCE || tileNum == WATER || tileNum == TREE || tileNum == BUSH) {
				g2.drawRect(SolidArea[row][col].x, SolidArea[row][col].y, gp.tileSize, gp.tileSize);
			}
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
			
		}
	}
}
