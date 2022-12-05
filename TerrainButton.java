import javax.swing.JButton;
import java.awt.Dimension;

class TerrainButton extends JButton {
    // fields
    private int row = 0;
    private int col = 0;
    private boolean isGold;
    private boolean revealed;
    private final int DIMENSION = 40;

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

    public boolean getGold() {
        return this.isGold;
    }

    // setter
    public void setButton(boolean isGold) {
        this.isGold = isGold;
    }
}