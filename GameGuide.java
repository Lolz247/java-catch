import java.awt.*;

public class GameGuide implements Draw{
    private String fontName;
    private int largeFontSize;
    private int smallFontSize;
    
    GameGuide(){
        this.fontName = GameConst.FONT;
        this.largeFontSize = GameConst.LARGE_FONT_SIZE;
        this.smallFontSize = GameConst.SMALL_FONT_SIZE;
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(200, 100, 500, 500);
        g.setColor(Color.white);
        g.fillRect(202, 102, 496, 496);
        g.setColor(Color.black);
        g.fillRect(212, 112, 476, 476);
        
        g.setColor(Color.white);
        Font headerFont = new Font(this.fontName, Font.BOLD, this.largeFontSize);
        g.setFont(headerFont);
        g.drawString("HOW TO PLAY", 315, 150);
        Font disclaimerFont = new Font(this.fontName, Font.ITALIC, this.smallFontSize);
        g.setFont(disclaimerFont);
        g.drawString("In dedication to", 240, 180);
        g.drawString("my final Gr11 compsci project...", 315, 200);
        Font textFont = new Font(this.fontName, Font.PLAIN, this.smallFontSize);
        g.setFont(textFont);
        g.drawString("Use [LEFT] and [RIGHT] arrow keys to move.", 223, 250);
        g.drawString("Use [SHIFT] to sprint,", 325, 275);
        g.drawString("but watch your sprint meter", 300, 300);
        g.drawString("|  Give Points  |  Take Points  |", 270, 340);
        g.drawString("|  Take Damage  | Side Movement |", 270, 400);
        g.drawString("Lasers hurt! Heed the warning...", 310, 470);
        g.drawString("Don't move when you're inside!", 310, 520);

        g.setColor(Color.WHITE);
        g.fillOval(325, 350, GameConst.DEF_ITEM_SIZE, GameConst.DEF_ITEM_SIZE);
        g.setColor(Color.YELLOW);
        g.fillOval(375, 350, GameConst.DEF_ITEM_SIZE, GameConst.DEF_ITEM_SIZE);
        g.fillOval(500, 410, GameConst.DEF_ITEM_SIZE, GameConst.DEF_ITEM_SIZE);
        g.setColor(Color.RED);
        g.fillOval(500, 350, GameConst.DEF_ITEM_SIZE, GameConst.DEF_ITEM_SIZE);
        g.fillOval(350, 410, GameConst.DEF_ITEM_SIZE, GameConst.DEF_ITEM_SIZE);
        g.setColor(Color.MAGENTA);
        g.fillOval(550, 350, GameConst.DEF_ITEM_SIZE, GameConst.DEF_ITEM_SIZE);
        g.fillOval(550, 410, GameConst.DEF_ITEM_SIZE, GameConst.DEF_ITEM_SIZE);

        g.setColor(Color.RED);
        g.fillRect(250, 445, 40, 40);
        g.setColor(Color.WHITE);
        g.fillRect(255, 447, 30, 36);
        g.setColor(GameConst.LIGHT_BLUE);
        g.fillRect(250, 495, 40, 40);
    }
}