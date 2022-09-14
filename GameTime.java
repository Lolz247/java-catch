class GameTime{
    private long startTime = 0;
    private long currentTime;
    private long timeElapsed;
    private int levelTimeInterval;
    private int timeCheck;
    
    public void updateTime(){
        this.currentTime = System.currentTimeMillis()/1000;
        this.timeElapsed = (currentTime-startTime);
        this.levelTimeInterval = 15;
        this.timeCheck = (int)currentTime;
    }
    public long getTimeElapsed(){
        return this.timeElapsed;
    }
    public boolean checkTimeChange(){
        if(this.timeCheck < this.timeElapsed){
            this.timeCheck = (int)this.timeElapsed;
            return true; 
        } else {
            return false;
        }
    }
    public long getStartTime(){
        return this.startTime;
    }
    public void setStartTime(boolean start){
        if(start){
            this.startTime = System.currentTimeMillis()/1000;
        }
    }
    public int getLevel(){
        if(this.timeElapsed >= this.levelTimeInterval*9){
            return 10;
        } else {
            return ((((int)this.timeElapsed)/this.levelTimeInterval)+1);
        }
    }
}