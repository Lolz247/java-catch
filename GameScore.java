public class GameScore {
    private int score = 0;
    public void increaseScore(int score){
        this.score+=score;
        if(this.score<0){
            this.score=0;
        }
    }
    public int getScore(){
        return this.score;
    }
}