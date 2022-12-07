import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.Color;

public class Player {
    // fields
    private final String PLAYER_NAME;
    private int points;
    private String color;
    // private int turn = 0;

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

    // public int getTurn() {
    //     return this.turn;
    // }

    // setter
    public final void setColor(String color) {
        this.color = color;
    }

    // public void setTurn() {
    //     this.turn = 1;
    // }

    // allow player to select button
    public void selectButton(TerrainButton[][] terrain, int row, int col) {   
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

    protected final Color[] colorObject() {
        Color[] colors = new Color[2];
        if(this.color.equals("blue")) {
            colors[0] = new Color(0, 0, 225);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color.equals("brown")) {
            colors[0] = new Color(100, 38, 14);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color.equals("cream")) {
            colors[0] = new Color(255, 250, 160);
            colors[1] = new Color(0, 0, 0);
        }
        else if(this.color.equals("cyan")) {
            colors[0] = new Color(0, 255, 255);
            colors[1] = new Color(0, 0, 0);
        }
        else if(this.color.equals("gray")) {
            colors[0] = new Color(128, 128, 128);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color.equals("green")) {
            colors[0] = new Color(0, 128, 0);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color.equals("magenta")) {
            colors[0] = new Color(255, 0, 143);
            colors[1] = new Color(0, 0, 0);
        }
        else if(this.color.equals("orange")) {
            colors[0] = new Color(255, 95, 31);
            colors[1] = new Color(0, 0, 0);
        }
        else if(this.color.equals("purple")) {
            colors[0] = new Color(128, 0, 128);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color.equals("red")) {
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