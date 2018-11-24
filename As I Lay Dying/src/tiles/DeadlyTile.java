package tiles;

import level.Level;
import entities.Mob;

public class DeadlyTile extends BasicTile
{
  public DeadlyTile(int id, int x, int y, int tileColor, int levelColor)
  {
    super(id, x, y, tileColor, levelColor);
    this.solid = false;
  }

  public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir)
  {
  }
}