import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.Color;

public class Player {
    // fields
    private final String PLAYER_NAME;
    private int points;
    private String color;
    private boolean turnOver;
    private boolean lastPlayer;

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

    protected final boolean turnOver() {
        return this.turnOver;
    }

    protected final boolean isLastPlayer() {
        return this.lastPlayer;
    }

    // setters
    public final void setColor(String color) {
        this.color = color;
    }

    protected final void startTurn() {
        this.turnOver = false;
    }

    protected final void endTurn() {
        this.turnOver = true;
    }

    public final void setLastPlayer(boolean isLastPlayer) {
        this.lastPlayer = isLastPlayer;
    }

    // allow player to select button
    public void selectButton(TerrainButton[][] terrain, int rowMax, int colMax) {   
        startTurn();
        for(int row = 0; row < rowMax; row++) {
            for(int col = 0; col < colMax; col++) {
                if(!turnOver()) {
                    terrain[row][col].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // allow action performance only if player has more than 0 points
                            if(getPoints() > 0 && !turnOver()) {
                                TerrainButton button = (TerrainButton) e.getSource();
                                int row = button.getRow();
                                int col = button.getCol();
                                keepScore(terrain, row, col);
                                terrain[row][col].setRevealed(true, colorObject());
                                if(terrain[row][col].isGold()) {
                                    gameEnd(terrain, row, col);
                                }
                                endTurn();
                            }
                        }
                    }); 
                }
            }
        }
    }
    
    public final Color[] colorObject() {
        String[] labels = Main.getLabels();
        Color[] colors = new Color[2];
        int i = 0; // increment to optimize addition of new colors in alphabetized list
        
        // blue
        if(this.color.equals(labels[i])) {
            colors[0] = new Color(0, 0, 225); // button color (e.g. blue)
            colors[1] = new Color(255, 255, 225); // text color (either black or white)
        }
        // brown
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(100, 38, 14);
            colors[1] = new Color(255, 255, 225); // white
        }
        // cream
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(255, 250, 160);
            colors[1] = new Color(0, 0, 0); // black
        }
        // cyan
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(0, 255, 255);
            colors[1] = new Color(0, 0, 0);
        }
        // gray
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(128, 128, 128);
            colors[1] = new Color(255, 255, 225);
        }
        // green
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(0, 128, 0);
            colors[1] = new Color(255, 255, 225);
        }
        // magenta
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(255, 0, 143);
            colors[1] = new Color(0, 0, 0);
        }
        // mint
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(152, 251, 152);
            colors[1] = new Color(0, 0, 0);
        }
        // orange
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(255, 95, 31);
            colors[1] = new Color(0, 0, 0);
        }
        // pastel
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(195, 177, 225);
            colors[1] = new Color(0, 0, 0);
        }
        // purple
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(128, 0, 128);
            colors[1] = new Color(255, 255, 225);
        }
        // red
        else if(this.color.equals(labels[++i])) {
            colors[0] = new Color(255, 0, 0);
            colors[1] = new Color(0, 0, 0);
        }
        return colors;
    }

    // allow player's score to be kept
    protected final void keepScore(TerrainButton[][] terrain, int row, int col) {
        if(!terrain[row][col].isRevealed()) {
            this.points += terrain[row][col].getPoints();
        }
        if(this.points > 0) {
            System.out.println(PLAYER_NAME + " has " + this.points + " points remaining");
        } 
        else {
            this.points = 0;
            System.out.println(PLAYER_NAME + " is OUT!"); // display message that player is out

            /* display message only if non-CPU player is the last player in the game
               this covers for the identical JOptionPane in the gamePlay() method of Main.java, since it will
               not be reached if the non-CPU player is the last player in the game
            */
            if(this.lastPlayer) {
                JOptionPane.showMessageDialog(null, Main.getEndMessage());
            }
        }
    }

    // end the game when the player finds the gold
    protected final void gameEnd(TerrainButton[][] terrain, int row, int col) {
        if(terrain[row][col].isGold()) {
            String message = PLAYER_NAME + " has found the gold! " + PLAYER_NAME + " wins the game!";
            JOptionPane.showMessageDialog(null, message);
            System.exit(0);
        }
    }
}