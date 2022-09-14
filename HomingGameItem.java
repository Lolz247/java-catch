import java.awt.*;

public class HomingGameItem extends GameItem{
    int itemY;
    boolean hasHit;
    HomingGameItem(int level){
        super(level, GameConst.HOMING_ITEM_VALUE, 1, Color.MAGENTA);
        this.itemX = (int)((Math.random()*(GameConst.GAME_BOX_BASE-20))+GameConst.GAME_BOX_SETX);
        this.itemY = GameConst.GAME_BOX_SETY;
        this.hasHit = false;
    }
    @Override
    public void sideMovement(GameCharacter other){
        if(super.itemX > other.getX()+25){
            super.itemX-=this.sideMovement;
        }
        if(super.itemX < other.getX()+75){
            super.itemX+=this.sideMovement;
        }
    }
    public void increaseSize(){
        super.itemX-=3;
        super.itemSize+=6;
    }
    public boolean getHasHit(){
        return this.hasHit;
    }
    public void setHasHit(){
        this.hasHit = true;
    }
}