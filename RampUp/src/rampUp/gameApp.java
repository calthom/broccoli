package rampUp;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.net.URL;

public class gameApp extends Applet implements Runnable, KeyListener {
	
	private Robot robot;
	private Image image, currentSprite, character, characterDucked, characterJumped, guyJump, guyStand, guyDuck, background;
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;
	
	@Override
	public void init() {
		setSize(800,480);
		setBackground(Color.BLACK);
		//Gives applet focus when game starts so that input goes directly into it
		setFocusable(true);
		addKeyListener(this);
		//Assigns applet window to frame variable
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Broccoli");
		
		try{
			base = getDocumentBase();
		} catch (Exception e){
			//TODO: handle exception
		}
		
		//Image Setups
		character = getImage(base, "data/character.png");
		guyStand = getImage(base, "data/guyStand.png");
		characterDucked = getImage(base, "data/down.png");
		guyDuck = getImage(base, "data/guyDuck.png");
		characterJumped = getImage(base, "data/jump.png");
		guyJump = getImage(base, "data/guyJump.png");
		currentSprite = character;
		background = getImage(base, "data/background.png");

	}

	@Override
	public void start() {
		
		bg1 = new Background(0,0);
		bg2 = new Background(2160,0);
		
		robot = new Robot();
		
		//Because gameApp implements Runnable, using gameApp object to create a thread
		//and then starting that thread causes object's run method to be called in 
		//separately executed thread
		Thread thread = new Thread(this);
		thread.start();
		
	}

	@Override
	public void stop() {
		// TODO
	}

	@Override
	public void destroy() {
		// TODO
	}

	@Override
	public void run() {
		while(true){

			robot.update();
			if(robot.isJumped()){
				currentSprite = guyJump;
			}else if(robot.isJumped() == false && robot.isDucked() == false){
				currentSprite = guyStand;
			}
			
			bg1.update();
			bg2.update();
			
			//Calls paint method
			repaint();

			//Sleeps for 17ms
			try{
				Thread.sleep(17);
			}catch (InterruptedException e){
				e.printStackTrace();
			}	
		}
	}
	
	@Override
	public void update(Graphics g) {
		if(image==null){
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		
		g.drawImage(image,0,0,this);
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		g.drawImage(background, bg1.getBigX(), bg1.getBigY(), this);
		g.drawImage(background, bg2.getBigX(), bg2.getBigY(), this);
		
		g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = guyDuck;
            if (robot.isJumped() == false){
                robot.setDucked(true);
                robot.setSpeedX(0);
            }
			break;

		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;

		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			robot.setMovingRight(true);
			break;

		case KeyEvent.VK_SPACE:
			robot.jump();
			break;
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = guyStand;
            robot.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			robot.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			robot.stopRight();
			break;

		case KeyEvent.VK_SPACE:
			break;
		
		}	
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

}
