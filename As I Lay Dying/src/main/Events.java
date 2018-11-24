package main;

import level.Level;
import entities.Player;
import gfx.Colors;
import gfx.Font;
import gfx.Screen;

public class Events {


static long lastTime;
static boolean playerIsIndoor = false;
//private long lastShot;
//private long Time;
public static boolean overItem = false;
public static boolean overCoin = false;

//private int green = Colors.get(-1, 555, 141, 400);
//private int blue = Colors.get(-1, 555, 115, 400);
//private int orange = Colors.get(-1, 555, 542, 400);
//private int red = Colors.get(-1, 555, 500, 400);
//private int black = Colors.get(-1, 555, 000, 400);

//private int playerHealth = 3;
static int bullets = 0;
public static int shotbullet = 0;

public Events() {
	
}


/*public void renderInterface(Screen screen, int x, int y) {
if (!playerIsIndoor)
if (playerHealth == 3) // HEALTH
    Font.render("ccc", screen, x+1, y, Colors.get(-1, 555, -1, 400));
if (playerHealth == 2) 
	Font.render("cc", screen, x+1, y, Colors.get(-1, 555, -1, 400));
if (playerHealth == 1) 
	Font.render("c", screen, x+1, y, Colors.get(-1, 555, -1, 400));
}
*/
	
public void renderPlayerEvents(Screen screen, int x, int y, InputHandler input, Player player, Level level) {
	
	
if (Player.triggeredDOOR == true) {  
	Font.render("ENTER", screen, x + 30, y + 37, Colors.get(-1, 135, -1, 000));
	if (input.enter.isPressed()) {
		//210 45
		Game.startHouse1Level("/levels/House1.png", 135, 85);
		playerIsIndoor = true;
	}
}

if (Player.triggeredDOOR_LEAVE == true) {   
	Font.render("LEAVE", screen, x - 140 , y + 90 , Colors.get(-1, 135, -1, 000));
	if (input.enter.isPressed()) {
		Game.startLevel("/levels/Town1.png", 265, 90);
		playerIsIndoor = false;
	}
}
	if (Player.triggeredTOWN1_ENTER == true){
		if (input.left.isPressed()){
			Game.startTOWN1("/levels/Town1.png", 135, 85);
		}
}
	if (Player.triggeredTOWN1_LEAVE == true){
		if (input.right.isPressed()){
			Game.startLEVEL1("/levels/Level1.png", 135, 85);
		}
	}
}
}


