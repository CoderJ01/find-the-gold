import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;

class TerrainButton extends JButton {
    // fields
    private int row = 0;
    private int col = 0;
    private boolean isGold;
    private boolean revealed;
    private final int DIMENSION = 40;
    private int points;

    // constructor
    public TerrainButton(int row, int col) {
        this.row = row;
        this.col = col;
        Dimension size = new Dimension(DIMENSION, DIMENSION);
        setPreferredSize(size);
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

    // setter
    public void setGold(boolean isGold) {
        this.isGold = isGold;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = true;

        if(this.revealed == true) {
            if(isGold()) {
                setOpaque(true);
                setBackground(new Color(255, 215, 0));
            }
        }
    }

    public void setPoints(int points) {
        this.points = points;
    }
}