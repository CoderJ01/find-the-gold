import javax.swing.JButton;
import java.awt.Dimension;

class TerrainButton extends JButton {
    // fields
    private int row = 0;
    private int col = 0;
    private boolean isGold;
    private boolean revealed;

    // constructor
    public TerrainButton(int row, int col) {
        this.row = row;
        this.col = col;
        Dimension size = new Dimension(50, 50);
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

    // setter
    public void setButton(boolean isGold) {
        this.isGold = isGold;
    }
}