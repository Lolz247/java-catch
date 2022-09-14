import java.awt.*;

abstract class GameItem{
    private int dropSpeed;
    private int scoreValue;
    private int itemY;
    private Color c;
    protected int sideMovement;
    protected int itemSize;
    protected int itemX;

    public GameItem(int level, int scoreValue, int sideMovement, Color c){
        this.dropSpeed = level/2 + 4;
        this.itemY = 50;
        this.itemSize = GameConst.DEF_ITEM_SIZE;
        this.scoreValue = scoreValue;
        this.sideMovement = sideMovement;
        this.c = c;
    }
    public int getScoreValue(){
        return this.scoreValue;
    }
    public int getItemX(){
        return this.itemX;
    }
    public int getItemY(){
        return this.itemY;
    }
    public void dropItem(int dropLocation){
        this.itemX = dropLocation;
        this.itemY = GameConst.GAME_BOX_SETY;
    }
    public void dropping(){
        this.itemY+=this.dropSpeed;
        if(this.itemX < 321){
            this.itemX = 321;
        }
        if(this.itemX > 559){
            this.itemX = 559;
        }
    }
    public void sideMovement(GameCharacter other){
        if(this.itemX > other.getCharCenter()){
            this.itemX+=this.sideMovement;
        } else if(this.itemX < other.getCharCenter()){
            this.itemX-=this.sideMovement;
        }
    }
    public boolean charCollide(GameCharacter other){
        if(this.itemX>=other.getHitX() && this.itemX<=(other.getHitX()+other.getHitLength()) && (this.itemY+this.itemSize)>other.getHitY() && (this.itemY+this.itemSize)<=(other.getHitY()+other.getHitWidth())){
            return true;
        } else {
            return false;
        }
    }
    public boolean groundCollide(GameBackdrop other){
        if(this.itemY+40 >= other.getGroundY()){
            return true;
        } else {
            return false;
        }
    }
    public void draw(Graphics g){
        g.setColor(this.c);
        g.fillOval(this.itemX, this.itemY, this.itemSize, this.itemSize);
    }
}