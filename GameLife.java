class GameLife{
    private int livesRemaining;
    
    GameLife(){
        this.livesRemaining = 5;
    }
    public int getLivesRemaining(){
        return this.livesRemaining;
    }
    public void takeDamage(){
        this.livesRemaining--;
    } 
    public boolean alive(){
        if(this.livesRemaining > 0){
            return true;
        } else {
            return false;
        }
    }
}