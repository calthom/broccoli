package rampUp;

import java.awt.Graphics;

public class Robot {

	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final int GROUND = 382;

	private int centerX = 100;
	private int centerY = GROUND;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;

	private static Background bg1 = gameApp.getBg1();
	private static Background bg2 = gameApp.getBg2();

	private int speedX = 0;
	private int speedY = 1;

	public void update() {

		// Moves Character or Scrolls Background accordingly
		// Speed is negative
		if (speedX < 0) {
			centerX += speedX;
		}

		if(speedX == 0 || speedX < 0){
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}

		// Speed is positive
		// Toon is in left 150 pixels, move right
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		} 

		// Toon stays in same position while background scrolls
		if(speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED);
			bg2.setSpeedX(-MOVESPEED);
		}


		// Updates Y Position
		// Gravity always present, but toon cannot go below the ground
		centerY += speedY;
		if (centerY + speedY >= GROUND) {
			centerY = GROUND;
		}

		// Handles Jumping
		//If toon is jumping, +1 to speed each time to slow toon's upwards movement.
		//Initial jump speed of -15 will eventually be taken down to 0 and then to +15
		//and toon will reach the ground again at which point speedY is set back
		//to zero and the jump is done.
		if (jumped == true) {
			speedY += 1;

			if (centerY + speedY >= GROUND) {
				centerY = GROUND;
				speedY = 0;
				jumped = false;
			}

		}


		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
	}

	public void moveRight() {
		if(ducked == false){
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if(ducked == false){
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stop() {

		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked(){
		return ducked;
	}

	public void setDucked(boolean ducked){
		this.ducked = ducked;
	}

	public boolean isMovingRight(){
		return movingRight;
	}

	public void setMovingRight(boolean movingRight){
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft(){
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft){
		this.movingLeft = movingLeft;
	}

}