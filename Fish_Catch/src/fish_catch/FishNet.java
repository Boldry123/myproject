package fish_catch;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;



public class FishNet {
	int x,y;
	int width,height;
	BufferedImage netImage;
	boolean show;
	public FishNet(){
		try {
			netImage=ImageIO.read(new File("src/fish/net09.png"));
			width=netImage.getWidth();
			height=netImage.getHeight();
			x=0;y=0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void moveTo(int x,int y){
		this.x=x;
		this.y=y;
	}
	
}
