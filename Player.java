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

                                // show points for non-CPU player only if button is not revealed
                                if(!terrain[row][col].isRevealed()) {
                                    keepScore(terrain, row, col);
                                }

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
    
    // determine the player's color based on selection
    public final Color[] colorObject() {
        Object[][] colors = Main.getColors();
        Color[] pickedColor = new Color[2];

        for(int i = 0; i < colors.length; i++) {
            if(this.color.equals((String) colors[i][0])) {
                pickedColor[0] = (Color) colors[i][1];
                pickedColor[1] = (Color) colors[i][2];
            }
        }
        return pickedColor;
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