import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Player {
    // fields
    private final String PLAYER_NAME;
    private int points;
    private String color;
    private int turn = 0;

    // constructor
    public Player(String name) {
        PLAYER_NAME = name;
        this.points = 5000;
    }

    // getters
    public String getPlayerName() {
        return PLAYER_NAME;
    }

    public int getPoints() {
        return this.points;
    }

    public String getColor() {
        return this.color;
    }

    public int getTurn() {
        return this.turn;
    }

    // setter
    public void setColor(String color) {
        this.color = color;
    }

    public void setTurn() {
        this.turn = 1;
    }

    // allow player to select button
    public void selectButton(TerrainButton[][] terrain, int row, int col) {   
        terrain[row][col].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getTurn() == 0) {
                    TerrainButton button = (TerrainButton) e.getSource();
                    int row = button.getRow();
                    int col = button.getCol();
                    terrain[row][col].setRevealed(true, colorObject());
                    setTurn();
                    System.out.println(getTurn());
                }
            }
        }); 
    }

    private Color[] colorObject() {
        Color[] colors = new Color[2];
        if(this.color == "blue") {
            colors[0] = new Color(0, 0, 225);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color == "brown") {
            colors[0] = new Color(100, 38, 14);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color == "gray") {
            colors[0] = new Color(128, 128, 128);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color == "green") {
            colors[0] = new Color(0, 128, 0);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color == "orange") {
            colors[0] = new Color(255, 95, 31);
            colors[1] = new Color(0, 0, 0);
        }
        else if(this.color == "purple") {
            colors[0] = new Color(128, 0, 128);
            colors[1] = new Color(255, 255, 225);
        }
        else if(this.color == "red") {
            colors[0] = new Color(255, 0, 0);
            colors[1] = new Color(0, 0, 0);
        }
        else if(this.color == "yellow") {
            colors[0] = new Color(255, 255, 0);
            colors[1] = new Color(0, 0, 0);
        }
        return colors;
    }
}