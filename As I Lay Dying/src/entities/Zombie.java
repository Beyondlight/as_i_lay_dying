package entities;

import gfx.Colors;
import gfx.Screen;

import java.util.Random;

import level.Level;

  public class Zombie extends Mob {
  	
  	private int scale = 1;
  	private int color = Colors.get(-1, 10, 252, 050);
	protected boolean isSwimming = false;
  	private int tickCount;
  	Random generator = new Random();
  	private int move;

  	public Zombie(Level level, int x, int y) {
  		super(level, "Zombie", x, y, 1);
  	}

  	
      public void tick() {
      	int xa = 0;
      	int ya = 0;
      	move = generator.nextInt(30) + 1;
      	
      	if(move == 4) 
          	ya--;
          	if (!hasCollided(xa, ya)) 
          
  		
  		if(move == 5) 
  			ya++;
  			if (!hasCollided(xa, ya)) 
  		
  		
  		if(move == 6) 
  			xa--;
  			if (!hasCollided(xa, ya))
  		
  		
  		if(move == 7) 
  			xa++;
  			if (!hasCollided(xa, ya)) 
      	
      	
      		
      	
      	
      	if(xa != 0 || ya != 0) {
  			move(xa, ya);
  			isMoving = true;
  		} else {
  			isMoving = false;
  		}
  		if(level.getTile(this.x >> 3, this.y >> 3 ).getId() == 3) {
  			isSwimming = true;
  			
  		}
  		if (isSwimming && level.getTile(this.x >> 3, this.y >> 3 ).getId() != 3) {
  			isSwimming = false;
  		}
  		tickCount++;
  		
  		
  	}
  	
  	public void render(Screen screen) {
  		int xTile = 12;
  		int yTile = 18;
  		int walkingSpeed = 6;
  		 int flipTop = this.numSteps >> walkingSpeed & 0x1;
  	    int flipBottom = this.numSteps >> walkingSpeed & 0x1;

  	    if (this.movingDir == 1) {
  	      xTile += 2;
  	    } else if (this.movingDir > 1) {
  	      xTile += 4 + (this.numSteps >> walkingSpeed & 0x1) * 2;
  	      flipTop = (this.movingDir - 1) % 2;
  	      flipBottom = (this.movingDir - 1) % 2;
  	    }

  	    int modifier = 8 * this.scale;
  	    int xOffset = this.x - modifier / 2;
  	    int yOffset = this.y - modifier / 2 - 4;
  	    if (this.isSwimming) {
  	      int waterColor = 0;
  	      yOffset += 4;
  	      if (this.tickCount % 60 < 15) {
  	        waterColor = Colors.get(-1, -1, 128, -1);
  	      } else if ((15 <= this.tickCount % 60) && (this.tickCount % 60 < 30)) {
  	        yOffset--;
  	        waterColor = Colors.get(-1, 128, 137, -1);
  	      } else if ((30 <= this.tickCount % 60) && (this.tickCount % 60 < 45)) {
  	        waterColor = Colors.get(-1, 128, -1, 137);
  	      } else {
  	        yOffset--;
  	        waterColor = Colors.get(-1, 128, 137, -1);
  	      }
  	      screen.render(xOffset, yOffset + 3, 864, waterColor, 0);
  	      screen.render(xOffset + 8, yOffset + 3, 864, waterColor, 1);
  	    }

  	    screen.render(xOffset + modifier * flipTop, yOffset, xTile + yTile * 32, this.color, flipTop);
  	    screen.render(xOffset + modifier - modifier * flipTop, yOffset, xTile + 1 + yTile * 32, this.color, flipTop);

  	    if (!this.isSwimming) {
  	      screen.render(xOffset + modifier * flipBottom, yOffset + modifier, xTile + 
  	        (yTile + 1) * 32, this.color, flipBottom);
  	      screen.render(xOffset + modifier - modifier * flipBottom, yOffset + modifier, xTile + 1 + (yTile + 1) * 
  	        32, this.color, flipBottom);
  	    }
  	  }
  	
  
  	public boolean hasCollided(int xa, int ya) {
  		int xMin = 0;
  		int xMax = 7;
  		int yMin = 3;
  		int yMax = 7;
  		for (int x = xMin; x < xMax; x++) {
  			if(isSolidTile(xa, ya, x, yMin)) {
  				return true;
  			}
  		}
  		for (int x = xMin; x < xMax; x++) {
  			if(isSolidTile(xa, ya, x, yMax)) {
  				return true;
  			}
  		}
  		for (int y = yMin; y < yMax; y++) {
  			if(isSolidTile(xa, ya, xMin, y)) {
  				return true;
  			}
  		}
  		for (int y = yMin; y < yMax; y++) {
  			if(isSolidTile(xa, ya, xMax, y)) {
  				return true;
  			}
  		}
  		
  		return false;
  	}
  }
