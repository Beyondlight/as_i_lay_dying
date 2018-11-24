package gfx;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import main.Game;


public class Menu extends JFrame {
  
  private static final long serialVersionUID = 1L;
  private JButton jButton1 = new JButton();
  public static boolean enterLevel;
  public static boolean enterDungeonForest;
  public static boolean credtis;
  public static boolean running = false;
  //private BufferedImage image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);

  
  public Menu(String Title) { 
  
    super(Title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 550; 
    int frameHeight = 450;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
	

    
    jButton1.setBounds(118, 300, 305, 57);
    jButton1.setText("Start");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    jButton1.setBackground(Color.WHITE);
    jButton1.setBorder(BorderFactory.createEtchedBorder(0, Color.BLACK, Color.BLUE));
    cp.add(jButton1);
   
    cp.setBackground(new Color(0xFFfffff));
   
    setVisible(true);
  } 
  

  public void jButton1_ActionPerformed(ActionEvent evt) { // ENTER LEVEL
    if(running == false) {
    	Game.main(null);
    	closeMenu();
    	} else {
    		System.out.println("Already running!");
    	}
  } 
  
  public void closeMenu() {
      WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
      Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
}
  

  
  public static void main(String[] args) {
    new Menu("Menu");
  } 
  
} 
