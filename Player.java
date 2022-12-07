import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.Color;

public class Player {
    // fields
    private final String PLAYER_NAME;
    private int points;
    private String color;
    private int turn = 0;

    // constructor
    public Player(String name) {
        PLAYER_NAME = name;
        this.points = 5000;
    }

    // getters
    public final String getPlayerName() {
        return PLAYER_NAME;
    }

    public final int getPoints() {
        return this.points;
    }

    public final String getColor() {
        return this.color;
    }

    // setter
    public final void setColor(String color) {
        this.color = color;
    }

    // allow player to select button
    public void selectButton(TerrainButton[][] terrain, int rowMax, int colMax) {   
        for(int row = 0; row < rowMax; row++) {
            for(int col = 0; col < colMax; col++) {
                terrain[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            TerrainButton button = (TerrainButton) e.getSource();
                            int row = button.getRow();
                            int col = button.getCol();
                            keepScore(terrain, row, col);
                            terrain[row][col].setRevealed(true, colorObject());
                            if(terrain[row][col].isGold()) {
                                gameEnd(terrain, row, col);
                            }
                        }
                }); 
            }
        }
    }

    protected final Color[] colorObject() {
        String[] labels = Main.getLabels();
        Color[] colors = new Color[2];
        
        // blue
        if(this.color.equals(labels[0])) {
            colors[0] = new Color(0, 0, 225);
            colors[1] = new Color(255, 255, 225);
        }
        // brown
        else if(this.color.equals(labels[1])) {
            colors[0] = new Color(100, 38, 14);
            colors[1] = new Color(255, 255, 225);
        }
        // cream
        else if(this.color.equals(labels[2])) {
            colors[0] = new Color(255, 250, 160);
            colors[1] = new Color(0, 0, 0);
        }
        // cyan
        else if(this.color.equals(labels[3])) {
            colors[0] = new Color(0, 255, 255);
            colors[1] = new Color(0, 0, 0);
        }
        // gray
        else if(this.color.equals(labels[4])) {
            colors[0] = new Color(128, 128, 128);
            colors[1] = new Color(255, 255, 225);
        }
        // green
        else if(this.color.equals(labels[5])) {
            colors[0] = new Color(0, 128, 0);
            colors[1] = new Color(255, 255, 225);
        }
        // magenta
        else if(this.color.equals(labels[6])) {
            colors[0] = new Color(255, 0, 143);
            colors[1] = new Color(0, 0, 0);
        }
        // orange
        else if(this.color.equals(labels[7])) {
            colors[0] = new Color(255, 95, 31);
            colors[1] = new Color(0, 0, 0);
        }
        // purple
        else if(this.color.equals(labels[8])) {
            colors[0] = new Color(128, 0, 128);
            colors[1] = new Color(255, 255, 225);
        }
        // red
        else if(this.color.equals(labels[9])) {
            colors[0] = new Color(255, 0, 0);
            colors[1] = new Color(0, 0, 0);
        }
        return colors;
    }

    // allow players score to be kept
    protected final void keepScore(TerrainButton[][] terrain, int row, int col) {
        if(!terrain[row][col].isRevealed()) {
            this.points += terrain[row][col].getPoints();
        }
        if(this.points <= 0) {
            this.points = 0;
        }
        System.out.println(this.PLAYER_NAME + " has " + this.points + " points remaining");
    }

    // end the game when the player finds the gold
    protected final void gameEnd(TerrainButton[][] terrain, int row, int col) {
        if(terrain[row][col].isGold()) {
            String message = this.PLAYER_NAME + " has found the gold. The game is over.";
            JOptionPane.showMessageDialog(null, message);
            System.exit(0);
        }
    }
}