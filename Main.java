import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    private static final int GRIDSIZE = 10;
    private static final int ROW = 15;
    private static final int COL = 30;
    private TerrainButton[][] terrain = new TerrainButton[GRIDSIZE][GRIDSIZE];

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        initialize();
    }

    private void initialize() {
        JFrame frame = new JFrame();
        frame.setTitle("GridLayout");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(ROW, COL));

        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                JButton button = new TerrainButton(row, col);
                panel.add(button);
            }
        }
        
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}