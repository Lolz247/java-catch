import java.awt.*;

class NoMoveArea implements Draw{
    private int areaX;
    private int areaY;
    private int areaBase;
    private int areaHeight;
    private int areaDropDist;
    private int timesDropped;
    private int charX;
    private int dropDelay;
    GameCharacter other;
    NoMoveArea(GameCharacter other){
        this.areaX = GameConst.GAME_BOX_SETX-40;
        this.areaY = GameConst.GAME_BOX_SETY;
        this.areaBase = GameConst.GAME_BOX_BASE+80;
        this.areaHeight = GameConst.GAME_BOX_HEIGHT/4;
        this.areaDropDist = this.areaHeight;
        this.timesDropped = 0;
        this.dropDelay = 0;
        this.other = other;
    }
    public void drop(){
        if(this.dropDelay >= GameConst.NO_MOVE_AREA_DROP_DELAY){
            this.timesDropped++;
            this.areaY+=this.areaDropDist;
        }
    }
    //checks if the character is in the area
    public boolean isArea(){
        if(this.timesDropped == 3 && this.dropDelay >= GameConst.NO_MOVE_AREA_DROP_DELAY){
            this.charX = other.getHitX();
            return true;
        } else {
            return false;
        }
    }
    public void resetDropDelay(){
        if(this.dropDelay >= GameConst.NO_MOVE_AREA_DROP_DELAY){
            this.dropDelay = 0;
        }
    }
    public boolean collision(){
        if(this.timesDropped == 3){
            return this.charX != other.getHitX();
        } else {
            return false;
        }
    }
    //checks if the area exits game border
    public boolean out(){
        return this.timesDropped == 4;
    }
    public void draw(Graphics g){
        this.dropDelay++;
        g.setColor(GameConst.LIGHT_BLUE);
        g.fillRect(this.areaX, this.areaY, this.areaBase, this.areaHeight);
        g.setColor(Color.BLACK);
        g.fillRect(this.areaX, this.areaY-20, 20, this.areaHeight+40);
        g.fillRect(this.areaX+this.areaBase-20, this.areaY-20, 20, this.areaHeight+40);
        g.setColor(Color.WHITE);
        g.fillRect(this.areaX+4, this.areaY-16, 12, this.areaHeight+32);
        g.fillRect(this.areaX+this.areaBase-16, this.areaY-16, 12, this.areaHeight+32);
    }
}