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

    private Color colorObject() {
        Color color = null;
        if(this.color == "blue") {
            color = new Color(0, 0, 225);
        }
        else if(this.color == "brown") {
            color = new Color(100, 38, 14);
        }
        else if(this.color == "gray") {
            color = new Color(128, 128, 128);
        }
        else if(this.color == "green") {
            color = new Color(0, 128, 0);
        }
        else if(this.color == "orange") {
            color = new Color(255, 95, 31);
        }
        else if(this.color == "purple") {
            color = new Color(128, 0, 128);
        }
        else if(this.color == "red") {
            color = new Color(255, 0, 0);
        }
        else if(this.color == "yellow") {
            color = new Color(255, 255, 0);
        }
        return color;
    }
}