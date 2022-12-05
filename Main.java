import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    private static final int GRIDSIZE = 10;
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

        JPanel panel = new JPanel(new GridLayout(4, 5, 10, 10));

        for(int i = 1; i <= 20; i++) {
            JButton button = new JButton("Button" + Integer.toString(i));
            panel.add(button);
        }
        
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}