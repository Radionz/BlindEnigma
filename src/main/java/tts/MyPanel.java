package main.java.tts;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class MyPanel extends JPanel{
	private String imgs;
	private boolean tailleOrigin;
	
	public MyPanel(String img, boolean tailleOrigin){
		this.imgs = img;
		this.tailleOrigin = tailleOrigin;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		BufferedImage img = null;
		try {
            img = ImageIO.read(new File(imgs));
        } catch (IOException e) {
            e.printStackTrace();
        }
		if(tailleOrigin)
			g.drawImage(img, (int)(this.getHeight()/2), 0, this.getHeight(), this.getHeight(), null);
		else
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
}