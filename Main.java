import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public final class Main extends JFrame {
    private static final int ROW = 15;
    private static final int COL = 30;
    private static TerrainButton[][] terrain = new TerrainButton[ROW][COL];
    private static Random rand = new Random();
    private static List<Player> players = new ArrayList<>();
    private static List<String> colors = new ArrayList<>();

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
        colors();
        addPlayers();
        initialize();
        setGoldButton();
        setButtonPoints();
    }

    // colors
    private static void colors() {
        colors.add("blue");
        colors.add("brown");
        colors.add("gray");
        colors.add("green");
        colors.add("orange");
        colors.add("purple");
        colors.add("red");
        colors.add("yellow");
    }

    private static void addPlayers() {
        players.add(new Player("Joshua"));
        players.add(new Player("Kingston"));

        players.get(0).setColor("blue");
        players.get(0).setColor("green");
    }

    // initialize frame for game
    private static void initialize() {
        JFrame frame = new JFrame();
        frame.setTitle("Find the Gold");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
        frame.setResizable(false); // disable frame from being resized

        JPanel panel = new JPanel(new GridLayout(ROW, COL));

        // add buttons to panel
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                terrain[row][col] = new TerrainButton(row, col);

                panel.add(terrain[row][col]); // add button

                players.get(0).selectButton(terrain, row, col);
            }
        }
        
        frame.add(panel); // add panel to frame

        frame.pack(); // size the frame so that all its contents are at or above their preferred sizes (i.e. make frame appear)
        frame.setVisible(true); // make frame visible
    }

    // set one of the buttons to be gold
    private static void setGoldButton() {
       int row = rand.nextInt(ROW);
       int col = rand.nextInt(COL);
       terrain[row][col].setGold(true); 
    }

    // set the amount of poinst for each button
    private static void setButtonPoints() {
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                if(terrain[row][col].isGold()) {
                    terrain[row][col].setPoints(5000); // gold button will be 5000 points
                    terrain[row][col].setPoints(true);
                }
            }
        }

        int[] points = new int[13];
        points[0] = -5000;
        points[1] = -2500;
        points[2] = -1250;
        points[3] = -1000;
        points[4] = -500;
        points[5] = -250;
        points[6] = -200;
        points[7] = -125;
        points[8] = -100;
        points[9] = -50;
        points[10] = -25;
        points[11] = -20;
        points[12] = -10;

        int p = 0;
        int max = 8;

        // sets point for each button
        while(p < points.length) {
            for(int i = 0; i < max; i++) {
                int randRow = rand.nextInt(ROW);
                int randCol = rand.nextInt(COL);
                // set points only if points have yet to be set
                if(!terrain[randRow][randCol].isPointsSet()) {
                    terrain[randRow][randCol].setPoints(points[p]);
                    terrain[randRow][randCol].setPoints(true);
                }
            }
            p++;
        }

        // set remaining buttons
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                if(!terrain[row][col].isPointsSet()) {
                    terrain[row][col].setPoints(-5);
                    terrain[row][col].setPoints(true);
                }
            }
        }
    }
}