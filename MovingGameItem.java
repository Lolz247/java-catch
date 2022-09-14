import java.awt.*;

public class MovingGameItem extends GameItem implements Draw{
    int itemY;
    MovingGameItem(int level){
        super(level, GameConst.MOVING_ITEM_VALUE, 2, Color.YELLOW);
        this.itemX = (int)((Math.random()*(GameConst.GAME_BOX_BASE-20))+GameConst.GAME_BOX_SETX);
        this.itemY = GameConst.GAME_BOX_SETY;
    }
}