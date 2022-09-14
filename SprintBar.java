import java.awt.*;

public class SprintBar implements Draw{
    DisplayBar displayBar;
    private int sprintBarX = GameConst.LEFT_DISPLAY_SETX;
    private int sprintBarY = GameConst.SPRINT_BAR_SETY;
    private int sprintBarLength;
    SprintBar(){
        this.displayBar = new DisplayBar(this.sprintBarX, this.sprintBarY, "", "SPRINT");
        this.sprintBarLength = GameConst.DEF_SPRINT_BAR_LENGTH;
    }
    public int getSprint(){
        return this.sprintBarLength;
    }
    public void consumeSprint(){
        if(this.sprintBarLength > 0){
            this.sprintBarLength-=2;
        }
    }
    public void rechargeSprint(){
        if(this.sprintBarLength < GameConst.DEF_SPRINT_BAR_LENGTH){
            this.sprintBarLength++;
        }
    }
    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(this.sprintBarX+6, this.sprintBarY+6, this.sprintBarLength, GameConst.DEF_DISPLAY_HEIGHT-12);
    }
}