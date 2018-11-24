package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler
  implements KeyListener
{
  public Key up = new Key();
  public Key down = new Key();
  public Key left = new Key();
  public Key right = new Key();
  public Key attack = new Key();
  public Key menu = new Key();
  public Key esc = new Key();
  public Key enter = new Key();

  public InputHandler(Game game)
  {
    game.addKeyListener(this);
  }

  public void keyPressed(KeyEvent e)
  {
    toggleKey(e.getKeyCode(), true);
  }

  public void keyReleased(KeyEvent e) {
    toggleKey(e.getKeyCode(), false);
  }

  public void keyTyped(KeyEvent e) {
  }

  public void toggleKey(int keyCode, boolean isPressed) {
    if (keyCode == KeyEvent.VK_W) {
      this.up.toggle(isPressed);
    }
    if (keyCode == KeyEvent.VK_S) {
      this.down.toggle(isPressed);
    }
    if (keyCode == KeyEvent.VK_A) {
      this.left.toggle(isPressed);
    }
    if (keyCode == KeyEvent.VK_D) {
      this.right.toggle(isPressed);
    }
    if (keyCode == 38) {
      this.up.toggle(isPressed);
    }
    if (keyCode == 40) {
      this.down.toggle(isPressed);
    }
    if (keyCode == 37) {
      this.left.toggle(isPressed);
    }
    if (keyCode == 39) {
      this.right.toggle(isPressed);
    }
    if (keyCode == KeyEvent.VK_SPACE) {
      this.attack.toggle(isPressed);
    }
    if (keyCode == KeyEvent.VK_ESCAPE){
      this.esc.toggle(isPressed);
    }
  	if (keyCode == KeyEvent.VK_ENTER){
  		this.enter.toggle(isPressed);
  	
  	}
  }

  public class Key
  {
    private boolean pressed = false;

    public Key() {  } 
    public boolean isPressed() { return this.pressed; }


    public void toggle(boolean isPressed)
    {
      this.pressed = isPressed;
    }
  }
}