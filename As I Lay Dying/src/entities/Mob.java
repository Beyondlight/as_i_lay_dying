package entities;

import level.Level;
import tiles.Tile;

public abstract class Mob extends Entity
{
  protected int walkDist = 0;
  protected int dir = 0;
  public int hurtTime = 0;
  public int maxHealth = 10;
  public int health = this.maxHealth;
  protected String name;
  protected int speed;
  protected int numSteps = 0;
  protected boolean isMoving;
  protected int movingDir = 1;
  protected int scale = 1;
  public int tickTime = 0;
  protected int xKnockback;
  protected int yKnockback;

  public Mob(Level level, String name, int x, int y, int speed)
  {
    super(level);
    this.name = name;
    this.x = x;
    this.y = y;
    this.xr = 4;
    this.yr = 3;
    this.speed = speed;
  }

  public void move(int xa, int ya) {
    if ((xa != 0) && (ya != 0)) {
      move(xa, 0);
      move(0, ya);
      this.numSteps -= 1;
      return;
    }

    this.numSteps += 1;
    if (!hasCollided(xa, ya)) {
      if (ya < 0)
        this.movingDir = 0;
      if (ya > 0)
        this.movingDir = 1;
      if (xa < 0)
        this.movingDir = 2;
      if (xa > 0)
        this.movingDir = 3;
      this.x += xa * this.speed;
      this.y += ya * this.speed;
    }
  }

  public abstract boolean hasCollided(int paramInt1, int paramInt2);

  protected boolean isSolidTile(int xa, int ya, int x, int y)
  {
    if (this.level == null) return false;
    Tile lastTile = this.level.getTile(this.x + x >> 3, this.y + y >> 3);
    Tile newTile = this.level.getTile(this.x + x + xa >> 3, this.y + y + ya >> 3);
    if ((!lastTile.equals(newTile)) && (newTile.isSolid())) {
      return true;
    }
    return false;
  }

  public boolean findStartPos(Level level) {
    int x = this.random.nextInt(level.width);
    int y = this.random.nextInt(level.height);
    int xx = x * 16 + 8;
    int yy = y * 16 + 8;

    if (level.player != null) {
      int xd = level.player.x - xx;
      int yd = level.player.y - yy;
      if (xd * xd + yd * yd < 6400) return false;
    }
    return false;
  }
  public String getName() {
    return this.name;
  }
  protected void die() {
    remove();
  }
  public void doHurt(Tile tile, int x, int y, int damage) {
    int attackDir = this.dir ^ 0x1;
    doHurt(damage, attackDir);
  }

  private void doHurt(int damage, int attackDir)
  {
  }
}