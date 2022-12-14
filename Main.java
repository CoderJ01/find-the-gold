import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;

public final class Main extends JFrame {
    private static final int ROW = 15;
    private static final int COL = 20;
    private static TerrainButton[][] terrain = new TerrainButton[ROW][COL];
    private static Random rand = new Random();
    private static List<Player> players = new ArrayList<>();
    private static List<String> colors = new ArrayList<>(); // use ArrayList to access remove method
    private static Scanner input = new Scanner(System.in);
    private static Object[][] colorAssortment = establishColors();
    private static final String ALL_PLAYERS_LOSE = "All players lose the game!";
    private static int difficulty;

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
        for(int i = 0; i < colorAssortment.length; i++) {
            colors.add((String)colorAssortment[i][0]);
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

        difficulty = selectDifficulty();

        String playerColors = "";

        // display each player's color
        for(int i = 0; i < players.size(); i++) {
            playerColors += players.get(i).getPlayerName() + " has selected " + players.get(i).getColor() + "\n";
        }

        JOptionPane.showMessageDialog(null, "Press OK to confirm that you are ready to play the game."); 
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
        // set the points for the gold button
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

        // set points for the non-gold buttons
        int p = 0;
        int max = difficulty;
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
            System.out.println("\nSelect a color");
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

    public static Object[][] getColors() {
        return colorAssortment;
    }

    public static String getEndMessage() {
        return ALL_PLAYERS_LOSE;
    }

    // this is the actual gameplay
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
                players.remove(players.get(c)); // remove player from the game
                
                // change indexes for proper accommodation (i.e. prevent IndexOutOfBoundsException)
                maxPlayerIndex--;
                c--;
            } 

            // prevent further reponses from buttons if gold button is found
            if(goldFound() || players.size() == 0) {
                break;
            }

            // gameplay for non-CPU player will be handled outside while loop to prevent infinite display of points
            if(players.size() == 1 && !(players.get(0) instanceof CPU)) {
                break;
            }

            // increment
            if(c < maxPlayerIndex) {
                c++;
            }
            else if(c == maxPlayerIndex) {
                c = 0;
            }
        }

        /* If the game were to continue inside the while loop, then the points display for the non-CPU player would loop infinitely.
           Using fields and local variables in class Player to circumvent the issue is not feasable due to the particular manner this
           codebase is set up. The game will continue as it always has.
        */ 
        if(players.size() == 1 && !(players.get(0) instanceof CPU)) {
            players.get(0).setLastPlayer(true);
            for(int row = 0; row < ROW; row++) {
                for(int col = 0; col < COL; col++) {
                    terrain[row][col].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if(players.get(0).getPoints() > 0) {
                                TerrainButton button = (TerrainButton) e.getSource();
                                int row = button.getRow();
                                int col = button.getCol();
                                // display score only if button has not been selected
                                if(!terrain[row][col].isRevealed()) {
                                    players.get(0).keepScore(terrain, row, col);
                                }
                                terrain[row][col].setRevealed(true, players.get(0).colorObject());
                                if(terrain[row][col].isGold()) {
                                    players.get(0).gameEnd(terrain, row, col);
                                    System.exit(0);
                                }
                                players.get(0).endTurn();
                            }
                        }
                    }); 
                }
            }
        }
        
        // display if all players lose all points
        if(players.size() == 0) {
            JOptionPane.showMessageDialog(null, ALL_PLAYERS_LOSE);
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

    // check if gold button is found
    private static boolean goldFound() {
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                if(terrain[row][col].isGold() && terrain[row][col].isRevealed()) {
                    return true;
                }
            }
        }
        return false;
    }

    // prompt player to choose the difficulty of the grid
    private static int selectDifficulty() {
        String difficulty = "";
        int max = 0;

        while(true) {
            System.out.println("\nSelect a grid difficulty");
            System.out.println("1 - very easy");
            System.out.println("2 - easy");
            System.out.println("3 - medium");
            System.out.println("4 - hard");
            System.out.println("5 - very hard");
            System.out.print("selection: ");
            difficulty = input.next();  

            if(difficulty.equals("1")) {
                max = 0;
                break;
            }
            else if(difficulty.equals("2")) {
                max = 3;
                break;
            }
            else if(difficulty.equals("3")) {
                max = 8;
                break;
            }
            else if(difficulty.equals("4")) {
                max = 12;
                break;
            }
            else if(difficulty.equals("5")) {
                max = 15;
                break;
            }    
        } 
        
        return max;
    }

    // set up the colors that will be available for the game
    private static Object[][] establishColors() {
        Color whiteText = new Color(255, 255, 225);
        Color blackText = new Color(0, 0, 0);
        Object[][] colors = 
        {
            {"blue", new Color(0, 0, 225), whiteText},
            {"brown", new Color(100, 38, 14), whiteText},
            {"cream", new Color(255, 250, 160), blackText},
            {"cyan", new Color(0, 255, 255), blackText},
            {"gray", new Color(128, 128, 128), whiteText},
            {"green", new Color(0, 128, 0), whiteText},
            {"magenta", new Color(255, 0, 143), blackText},
            {"mint", new Color(152, 251, 152), blackText},
            {"orange", new Color(255, 95, 31), blackText},
            {"pastel", new Color(195, 177, 225), blackText},
            {"purple", new Color(128, 0, 128), whiteText},
            {"red",  new Color(255, 0, 0), blackText},
        };
        return colors;
    }
}