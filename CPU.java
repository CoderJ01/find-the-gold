import java.util.Random;

public final class CPU extends Player {
    private Random rand = new Random(); // field
    
    // constructor
    public CPU(String name) {
        super(name);
    }

    // have CPU select button
    @Override
    public void selectButton(TerrainButton[][] terrain, int row, int col) {   
        if(terrain[row][col].isRevealed()) {
            row = rand.nextInt(Main.getRowMaximun());
            col = rand.nextInt(Main.getColMaximun());
            keepScore(terrain, row, col);
            terrain[row][col].setRevealed(true, colorObject());
            if(terrain[row][col].isGold()) {
                gameEnd(terrain, row, col);
            }  
        }     
    }
}