import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    // private static final int GRIDSIZE = 10; 
    private static final int ROW = 15;
    private static final int COL = 30;
    // private TerrainButton[][] terrain = new TerrainButton[GRIDSIZE][GRIDSIZE];

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        initialize();
    }

    private void initialize() {
        JFrame frame = new JFrame();
        frame.setTitle("GridLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
        frame.setResizable(false); // disable frame from being resized

        JPanel panel = new JPanel(new GridLayout(ROW, COL));

        // add buttons to panel
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                JButton button = new TerrainButton(row, col);
                panel.add(button);
            }
        }
        
        frame.add(panel); // add panel to frame

        frame.pack(); // size the frame so that all its contents are at or above their preferred sizes (i.e. make frame appear)
        frame.setVisible(true); // make frame visible
    }
}