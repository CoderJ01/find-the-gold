import javax.swing.JButton;
import java.awt.Dimension;

class TerrainButton extends JButton {
    private int row = 0;
    private int col = 0;
    private boolean isGold;
    private boolean revealed;

    public TerrainButton(int row, int col) {
        this.row = row;
        this.col = col;
        Dimension size = new Dimension(50, 50);
        setPreferredSize(size);
    }
}