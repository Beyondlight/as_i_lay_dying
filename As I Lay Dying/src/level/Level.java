package level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import tiles.Tile;
import entities.Entity;
import entities.Player;
import gfx.Screen;

public class Level
{
  //private Random random = new Random();
  public Player player;
  private byte[] tiles;
  public int width;
  public int height;
  public List<Entity> entities = new ArrayList();
  private String imagePath;
  private BufferedImage image;

  public Level(String imagePath)
  {
    if (imagePath != null) {
      this.imagePath = imagePath;
      loadLevelFromFile();
    } else {
      this.width = 64;
      this.height = 64;
      this.tiles = new byte[this.width * this.height];
      generateLevel();
    }
  }

  private void loadLevelFromFile()
  {
    try
    {
      this.image = ImageIO.read(Level.class.getResource(this.imagePath));
      this.width = this.image.getWidth();
      this.height = this.image.getHeight();
      this.tiles = new byte[this.width * this.height];
      loadTiles();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadTiles() {
    int[] tileColors = this.image.getRGB(0, 0, this.width, this.height, null, 0, this.width);
    for (int y = 0; y < this.height; y++)
      for (int x = 0; x < this.width; x++)
        for (Tile t : Tile.tiles)
          if ((t != null) && (t.getLevelColor() == tileColors[(x + y * this.width)])) {
            this.tiles[(x + y * this.width)] = t.getId();
            break;
          }
  }

  private void saveLevelToFile()
  {
    try
    {
      ImageIO.write(this.image, "png", new File(Level.class.getResource(this.imagePath).getFile()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void alterTile(int x, int y, Tile newTile) {
    this.tiles[(x + y * this.width)] = newTile.getId();
    this.image.setRGB(x, y, newTile.getLevelColor());
  }

  public void generateLevel() {
    for (int y = 0; y < this.height; y++)
      for (int x = 0; x < this.width; x++)
        if (x * y % 10 < 9) {
          this.tiles[(x + y * this.width)] = Tile.GRASS.getId();
        } else {
          this.tiles[(x + y * this.width)] = Tile.STONE.getId();
          this.tiles[(x + y * this.width)] = Tile.DIRT.getId();
          this.tiles[(x + y * this.width)] = Tile.CACTUS.getId();
        }
  }

  public void tick()
  {
    for (Entity e : this.entities) {
      e.tick();
    }

    for (Tile t : Tile.tiles) {
      if (t == null) {
        break;
      }
      t.tick();
    }
  }

  public void renderTiles(Screen screen, int xOffset, int yOffset) {
    if (xOffset < 0)
      xOffset = 0;
    if (xOffset > (this.width << 3) - screen.width)
      xOffset = (this.width << 3) - screen.width;
    if (yOffset < 0)
      yOffset = 0;
    if (yOffset > (this.height << 3) - screen.height) {
      yOffset = (this.height << 3) - screen.height;
    }
    screen.setOffset(xOffset, yOffset);

    for (int y = yOffset >> 3; y < (yOffset + screen.height >> 3) + 1; y++)
      for (int x = xOffset >> 3; x < (xOffset + screen.width >> 3) + 1; x++)
        getTile(x, y).render(screen, this, x << 3, y << 3);
  }

  public void renderEntities(Screen screen)
  {
    for (Entity e : this.entities)
      e.render(screen);
  }

  public Tile getTile(int x, int y)
  {
    if ((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height))
      return Tile.VOID;
    return Tile.tiles[this.tiles[(x + y * this.width)]];
  }

  public void addEntity(Entity entity)
  {
    this.entities.add(entity);
  }
  public void trySpawn(int count) {
    for (int i = 0; i < count; i++)
    {
    }
      int minLevel = 1;
      int i = 1;
    }
  
}