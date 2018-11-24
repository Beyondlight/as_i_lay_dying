package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet
{
  public int width;
  public int height;
  public int[] pixels;
  public String path;

  public SpriteSheet(String path)
  {
    BufferedImage image = null;
    try
    {
      image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (image == null) {
      return;
    }

    this.path = path;
    this.width = image.getWidth();
    this.height = image.getHeight();

    this.pixels = image.getRGB(0, 0, this.width, this.height, null, 0, this.width);

    for (int i = 0; i < this.pixels.length; i++)
      this.pixels[i] = ((this.pixels[i] & 0xFF) / 64);
  }
}