package tiles;

public class AnimatedTile extends BasicTile
{
  private int[][] animationTileCoords;
  private int currentAnimationIndex;
  private long lastIterationTime;
  private int animationSwitchDelay;

  public AnimatedTile(int id, int[][] animationCoords, int tileColor, int levelColor, int animationSwitchDelay)
  {
    super(id, animationCoords[0][0], animationCoords[0][1], tileColor, levelColor);
    this.animationTileCoords = animationCoords;
    this.currentAnimationIndex = 0;
    this.lastIterationTime = System.currentTimeMillis();
    this.animationSwitchDelay = animationSwitchDelay;
  }

  public void tick() {
    if (System.currentTimeMillis() - this.lastIterationTime >= this.animationSwitchDelay) {
      this.lastIterationTime = System.currentTimeMillis();
      this.currentAnimationIndex = ((this.currentAnimationIndex + 1) % this.animationTileCoords.length);
      this.tileId = (this.animationTileCoords[this.currentAnimationIndex][0] + this.animationTileCoords[this.currentAnimationIndex][1] * 32);
    }
  }
}