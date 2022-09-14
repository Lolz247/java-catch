public class LifeBar{
    DisplayBar displayBar;
    private String lifeString;
    private int lifeBarX;
    private int lifeBarY;
    
    public LifeBar(int life){
        this.lifeString = String.valueOf(life);
        this.lifeBarX = GameConst.RIGHT_DISPLAY_SETX;
        this.lifeBarY = GameConst.LIFE_BAR_SETY;
        this.displayBar = new DisplayBar(this.lifeBarX, this.lifeBarY, this.lifeString, "LIVES");
    }
}