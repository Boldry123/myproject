package fish_catch;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class yu extends JFrame{
	
	
	public static void main(String[] args) {
		JFrame f=new JFrame();
		f.setTitle("捕鱼达人");
		pool p=new pool();
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
