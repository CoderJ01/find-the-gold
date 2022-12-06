public class Player {
    // fields
    private final String PLAYER_NAME;
    private int points;
    private String color;

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

    // setter
    public void setColor(String color) {
        this.color = color;
    }

    // allow player to select button
    public void selectButton(TerrainButton[][] terrain, int row, int col) {

    }
}