import java.awt.*;
import javax.swing.*;

public final class TerrainButton extends JButton {
    // fields
    private int row = 0;
    private int col = 0;
    private boolean isGold;
    private boolean revealed;
    private final int DIMENSION = 40;
    private int points;
    private boolean pointsSet; // keep track of whether or not points are set for button
    private boolean colorSet; // keep track of whether or not the color has been set for the bottun

    // constructor
    public TerrainButton(int row, int col) {
        this.row = row;
        this.col = col;
        Dimension size = new Dimension(DIMENSION, DIMENSION);
        setPreferredSize(size);
        setMargin(new Insets(0, 0, 0, 0)); // make three dots disappear for points display
    }

    // getters
    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean isRevealed() {
        return this.revealed;
    }

    public boolean isGold() {
        return this.isGold;
    }

    public int getPoints() {
        return this.points;
    }

    public boolean isPointsSet() {
        return this.pointsSet;
    }

    public boolean isColorSet() {
        return this.colorSet;
    }

    // setters
    public void setGold(boolean isGold) {
        this.isGold = isGold;
    }

    public void setPoints(boolean pointsSet) {
        this.pointsSet = true;
    }

    private void setColor() {
        this.colorSet = true;
    }

    public void setRevealed(boolean revealed, Color[] colors) {
        this.revealed = true;

        if(this.revealed == true) {
            setOpaque(true);
            if(isGold()) {
                setBackground(new Color(255, 215, 0));
            }
            else {
                if(!isColorSet()) {
                    setForeground(colors[1]); // set color of text
                    setBackground(colors[0]); // set color of button
                    setColor(); // prevent non-CPU player from changing color of button selected by CPU player
                }
            }
            setText("" + this.points);
        }
    }

    public void setPoints(int points) {
        this.points = points;
        setPoints(true);
    }
}