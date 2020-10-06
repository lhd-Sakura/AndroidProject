package iot.qwh.txt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

public class MyGameFrame extends Frame{

	Image planeImg=GameUtil.getImage("image/plane.png");
	Image background=GameUtil.getImage("image/background.png");
	
	Plane plane=new Plane(planeImg,250,250);
//	Shell shell=new Shell();
	
	Shell[] shells=new Shell[50];
	
	Explode bao;
	
	Date startTime=new Date();
	Date endTime;
	int period;
	
	@Override
	public void paint(Graphics g) {   //自动被调用
	 
//		super.paint(g);
		
		g.drawImage(background, 0, 0, null);
		plane.drawSelf(g);
		for(int i=0;i<shells.length;i++) {
			shells[i].draw(g);
			
         boolean peng=shells[i].getRect().intersects(plane.getRect());
			
			if(peng) {
				plane.live=false;
				if(bao== null) {
									bao=new Explode(plane.x, plane.y);
									endTime=new Date();
									period=(int)((endTime.getTime()-startTime.getTime())/1000);
				}
				bao.draw(g);
				
			}
			if(!plane.live) {
				
			g.setColor(Color.RED);
			Font f=new Font("宋体", Font.BOLD, 30);
			g.setFont(f);
			g.drawString("时间："+period+"秒",(int)plane.x,(int)plane.y);
			}
		}
//		shell.draw(g);
		
		
	}
	
	class PaintThread extends Thread{
		
		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//super.run();
		}
	}
	
	class KeyMonitor extends KeyAdapter {
		
		@Override 
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
		
		
	}
	
	
	public void launchFrame() {
		this.setTitle("老干部练习作品");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDH, Constant.GAME_HEIGHT);
		this.setLocation(300,300);
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new PaintThread().start();
		addKeyListener(new KeyMonitor());
		
		for(int i=0;i<shells.length;i++) {
			shells[i]=new Shell();
		
		}
	}
	
	
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
	
	
	public static void main(String[] args) {
		MyGameFrame f=new MyGameFrame();
		f.launchFrame();
	}
}
