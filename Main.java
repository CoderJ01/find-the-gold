import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;

public class Main extends JFrame {
    private static final int ROW = 15;
    private static final int COL = 30;
    private TerrainButton[][] terrain = new TerrainButton[ROW][COL];
    private Random rand = new Random();

    public static void main(String[] args) {
        try {
            // modify window appearance
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
        }
        catch(Exception e) {}
        new Main();
    }

    public Main() {
        initialize();
        setGoldButton();
    }

    // initialize frame for game
    private void initialize() {
        JFrame frame = new JFrame();
        frame.setTitle("Find the Gold");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
        frame.setResizable(false); // disable frame from being resized

        JPanel panel = new JPanel(new GridLayout(ROW, COL));

        // add buttons to panel
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                terrain[row][col] = new TerrainButton(row, col);
                panel.add(terrain[row][col]);
            }
        }
        
        frame.add(panel); // add panel to frame

        frame.pack(); // size the frame so that all its contents are at or above their preferred sizes (i.e. make frame appear)
        frame.setVisible(true); // make frame visible
    }

    // set one of the buttons to be gold
    private void setGoldButton() {
       int row = rand.nextInt(ROW);
       int col = rand.nextInt(COL);
       terrain[row][col].setGold(true); 
       Color gold = new Color(255, 215, 0);
       terrain[row][col].setOpaque(true);
       terrain[row][col].setBackground(gold);
    }
}