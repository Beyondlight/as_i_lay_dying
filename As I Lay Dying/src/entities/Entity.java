package entities;

import gfx.Screen;

import java.util.Random;

import level.Level;

public abstract class Entity
{
  public int x;
  public int y;
  protected final Random random = new Random();
  public int xr = 6;
  public int yr = 6;
  protected Level level;
  public boolean removed;

  public Entity(Level level)
  {
    init(level);
  }

  public void render(Screen screen) {
  }

  public final void init(Level level) {
    this.level = level;
  }

  public void tick()
  {
  }

  public void remove() {
    this.removed = true;
  }
}