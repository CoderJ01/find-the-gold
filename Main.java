import java.awt.GridLayout;

import javax.swing.JFrame;
import java.awt.*;
 import java.applet.Applet;

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
        
    }
}