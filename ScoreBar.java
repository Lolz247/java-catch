public class ScoreBar{
    DisplayBar displayBar;
    private String scoreString;
    private int scoreBarX;
    private int scoreBarY;
    
    public ScoreBar(int score){
        this.scoreString = String.valueOf(score);
        this.scoreBarX = GameConst.RIGHT_DISPLAY_SETX;
        this.scoreBarY = GameConst.SCORE_BAR_SETY;
        this.displayBar = new DisplayBar(this.scoreBarX, this.scoreBarY, this.scoreString, "SCORE");
    }
}