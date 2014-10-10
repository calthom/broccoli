package rampUp;

public class Background {
	
	private int bigX, bigY, speedX;
	
	public Background(int x, int y){
		bigX = x;
		bigY = y;
		speedX = 0;
	}
	
	public void update(){
		
		bigX += speedX;
		
		if(bigX <= -2160){
			bigX += 4320;
		}
		
	}

	public int getBigX() {
		return bigX;
	}

	public int getBigY() {
		return bigY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setBigX(int bigX) {
		this.bigX = bigX;
	}

	public void setBigY(int bigY) {
		this.bigY = bigY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

}
