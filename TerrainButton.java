import java.awt.*;
import javax.swing.*;

class TerrainButton extends JButton {
    // fields
    private int row = 0;
    private int col = 0;
    private boolean isGold;
    private boolean revealed;
    private final int DIMENSION = 40;
    private int points;
    private boolean pointsSet; // keep track of whether or not points are set for button

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

    public boolean arePointsSet() {
        return this.pointsSet;
    }

    // setters
    public void setGold(boolean isGold) {
        this.isGold = isGold;
    }

    public void setPoints(boolean pointsSet) {
        this.pointsSet = true;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = true;

        if(this.revealed == true) {
            if(isGold()) {
                setOpaque(true);
                setBackground(new Color(255, 215, 0));
                setText("" + this.points);
            }
            else {
                setText("" + this.points);
            }
        }
    }

    public void setPoints(int points) {
        this.points = points;
        setPoints(true);
    }
}