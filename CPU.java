import java.util.Random;
import java.util.concurrent.TimeUnit;

public final class CPU extends Player {
    private Random rand = new Random(); // field
    
    // constructor
    public CPU(String name) {
        super(name);
    }

    // have CPU select button
    @Override
    public void selectButton(TerrainButton[][] terrain, int row, int col) {   
        delay();
        if(!terrain[row][col].isRevealed()) {
            row = rand.nextInt(Main.getRowMaximun());
            col = rand.nextInt(Main.getColMaximun());
            keepScore(terrain, row, col);
            terrain[row][col].setRevealed(true, colorObject());
            if(terrain[row][col].isGold()) {
                gameEnd(terrain, row, col);
            }  
        }     
    }

    private void delay() {
        try {
            // delay each output by less than a second
            TimeUnit.MICROSECONDS.sleep(750);
        }
        catch(InterruptedException e) {
            System.out.println("Error");
        }
    }
}