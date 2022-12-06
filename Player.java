public class Player {
    // fields
    private final String PLAYER_NAME;
    private int points;

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
}