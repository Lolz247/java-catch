public class TimeBar{
    DisplayBar displayBar;
    private String timeString;
    private int timeBarX;
    private int timeBarY;
    
    public TimeBar(long time){
        this.timeString = String.valueOf(time);
        this.timeBarX = GameConst.LEFT_DISPLAY_SETX;
        this.timeBarY = GameConst.TIME_BAR_SETY;
        this.displayBar = new DisplayBar(this.timeBarX, this.timeBarY, this.timeString, "TIME");
    }
}