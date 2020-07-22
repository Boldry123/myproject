package fish_catch;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CatchFishes extends JFrame{
	
	
	public static void main(String[] args) {
		JFrame f=new JFrame();
		f.setTitle("捕鱼达人");
		FishPool p=new FishPool();
		f.add(p);
		f.setSize(p.w,p.h);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setIconImage(new ImageIcon("src/fish/fish05_00.png").getImage());
		f.setVisible(true);
		Thread t =new Thread(p);
		t.start();
	}
}
