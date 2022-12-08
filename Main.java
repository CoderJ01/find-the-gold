import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public final class Main extends JFrame {
    private static final int ROW = 15;
    private static final int COL = 20;
    private static TerrainButton[][] terrain = new TerrainButton[ROW][COL];
    private static Random rand = new Random();
    private static List<Player> players = new ArrayList<>();
    private static List<String> colors = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    private static String[] labels = {"blue", "brown", "cream", "cyan", "gray", "green", "magenta", "orange", "purple", "red"};

    public static void main(String[] args) {
        try {
            // modify window appearance
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
        }
        catch(Exception e) {}
        new Main();
    }

    // run game
    public Main() {
        colors();
        addPlayers();
        initializeGrid();
        setGoldButton();
        setButtonPoints();
        gamePlay();
    }

    // colors
    private static void colors() {
        for(int i = 0; i < labels.length; i++) {
            colors.add(labels[i]);
        }
    }

    private static void addPlayers() {
        
        String name = enterName(); // retrieve player's name

        // inquire if player wants to view rules
        if(viewOrNo(name)) {
            gameRules(); // describe rules of game
        }

        // add players
        players.add(new Player(name));

        int numberOfOpponents = opponents(); // retrieve number of opponents
        
        String computerName = "CPU_";
        
        for(int i = 1; i <= numberOfOpponents; i++) {
            players.add(new CPU(computerName + i));
        }

        // have non-CPU player select color
        String color = selectColor();

        players.get(0).setColor(color);

        // have CPU players pick a color
        for(int i = 1; i < players.size(); i++) {
            int pick = rand.nextInt(colors.size());
            players.get(i).setColor(colors.get(pick)); // CPU picks a random color
            colors.remove(colors.get(pick)); // pick is removed to prevent any duplicates
        }

        String playerColors = "";

        for(int i = 0; i < players.size(); i++) {
            playerColors += players.get(i).getPlayerName() + " has selected " + players.get(i).getColor() + "\n";
        }

        JOptionPane.showMessageDialog(null, playerColors); // display each player's color
        System.out.println();
        System.out.println(playerColors);
    }

    // initialize frame for game
    private static void initializeGrid() {
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

    // set the amount of points for each button
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

        // set points for each button
        int p = 0;
        int max = 5;
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

        // set points for remaining buttons
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                if(!terrain[row][col].isPointsSet()) {
                    terrain[row][col].setPoints(-5);
                    terrain[row][col].setPoints(true);
                }
            }
        }
    }

    // have player enter name
    private static String enterName() {
        System.out.print("Enter your name: ");
        String name = input.next();
        return name;
    }

    // allow the player to enter the number of opponents to play against
    private static int opponents() {
        int numberOfOpponents = 0;
        boolean error = false;
        int min = 1, max = 9;

        while(true) {
            // check for appropiate data type
            do {
                error = false;
                try {
                    System.out.print("\nHow many opponents would you like to play against? Enter a number from " + min + " to " + max + ": ");
                    numberOfOpponents = input.nextInt();
                }
                catch(InputMismatchException e) {
                    error = true;
                    input.nextLine();
                }
            } while(error);

            // check if integer is within the specified range
            if(numberOfOpponents >= min && numberOfOpponents <= max) {
                break;
            }
        }
        return numberOfOpponents;
    }

    // have the player select a color
    private static String selectColor() {
        String color = "";

        OUTER: while(true) {
            System.out.println("\nSelect a color: ");
            // display colors
            for(int i = 0; i < colors.size(); i++) {
                System.out.println("- " + colors.get(i));
            }
            
            System.out.print("selection: ");
            color = input.next(); // player's selection

            for(String colorChoice : colors) {
                if(color.equals(colorChoice)){
                    break OUTER;
                }
            }
        }

        // remove color that player picks
        for(int i = 0; i < colors.size(); i++) {
            if(color.equals(colors.get(i))) {
                colors.remove(colors.get(i)); 
            }
        }

        return color;
    }

    // getters (for classes CPU and Player)
    public static int getRowMaximun() {
        return ROW;
    }

    public static int getColMaximun() {
        return COL;
    }

    public static String[] getLabels() {
        return labels;
    }

    // this is the actual game play
    private static void gamePlay() {
        int c = 0;
        int maxPlayerIndex = (players.size() - 1);
        
        // loop through players for turn switching
        while(true) {
            
            // allow players with over zero points to select button
            if(players.get(c).getPoints() > 0) {
                players.get(c).selectButton(terrain, ROW, COL);
            }
            // if player has 0 points 
            else {
                System.out.println(players.get(c).getPlayerName() + " is OUT!"); // display message that player is out
                players.remove(players.get(c)); // remove player from the game
                
                // change indexes for proper accommodation (i.e. prevent IndexOutOfBoundsException)
                maxPlayerIndex--;
                c--;
            } 
            
            // increment
            if(c < maxPlayerIndex) {
                c++;
            }
            else if(c == maxPlayerIndex) {
                c = 0;
            }
        }
    }

    // describe the rules if the game
    private static void gameRules() {
        System.out.println("\nRULES");
        System.out.println("*****");
        System.out.println("The objective of this game is to be the first player to find the gold button. All players start out");
        System.out.println("with 5000 points. Everytime a player selects a button that is not the gold button, the player will lose");
        System.out.println("points. The amount of points lost depends on the particular button. If a player loses all 5000 points, the");
        System.out.println("player is out of the game.");

        // confirm that player understands the rules
        String confirmation = "";
        while(true) {
            System.out.print("\nEnter 'y' to confirm that you understand the rules: ");
            confirmation = input.next();
            if(confirmation.equals("y")) {
                break;
            }
        }
    }

    // ask if player would like to see the rules
    private static boolean viewOrNo(String name) {
        String view = "";
        System.out.print("\nHello " + name + ", would you like to view the rules of the game? If you would, enter 'y'. If not, enter any other key: ");
        view = input.next();
        if(view.equals("y")) {
            return true;
        }
        else {
            return false;
        }
    }
}