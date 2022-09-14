import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class GameGame{
    //frame
    GameGraphic gameGraphic;
    JFrame frame;
    BasicKeyListener myKeyListener;
    private int frameCount;
    private int frameWidth;
    private int frameHeight;
    private boolean pressed[];
    JPanel panel;
    JTextField inputField;
    JLabel messageLabel;
    JButton enterButton;
    //displays and statistics
    BeginGame menu;
    GameBackdrop gameBackdrop;
    ScoreBar scoreBar;
    TimeBar timeBar;
    LevelBar levelBar;
    LifeBar lifeBar;
    SprintBar sprintBar;
    HighscoreBoard highscore;
    GameGuide guide;
    GameTime time;
    GameLife life;
    GameScore score;
    EndGame gameOver;
    //game characters, items, etc.
    GameCharacter avatar;
    ArrayList<GameItem> itemsOnScreen;
    ArrayList<GameLaser> laserFiring;
    ArrayList<NoMoveArea> noMoveArea;
    int damageItemCooldown;
    int laserCooldown;
    int areaCooldown;
    Boolean endCheck = true;
    
    public GameGame() throws Exception{
        this.gameGraphic = new GameGraphic();
        this.pressed = new boolean[]{false, false, false};
        //build frame
        this.frameWidth = GameConst.FRAME_WIDTH;
        this.frameHeight = GameConst.FRAME_HEIGHT;
        this.frame = new JFrame("java!catch");
        this.myKeyListener = new BasicKeyListener();
        this.frameCount = 0;
        //initializing displays
        this.menu = new BeginGame();
        this.gameBackdrop = new GameBackdrop();
        this.score = new GameScore();
        this.scoreBar = new ScoreBar(score.getScore());
        this.time = new GameTime();
        this.timeBar = new TimeBar(time.getTimeElapsed());
        this.levelBar = new LevelBar(time.getLevel());
        this.life = new GameLife();
        this.lifeBar = new LifeBar(life.getLivesRemaining());
        this.sprintBar = new SprintBar();
        this.highscore = new HighscoreBoard();
        this.guide = new GameGuide();
        this.gameOver = new EndGame();
        //initializing character and items
        this.avatar = new GameCharacter();
        this.itemsOnScreen = new ArrayList<GameItem>();
        this.laserFiring = new ArrayList<GameLaser>();
        this.noMoveArea = new ArrayList<NoMoveArea>();
        this.damageItemCooldown = 0; //will default to 4
        this.laserCooldown = 0; //will default to 5
        this.areaCooldown = 0; //will default to 7
    }
    class GameGraphic extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            gameBackdrop.draw(g);
            scoreBar.displayBar.draw(g);
            timeBar.displayBar.draw(g);
            levelBar.displayBar.draw(g);
            lifeBar.displayBar.draw(g);
            sprintBar.displayBar.draw(g);
            sprintBar.draw(g);
            highscore.draw(g);
            for(NoMoveArea i:noMoveArea){
                i.draw(g);
            }
            if(menu.getStartGame()){
                avatar.draw(g);
            }
            for(GameLaser i:laserFiring){
                i.preLaser.draw(g);
                if(i.delay()>GameConst.LASER_DELAY){
                    i.beam.draw(g);
                }
            }
            for(GameItem i:itemsOnScreen){
                i.dropping();
                i.draw(g);
            }
            if(menu.getKeepMenu()){
                menu.draw(g);
            }
            if(menu.getOpenGuide()){
                guide.draw(g);
            }
            if(life.getLivesRemaining() == 0){
                gameOver.setScore(score);
                gameOver.setTime(time);
                try {
                    gameOver.checkHighscore(highscore);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                gameOver.draw(g);
            }
        }
    }
    public class BasicKeyListener implements KeyListener{
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            //menu and guide controls
            if(key == KeyEvent.VK_SPACE && menu.canStart() && !menu.getStartGame() && !menu.getOpenGuide()){
                menu.setKeepMenu(false);
                menu.setStartGame(true);
            } else if(key == KeyEvent.VK_H && !menu.getOpenGuide()){
                menu.setOpenGuide(true);
            } else if((key == KeyEvent.VK_H || key == KeyEvent.VK_ESCAPE)&& menu.getOpenGuide()){
                menu.setOpenGuide(false);
            }
            //character controls
            if(key == KeyEvent.VK_LEFT && menu.getStartGame()){
                pressed[0] = true;
            }
            if(key == KeyEvent.VK_RIGHT && menu.getStartGame()){
                pressed[1] = true;
            }
            if(key == KeyEvent.VK_SHIFT && menu.getStartGame()){
                pressed[2] = true;
            }
        }
        public void keyReleased(KeyEvent e){ 
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_LEFT && menu.getStartGame()){
                pressed[0] = false;
            }
            if(key == KeyEvent.VK_RIGHT && menu.getStartGame()){
                pressed[1] = false;
            }
            if(key == KeyEvent.VK_SHIFT && menu.getStartGame()){
                pressed[2] = false;
            }
        }       
        public void keyTyped(KeyEvent e){
        }
    }
    public void gameItemDelay(){
        this.damageItemCooldown = 4; 
        this.laserCooldown = 5; 
        this.areaCooldown = 7;
    }
    public void update(){
        if(this.menu.getStartGame()){
            this.frameCount++;
            //updates time
            if(this.time.getStartTime() == 0){
                this.time.setStartTime(this.menu.getStartGame());
            }
            this.time.updateTime();
            if(this.frameCount % 50 == 0){
                this.score.increaseScore(1);
            }
            //updates displays
            this.scoreBar = new ScoreBar(score.getScore());
            this.timeBar = new TimeBar(time.getTimeElapsed());
            this.levelBar = new LevelBar(time.getLevel());
            this.lifeBar = new LifeBar(life.getLivesRemaining());
            //randomizes items dropped
            if(this.frameCount%30==0){
                this.damageItemCooldown--;
                this.laserCooldown--;
                this.areaCooldown--;
                if(this.laserCooldown < 0){
                    this.laserCooldown = 0;
                }
                if(this.damageItemCooldown < 0){
                    this.damageItemCooldown = 0;
                }
                if(this.areaCooldown < 0){
                    this.areaCooldown = 0;
                }
                int itemSelection = (int)(Math.random()*6);
                if(itemSelection == 0){
                    this.itemsOnScreen.add(new BasicGameItem(this.time.getLevel()));
                } else if(itemSelection == 1){
                    if(this.damageItemCooldown == 0){
                        itemsOnScreen.add(new DamageGameItem(this.time.getLevel()));
                    }
                } else if(itemSelection == 2){
                    itemsOnScreen.add(new MovingGameItem(this.time.getLevel()));
                } else if(itemSelection == 3){
                    itemsOnScreen.add(new HomingGameItem(this.time.getLevel()));
                } else if(itemSelection == 4){
                    if(laserCooldown == 0){
                        this.laserFiring.add(new GameLaser(this.avatar));
                        gameItemDelay();
                    }
                } else if(itemSelection == 5){
                    if(this.areaCooldown == 0){
                        this.noMoveArea.add(new NoMoveArea(this.avatar));
                        gameItemDelay();
                    }
                }
                int random = (int)(Math.random()*(GameConst.GAME_BOX_BASE-10))+GameConst.GAME_BOX_SETX;
                for(GameItem i:this.itemsOnScreen){
                    if(!(i.getItemY() > 50)){
                        i.dropItem(random);
                    }
                }
            }
        }
        //drops and check item/laser/area statuses
        for(GameItem i:this.itemsOnScreen){
            i.dropping();
            i.sideMovement(this.avatar);
            if(i.charCollide(this.avatar)){
                if(i instanceof DamageGameItem){
                    this.life.takeDamage();
                }
                if(!(i instanceof HomingGameItem)){
                    this.score.increaseScore(i.getScoreValue());
                    this.itemsOnScreen.remove(i);
                    break;
                } else {
                    ((HomingGameItem)i).increaseSize();
                    if(!((HomingGameItem)i).getHasHit()){
                        this.score.increaseScore(i.getScoreValue());
                        ((HomingGameItem)i).setHasHit();
                    }
                }
            }
            if(i.groundCollide(this.gameBackdrop)){
                this.itemsOnScreen.remove(i);
                break;
            }
        }
        for(GameLaser i:this.laserFiring){
            if(i.beam.collides(this.avatar) && !i.getHasHit() && i.delay() > GameConst.LASER_DELAY){
                i.setHasHit();
                this.life.takeDamage();
            }
            if(i.delay() > GameConst.LASER_APPEARANCE_TIME){
                this.laserFiring.remove(i);
                break;
            }
        }
        for(NoMoveArea i:noMoveArea){
            i.drop();
            i.isArea();
            i.resetDropDelay();
            if(i.collision()){
                this.life.takeDamage();
                this.noMoveArea.remove(i);
                break;
            }
            if(i.out()){
                this.noMoveArea.remove(i);
                break;
            }
        }
        //moves character
        if(this.pressed[0]){
            this.avatar.moveLeft();
        }
        if(this.pressed[1]){
            this.avatar.moveRight();
        }
        if(this.pressed[0] && this.pressed[2]){
            avatar.sprintLeft();
        }
        if(this.pressed[1] && this.pressed[2]){
            this.avatar.sprintRight();
        }
        if(this.pressed[2]){
            this.sprintBar.consumeSprint();
            if(this.sprintBar.getSprint() == 0){
                this.pressed[2] = false;
            }
        } else {
            this.sprintBar.rechargeSprint();
        }
    }
    public void runGameLoop() throws Exception{
        while(true){
            if(this.life.alive()){
                this.update();
            } else{
                if(this.endCheck){
                    this.highscore.importScore(menu.getName(), score.getScore());
                    this.itemsOnScreen.clear();
                    this.laserFiring.clear();
                    this.noMoveArea.clear();
                    this.update();
                    this.endCheck = false;
                }
            }
            this.frame.repaint();
            try{Thread.sleep(20);} catch(Exception e){}
        }
    }
    public void setUp(){
        this.frame.setSize(this.frameWidth, this.frameHeight);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.add(this.gameGraphic);
        this.frame.addKeyListener(this.myKeyListener);
        this.menu.createPanel();
    }
}