import java.awt.*;
import java.lang.Character;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

public class HighscoreBoard implements Draw{
    //highscores + names
    private int score1;
    private int score2;
    private int score3;
    private int score4;
    private int score5;
    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private String name5;
    
    private int fontSize;
    private String fontName;
    private int highscoreBarX;
    private int highscoreBarY;
    private int base;
    private int height;
    private boolean checkHighscore; //checks if there is a new highscore
    
    private File highscores;
    private PrintWriter output;
    private Scanner input;
    
    public HighscoreBoard() throws Exception{
        this.highscores = new File("gameHighscore.txt");
        this.input = new Scanner(this.highscores);
        
        this.score1 = 0;
        this.score2 = 0;
        this.score3 = 0;
        this.score4 = 0;
        this.score5 = 0;
        
        this.name1 = "";
        this.name2 = "";
        this.name3 = "";
        this.name4 = "";
        this.name5 = "";
        
        String scoreString1 = this.input.nextLine();
        String scoreString2 = this.input.nextLine();
        String scoreString3 = this.input.nextLine();
        String scoreString4 = this.input.nextLine();
        String scoreString5 = this.input.nextLine();
        //separates scoreString into name and score
        int initialStart;
        initialStart = -1;
        for(int i=scoreString1.length()-1;i>=0;i--){
            if(!(scoreString1.charAt(i)==' ') && initialStart==-1){
                this.score1+=(Character.getNumericValue(scoreString1.charAt(i))*((int)(Math.pow(10,scoreString1.length()-i-1))));
            } else {
                initialStart = i;
                if(scoreString1.charAt(i)==' '){
                    i--;
                }
                this.name1 = Character.toString(scoreString1.charAt(i)) + this.name1;
            }
        }
        initialStart = -1;
        for(int i=scoreString2.length()-1;i>=0;i--){
            if(!(scoreString2.charAt(i)==' ') && initialStart==-1){
                this.score2+=(Character.getNumericValue(scoreString2.charAt(i))*((int)(Math.pow(10,scoreString2.length()-i-1))));
            } else {
                initialStart = i;
                if(scoreString2.charAt(i)==' '){
                    i--;
                }
                this.name2 = Character.toString(scoreString2.charAt(i)) + this.name2;
            }
        }
        initialStart = -1;
        for(int i=scoreString3.length()-1;i>=0;i--){
            if(!(scoreString3.charAt(i)==' ') && initialStart==-1){
                this.score3+=(Character.getNumericValue(scoreString3.charAt(i))*((int)(Math.pow(10,scoreString3.length()-i-1))));
            } else {
                initialStart = i;
                if(scoreString3.charAt(i)==' '){
                    i--;
                }
                this.name3 = Character.toString(scoreString3.charAt(i)) + this.name3;
            }
        }
        initialStart = -1;
        for(int i=scoreString4.length()-1;i>=0;i--){
            if(!(scoreString4.charAt(i)==' ') && initialStart==-1){
                this.score4+=(Character.getNumericValue(scoreString4.charAt(i))*((int)(Math.pow(10,scoreString4.length()-i-1))));
            } else {
                initialStart = i;
                if(scoreString4.charAt(i)==' '){
                    i--;
                }
                this.name4 = Character.toString(scoreString4.charAt(i)) + this.name4;
            }
        }
        initialStart = -1;
        for(int i=scoreString5.length()-1;i>=0;i--){
            if(!(scoreString5.charAt(i)==' ') && initialStart==-1){
                this.score5+=(Character.getNumericValue(scoreString5.charAt(i))*((int)(Math.pow(10,scoreString5.length()-i-1))));
            } else {
                initialStart = i;
                if(scoreString5.charAt(i)==' '){
                    i--;
                }
                this.name5 = Character.toString(scoreString5.charAt(i)) + this.name5;
            }
        }
        
        this.fontSize = GameConst.NORMAL_FONT_SIZE;
        this.fontName = GameConst.FONT;
        this.highscoreBarX = GameConst.LEFT_DISPLAY_SETX;
        this.highscoreBarY = GameConst.HIGHSCORE_BAR_SETY;
        this.base = GameConst.DEF_DISPLAY_BASE;
        this.height = GameConst.HS_DISPLAY_HEIGHT;
    }
    public boolean getCheckHighscore(){
        return this.checkHighscore;
    }
    public void printScores() throws Exception{
        this.output = new PrintWriter(this.highscores);
        this.output.println(this.name1 + " " + this.score1);
        this.output.println(this.name2 + " " + this.score2);
        this.output.println(this.name3 + " " + this.score3);
        this.output.println(this.name4 + " " + this.score4);
        this.output.println(this.name5 + " " + this.score5);
        this.output.close();
    }
    public void importScore(String newName, int newScore) throws Exception{
        if(newScore > this.score1){
            this.score5 = this.score4;
            this.name5 = this.name4;
            this.score4 = this.score3;
            this.name4 = this.name3;
            this.score3 = this.score2;
            this.name3 = this.name2;
            this.score2 = this.score1;
            this.name2 = this.name1;
            this.score1 = newScore;
            this.name1 = newName;
            printScores();
            this.checkHighscore = true;
        } else if(newScore > this.score2){
            this.score5 = this.score4;
            this.name5 = this.name4;
            this.score4 = this.score3;
            this.name4 = this.name3;
            this.score3 = this.score2;
            this.name3 = this.name2;
            this.score2 = newScore;
            this.name2 = newName;
            printScores();
            this.checkHighscore = true;
        } else if(newScore > this.score3){
            this.score5 = this.score4;
            this.name5 = this.name4;
            this.score4 = this.score3;
            this.name4 = this.name3;
            this.score3 = newScore;
            this.name3 = newName;
            printScores();
            this.checkHighscore = true;
        } else if(newScore > this.score4){
            score5 = this.score4;
            this.name5 = this.name4;
            this.score4 = newScore;
            this.name4 = newName;
            printScores();
            this.checkHighscore = true;
        } else if(newScore > this.score5){
            this.score5 = newScore;
            this.name5 = newName;
            printScores();
            this.checkHighscore = true;
        } else {
            this.checkHighscore = false;
        }
    }
    public void draw(Graphics g) {            
        g.setColor(Color.white);
        g.fillRect(this.highscoreBarX, this.highscoreBarY, this.base, this.height);
        g.setColor(Color.black);
        g.fillRect(this.highscoreBarX + 6, this.highscoreBarY + 6, this.base - 12, this.height - 12);
        g.setColor(Color.white);
        
        Font textFont = new Font(this.fontName, Font.PLAIN, this.fontSize);
        g.setFont(textFont);
        g.drawString(this.name1 + " " + this.score1, this.highscoreBarX + 15, this.highscoreBarY + 40);
        g.drawString(this.name2 + " " + this.score2, this.highscoreBarX + 15, this.highscoreBarY + 72);
        g.drawString(this.name3 + " " + this.score3, this.highscoreBarX + 15, this.highscoreBarY + 104);
        g.drawString(this.name4 + " " + this.score4, this.highscoreBarX + 15, this.highscoreBarY + 136);
        g.drawString(this.name5 + " " + this.score5, this.highscoreBarX + 15, this.highscoreBarY + 168);
        g.drawString("HIGHSCORE", this.highscoreBarX + 10, this.highscoreBarY - 5);
    }
}