public class LevelBar{
    DisplayBar displayBar;
    private String levelString;
    private int levelBarX;
    private int levelBarY;
    
    public LevelBar(int level){
        this.levelString = String.valueOf(level);
        this.levelBarX = GameConst.RIGHT_DISPLAY_SETX;
        this.levelBarY = GameConst.LEVEL_BAR_SETY;
        this.displayBar = new DisplayBar(this.levelBarX, this.levelBarY, this.levelString, "LEVEL");
    }
}