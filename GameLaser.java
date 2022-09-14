import java.awt.*;

public class GameLaser{
    PreLaser preLaser;
    Beam beam;
    private int locationX;
    private int locationY;
    private int laserLocationY;
    private int width;
    private int height;
    private int laserHeight;
    private int delay;
    private boolean hasHit;
    GameLaser(GameCharacter other){
        this.preLaser = new PreLaser();
        this.beam = new Beam();
        this.locationX = other.getCharCenter();
        this.locationY = GameConst.GAME_BOX_SETY;
        this.laserLocationY = this.locationY+10;
        //keeps laser inside borders
        if(this.locationX > GameConst.GAME_BOX_SETX+GameConst.GAME_BOX_BASE-this.width-5){
            this.locationX = GameConst.GAME_BOX_SETX+GameConst.GAME_BOX_BASE-this.width-5;
        }
        if(this.locationX < GameConst.GAME_BOX_SETX+5){
            this.locationX = GameConst.GAME_BOX_SETX+5;
        }
        this.width = GameConst.LASER_WIDTH;
        this.height = GameConst.GAME_BOX_HEIGHT;
        this.laserHeight = 0;
        this.delay = 0;
        this.hasHit = false;
    }
    public int delay(){
        return this.delay;
    }
    public boolean getHasHit(){
        return this.hasHit;
    }
    public void setHasHit(){
        this.hasHit = true;
    }
    //draws prelaser
    public class PreLaser implements Draw{
        public void draw(Graphics g) { 
            delay++;       
            g.setColor(Color.RED);
            g.fillRect(locationX-5, laserLocationY, 1, height);
            g.fillRect(locationX+5+width, laserLocationY, 1, height);

            g.setColor(Color.BLACK);
            g.fillRect(locationX-15, locationY+6, 42, 42);
            g.fillRect(locationX-10, locationY+4, 32, 56);
            g.fillRect(locationX-6, locationY+10, 10, 55);
            g.fillRect(locationX+8, locationY+10, 10, 55);
            g.setColor(Color.WHITE);
            g.fillRect(locationX-13, locationY+8, 38, 40);
            g.fillRect(locationX-8, locationY+6, 28, 52);
            g.fillRect(locationX-4, locationY+10, 6, 53);
            g.fillRect(locationX+10, locationY+10, 6, 53);
            g.setColor(Color.BLACK);
            g.fillRect(locationX-8, locationY+14, 11, 18);
            g.fillRect(locationX+10, locationY+14, 11, 18);
            g.fillRect(locationX+5, locationY+10, 3, 40);
            g.fillRect(locationX+4, locationY+50, 5, 5);
        }
    }
    //draws laser
    public class Beam implements Draw{
        public boolean collides(GameCharacter other){
            if((locationX>other.getHitX() && locationX<other.getHitX()+other.getHitLength()) || (locationX+width>other.getHitX()+5 && locationX+width<other.getHitX()+other.getHitLength())){
                return true;
            } else {
                return false;
            }
        }
        public void draw(Graphics g) {       
            g.setColor(Color.RED);
            g.fillRect(locationX-5, laserLocationY, width+10, laserHeight);     
            g.setColor(Color.WHITE);
            g.fillRect(locationX, laserLocationY, width, laserHeight);

            g.setColor(Color.BLACK);
            g.fillRect(locationX-15, locationY+6, 42, 42);
            g.fillRect(locationX-10, locationY+4, 32, 56);
            g.fillRect(locationX-6, locationY+10, 10, 55);
            g.fillRect(locationX+8, locationY+10, 10, 55);
            g.setColor(Color.WHITE);
            g.fillRect(locationX-13, locationY+8, 38, 40);
            g.fillRect(locationX-8, locationY+6, 28, 52);
            g.fillRect(locationX-4, locationY+10, 6, 53);
            g.fillRect(locationX+10, locationY+10, 6, 53);
            g.setColor(Color.BLACK);
            g.fillRect(locationX-8, locationY+14, 11, 18);
            g.fillRect(locationX+10, locationY+14, 11, 18);
            g.fillRect(locationX+5, locationY+10, 3, 40);
            g.fillRect(locationX+4, locationY+50, 5, 5);
            g.setColor(GameConst.LIGHT_BLUE);
            g.fillOval(locationX-6, locationY+18, 6, 12);
            g.setColor(Color.BLACK);
            g.fillRect(locationX-4, locationY+24, 3, 5);
            //animates laser
            laserHeight+=(height/4);
            if(delay() > 98){
                laserLocationY+=(height/4);
            }
        }
    }
}