package entities;

import gfx.Colors;
import gfx.Screen;
import level.Level;
import main.InputHandler;
import tiles.Tile;

public class Player extends Mob
{
  private InputHandler input;
  private int attackDir;
  private int color = Colors.get(-1,423, 120, 543); //(-1, 111, 130, 543)
  protected boolean isSwimming = false;
  private int tickCount = 0;
  private int health;
  private boolean display = true;
  private int hurtTime = 0;
  private int maxHealth = 10;
  public static boolean triggeredDOOR = false;
  public static boolean triggeredDOOR_LEAVE = false;
  public static boolean triggeredTOWN1_LEAVE = false;
  public static boolean triggeredTOWN1_ENTER = false;


  public Player(Level level, int x, int y, InputHandler input)
  {
    super(level, "Player", x, y, 1);
    this.input = input;
    this.health = 10;
  
}

public void tick()
  {
    if (this.hurtTime % 3 == 0) {
      this.display = (!this.display);
    }
    if (this.hurtTime == 0) {
      this.display = true;
    }
    if (this.hurtTime > 0) {
      this.hurtTime -= 1;
    }
    int xa = 0;
    int ya = 0;

    if (this.input.up.isPressed()) {
      ya--;
    }
    if (this.input.down.isPressed()) {
      ya++;
    }
    if (this.input.left.isPressed()) {
      xa--;
    }
    if (this.input.right.isPressed()) {
      xa++;
    }
    this.input.attack.isPressed();

    if ((xa != 0) || (ya != 0)) {
      move(xa, ya);
      this.isMoving = true;
    } else {
      this.isMoving = false;
    }
    if (this.level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
      this.isSwimming = true;
    }
    if ((this.isSwimming) && (this.level.getTile(this.x >> 3, this.y >> 3).getId() != 3)) {
      this.isSwimming = false;
    }
    this.tickCount += 1;

    if ((this.level.getTile((this.x - 4) / 8, (this.y - 4) / 8).getId() == Tile.CACTUS
      .getId()) || 
      (this.level.getTile((this.x + 4) / 8, (this.y + 4) / 8).getId() == Tile.CACTUS
      .getId()) || 
      (this.level.getTile((this.x - 4) / 8, (this.y + 4) / 8).getId() == Tile.CACTUS
      .getId()) || 
      (this.level.getTile((this.x + 4) / 8, (this.y - 4) / 8).getId() == Tile.CACTUS
      .getId())) {
      doHurt();
    }

    for (int i = 0; i < this.maxHealth; i++)
    {
      if (i < this.health) {
        this.color = Colors.get(-1, 111, 130, 543);
        if (!this.display)
          this.color = Colors.get(-1, -1, 555, 400);
      } else {
        this.color = Colors.get(-1, 111, 130, 543);
        if (!this.display)
          this.color = Colors.get(-1, -1, 555, 222);
      }
      if (this.level.getTile(this.x >> 3,  this.y >> 3).getId() == 14) {
			triggeredDOOR = true;
		}
      if (this.level.getTile(this.x >> 3, this.y >> 3 ).getId() != 14) {
			triggeredDOOR = false;
		}
      if (this.level.getTile(this.x >> 3,  this.y >> 3).getId() == 16) {
			triggeredDOOR_LEAVE = true;
		}
		if (this.level.getTile(this.x >> 3, this.y >> 3 ).getId() != 16) {
			triggeredDOOR_LEAVE = false;
		}
		if (this.level.getTile(this.x >> 3, this.y >> 3).getId() == 19){
			triggeredTOWN1_ENTER = true;
		}
		if (this.level.getTile(this.x >> 3, this.y >> 3).getId() != 19){
				triggeredTOWN1_ENTER = false;
			}
		if (this.level.getTile(this.x >> 3, this.y >> 3).getId() == 18){
			triggeredTOWN1_LEAVE = true;
		}
		if (this.level.getTile(this.x >> 3, this.y >> 3).getId() != 18){
			triggeredTOWN1_LEAVE = false;
    }
    }
		
  }

  public void render(Screen screen)
  {
    int xTile = 0;
    int yTile = 14;
    // (default in case of trying a character) 
    //int xTile = 0;
    //int yTile = 14;
    int walkingSpeed = 4;
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

  public boolean hasCollided(int xa, int ya)
  {
    int xMin = 0;
    int xMax = 7;
    int yMin = 3;
    int yMax = 7;
    for (int x = xMin; x < xMax; x++) {
      if (isSolidTile(xa, ya, x, yMin)) {
        return true;
      }
    }
    for (int x = xMin; x < xMax; x++) {
      if (isSolidTile(xa, ya, x, yMax)) {
        return true;
      }
    }
    for (int y = yMin; y < yMax; y++) {
      if (isSolidTile(xa, ya, xMin, y)) {
        return true;
      }
    }
    for (int y = yMin; y < yMax; y++) {
      if (isSolidTile(xa, ya, xMax, y)) {
        return true;
      }
    }
    return false;
  }

  protected void doHurt()
  {
    if (this.hurtTime == 0) {
      this.hurtTime = 60;
      this.health -= 1;
      if (this.attackDir == 0) this.yKnockback = 6;
      if (this.attackDir == 1) this.yKnockback = -6;
      if (this.attackDir == 2) this.xKnockback = -6;
      if (this.attackDir == 3) this.xKnockback = 6;
      this.hurtTime = 10;
    }
  }
}