package tiles;

import entities.Mob;
import gfx.Colors;
import gfx.Screen;
import level.Level;

public abstract class Tile {

	// COMMON TILES
	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colors.get(000,
			-1, -1, -1), 0xFF000000);
	public static final Tile STONE = new BasicSolidTile(4, 20, 0, Colors.get(-1,
			333, -1, -1), 0xFF555555);
	public static final Tile BRICK = new BasicSolidTile(9, 5, 5, Colors.get(256,
			200, 171, 177), 0xFF9e541f);
	public static final Tile DOORTOP = new BasicSolidTile(10, 6, 5, Colors.get(170,
		200, 349, 230), 0xFFfff54e);
	public static final Tile DOORBOTTOM = new BasicTile(11, 7, 5, Colors.get(170,
			200, 349, 230), 0xFFfef54e);
	public static final Tile GLASS = new BasicSolidTile(12, 8, 5, Colors.get(234, 234,
			234, 141), 0xFF4fffff);
	public static final Tile WOOD = new BasicSolidTile(13, 9, 5, Colors.get(540, 0,
			276, 276), 0xFF00579b);
	public static final Tile GRASS = new BasicTile(2, 2, 0, Colors.get(-1, 331,
			141, -1), 0xFF00FF00); // 331
	public static final Tile DIRT = new BasicTile(1, 1, 0, Colors.get(177, 177, 171,177), 0xFFb8860b);
	// 331
	public static final Tile FLOOR = new BasicTile(17, 10, 5, Colors.get(320, 542, 540, 320), 0xFF8a172b);
	
	// ANIMATED TILES
	public static final Tile WATER = new AnimatedTile(3, new int[][] {{ 17, 0 }, { 18, 0 }, { 19, 0 }, { 17, 0 }},
	            Colors.get(-1, 128, 137, -1), 0xFF0000FF, 500);
	public static final Tile SMOKE = new AnimatedTile(15, new int[][] {{ 21, 0}, { 22, 0 }, { 23, 0}, { 21, 0}}, Colors.get(332, 330, 218, -1), 0xFF757575, 250);
	
	// DEADLY TILES
	public static final Tile CACTUS = new DeadlyTile(5, 15, 4, Colors.get(0, 340, 346, 331), 0xFF1ad01a);
	public static final Tile CACTUS2 = new DeadlyTile(6, 16, 4, Colors.get(0, 340, 346, 331), 0xFF1acf1a);
	public static final Tile CACTUS3 = new DeadlyTile(7, 15, 5, Colors.get(0, 340, 346, 331), 0xFF1ace1a);
	public static final Tile CACTUS4 = new DeadlyTile(8, 16, 5, Colors.get(0, 340, 346, 331), 0xFF1acd1a);
	//public static final Tile CACTUS = new DeadlyTile(5, 3, 9, Colors.get(317, 317, 330, -1), 0xFF1ad01a);

	// INTERACTION TILES
	public static final Tile DOOR_ENTER = new BasicSolidTile(14, 6, 5, Colors.get(170, 200, 210, 230), 0xFFe8219c);
    public static final Tile DOOR_LEAVE = new BasicTile(16, 9, 4, Colors.get(32, 210, 205, 220), 0xFFfbc9c2);
	public static final Tile TOWN1_LEAVE = new BasicTile(18, 1, 0, Colors.get(177, 177, 171, 177), 0xFFf59e8b);
	public static final Tile TOWN1_ENTER = new BasicTile(19, 1, 0, Colors.get(177, 177, 171, 177), 0xFF383e19);
	
	public byte id;
	protected boolean solid;
	protected boolean emitter;
    protected boolean trigger;
    private int levelColor;
	public boolean connectsToGRASS = false;
	public boolean connectsToSAND = false;
	public boolean connectsToWATER = false;

	public Tile(int id, boolean isSolid, boolean isEmitter, int levelColor) {
		this.id = (byte) id;
		if (tiles[id] != null)
			throw new RuntimeException("Duplicate tile id on" + id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.levelColor = levelColor;
		tiles[id] = this;

	}

	public byte getId() {
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isEmitter() {
		return emitter;
	}

	public int getLevelColor() {
		return levelColor;
	}
	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
	}

	public abstract void tick();

	public abstract void render(Screen screen, Level level, int x, int y);

}
