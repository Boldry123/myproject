package fish_catch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class pool extends JPanel implements Runnable{
	BufferedImage bi;
	BufferedImage [][]fishState=new BufferedImage[13][10];
	int w,h,c;
	int speed;
	int index;
	int score;
	int xs[]=new int[13];
	int ys[]=new int[13];
	Random rand=new Random();
	Net1 net=new Net1();
	boolean result=false;
	public pool(){
		try {
			bi=ImageIO.read(new File("src/fish/bg.gif"));
			w=bi.getWidth();
			h=bi.getHeight();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 10; j++) {
					fishState[i][j]=ImageIO.read(new File("src/fish/fish0"+(i+1)+"_0"+j+".png"));
				}
				xs[i]=rand.nextInt(w-fishState[i][0].getWidth());
				ys[i]=rand.nextInt(h-fishState[i][0].getHeight());
				speed = rand.nextInt(8) + 2;
			}
			
			fishState[9][0]=ImageIO.read(new File("src/fish/fish13_00.png"));
			
			xs[9]=rand.nextInt(w-fishState[9][0].getWidth());
			ys[9]=rand.nextInt(h-fishState[9][0].getHeight());
			fishState[10][0]=ImageIO.read(new File("src/fish/fish14_00.png"));
			xs[10]=rand.nextInt(w-fishState[10][0].getWidth());
			ys[10]=rand.nextInt(h-fishState[10][0].getHeight());
			for (int i = 9; i < 11; i++) {
				for (int j = 0; j < 10; j++) {
					fishState[i][j]=ImageIO.read(new File("src/fish/fish"+(i+4)+"_0"+j+".png"));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void paint(final Graphics g){
		super.paint(g);
		g.drawImage(bi, 0, 0, this);
		for (int i = 0; i < 11; i++) {
				g.drawImage(fishState[i][0],xs[i],ys[i] ,null);	
		}
		g.drawImage(net.netImage,net.x-net.width/2,net.y-net.height/2,null);
		Font font=new Font("宋体",Font.BOLD,24);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("分数："+score, 50, 50);
		this.addMouseMotionListener(
				new MouseMotionListener() {
					public void mouseMoved(MouseEvent e) {
						int x = e.getX();
						int y = e.getY();
						net.moveTo(x, y);	
					}
					public void mouseDragged(MouseEvent e) {}
				});
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				net.moveTo(-net.width, -net.height);
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < 11; i++) {
					int dx=Math.abs(e.getX()-xs[i]-fishState[i][0].getWidth()/2);
					int dy=Math.abs(e.getY()-ys[i]-fishState[i][0].getHeight()/2);
					int distance=(int)Math.sqrt(dx*dx+dy*dy);
					result=distance<net.width/2?true:false;
					if(result){
						xs[i]=-fishState[i][0].getWidth();
						ys[i]=-fishState[i][0].getHeight();
						score=score+fishState[i][0].getWidth()/10;
					}
				}
			}
		});	
	}
	public void run(){
		while(true){
				for (int i = 0; i < 11; i++) {
					fishState[i][0]=fishState[i][index++%10];
					xs[i]-=speed;
					if(xs[i]<-fishState[i][0].getWidth()){
						xs[i]=bi.getWidth()+1;
						ys[i]=rand.nextInt(bi.getHeight()-fishState[i][0].getHeight());
						speed=rand.nextInt(8)+2;
					}
				}
				repaint();
				try{
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		}
	}
}
