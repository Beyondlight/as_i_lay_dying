package main;

import entities.Player;
import entities.Slime;
import entities.Zombie;
import gfx.Colors;
import gfx.Screen;
import gfx.SpriteSheet;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import level.Level;

public class Game extends Canvas
  implements Runnable
{
  private static final long serialVersionUID = 1L;
  public static final int WIDTH = 180; 
  public static final int HEIGHT = 140;
  public static final int SCALE = 3;
  public static final String NAME = "As I Lay Dying";
  public boolean running = false;
  public int tickCount = 0;

  private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

  private int[] pixels = ((DataBufferInt)this.image.getRaster().getDataBuffer())
    .getData();
  private int[] colors = new int[6*6*6];
  private Screen screen;
  public static InputHandler input;
  public static Level level;
  public static Player player;
  public static Zombie zombie;
  public static Slime slime;
  private Random random = new Random();
  public Menu menu;
  private JFrame frame;
  public static Events events;
  Game game;

  public void setMenu(Menu menu)
  {
    this.menu = menu;
   // if (menu != null) menu.init(this, this.input);
  }

  public Game()
  {
	  setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));

    this.frame = new JFrame("As I Lay Dying");

    this.frame.setDefaultCloseOperation(3);
    this.frame.setLayout(new BorderLayout());
    this.frame.add(this, "Center");
    this.frame.pack();
    this.frame.setResizable(false);
    this.frame.setLocationRelativeTo(null);
    this.frame.setVisible(true);
  }

  public void init()
  {
    int index = 0;
    for (int r = 0; r < 6; r++) {
      for (int g = 0; g < 6; g++) {
        for (int b = 0; b < 6; b++) {
          int rr = r * 255 / 5;
          int gg = g * 255 / 5;
          int bb = b * 255 / 5;

          this.colors[(index++)] = (rr << 16 | gg << 8 | bb);
        }
        resetGame();
      }
      //  setMenu(new TitleMenu());
      }
    
      this.screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/SpriteSheet.png"));
      this.input = new InputHandler(this);
      this.level = new Level("/levels/Town1.png");
      this.player = new Player(this.level, 38, 25, this.input);
      this.zombie = new Zombie(this.level, 80, 40);
      this.slime = new Slime(this.level, 75,75);
      this.level.addEntity(this.player);
      this.level.addEntity(this.zombie);
      this.level.addEntity(this.slime);
	  this.events = new Events();

  

    }

//TOWN1
  public static void startLevel(String levelPath, int x, int y) {
			level = new Level(levelPath);    
			player = new Player(level, x, y, input);
			zombie = new Zombie(level, 35, 50);
			slime = new Slime(level, 55, 55);
			level.addEntity(zombie);
			level.addEntity(player);   
			level.addEntity(slime);
			events = new Events();

}
  public static void startHouse1Level(String levelPath, int x, int y){
		level = new Level(levelPath);    
		Player player = new Player(level, 90, y + 40, input);
		level.addEntity(player);
		events = new Events ();
		
  }
  
  public static void startTOWN1(String levelPath, int x, int y){
	  level = new Level(levelPath);
	  Player player = new Player(level, x, y, input);
	  level.addEntity(player);
		zombie = new Zombie(level, 35, 50);
		slime = new Slime(level, 55, 55);
		level.addEntity(zombie);
		level.addEntity(player);   
		level.addEntity(slime);
		events = new Events();
  }
  // Screen after TOWN1 (confusing with LEVEL1)
  public static void startLEVEL1(String levelPath, int x, int y){
	  level = new Level(levelPath);
	  Player player = new Player(level, x, y, input);
	  level.addEntity(player);
		zombie = new Zombie(level, 35, 50);
		slime = new Slime(level, 55, 55);
		level.addEntity(zombie);
		level.addEntity(player);   
		level.addEntity(slime);
		events = new Events();
  }
public synchronized void start()
  {
    this.running = true;
    new Thread(this).start();
  }

  public synchronized void stop()
  {
    this.running = false;
  }

  public void resetGame()
  {
  }

  public void run() {
    long lastTime = System.nanoTime();
    double nsPerTick = 16666666.666666666D;
    int frames = 0;
    int ticks = 0;
    long lastTimer1 = System.currentTimeMillis();

    double delta = 0.0D;

    init();

    while (this.running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / nsPerTick;
      lastTime = now;
      boolean shouldRender = true;

      while (delta >= 1.0D) {
        ticks++;
        tick();
        delta -= 1.0D;
        shouldRender = true;
      }
      try
      {
        Thread.sleep(2L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (shouldRender) {
        frames++;
        render();
      }

      if (System.currentTimeMillis() - lastTimer1 > 1000L) {
        lastTimer1 += 1000L;
        this.frame.setTitle(ticks + " ticks, " + frames + " fps");
        frames = 0;
        ticks = 0;
      }
    }
  }

  private void tick()
  {
    this.tickCount += 1;
    this.level.tick();
  }

  public void render()
  {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(1);
      return;
    }

    int xOffset = this.player.x - this.screen.width / 2;
    int yOffset = this.player.y - this.screen.height / 2;

    this.level.renderTiles(this.screen, xOffset, yOffset);

    for (int x = 0; x < this.level.width; x++)
    {
      int color = Colors.get(-1, -1, -1, 0);
      if ((x % 10 == 0) && (x != 0)) {
        color = Colors.get(-1, -1, -1, 500);
      }

    }

    this.level.renderEntities(this.screen);
   // events.renderInterface(screen, xOffset, yOffset);
    events.renderPlayerEvents(screen, xOffset, yOffset, input, player, level);
 
    
    for (int y = 0; y < this.screen.height; y++) {
      for (int x = 0; x < this.screen.width; x++) {
        int colorCode = this.screen.pixels[(x + y * this.screen.width)];
        if (colorCode < 255) {
          this.pixels[(x + y * 180)] = this.colors[colorCode];
        }

      }

      Graphics g = bs.getDrawGraphics();
      g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
      g.dispose();
      bs.show();
    }
  }

  /*private void renderGui() { for (int y = 0; y < 2; y++) {
      for (int x = 0; x < 20; x++) {
        this.screen.render(x * 8, this.screen.height - 16 + y * 8, 384, Colors.get(0, 0, 0, 0), 0);
      }
    }
   // if (this.menu != null)
   //   this.menu.render(this.screen);
  }  */

  public static void main(String[] args)
  {
    new Game().start();
	
	
}
}