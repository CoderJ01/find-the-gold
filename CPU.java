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
    public void selectButton(TerrainButton[][] terrain, int rowMax, int colMax) {   
        delay();
        startTurn();
        OUTER: while(getPoints() > 0) {
            for(int row = 0; row < rowMax; row++) {
                for(int col = 0; col < colMax; col++) {     
                    if(!terrain[row][col].isRevealed() && !turnOver()) {
                        row = rand.nextInt(Main.getRowMaximun());
                        col = rand.nextInt(Main.getColMaximun());
                        keepScore(terrain, row, col);
                        terrain[row][col].setRevealed(true, colorObject());
                        endTurn();
                        if(terrain[row][col].isGold()) {
                            gameEnd(terrain, row, col);
                        } 
                        if(turnOver()) {
                            break OUTER;
                        }  
                    }     
                }
            }
        }
    }

    private void delay() {
        try {
            // delay each output by less than a second
            TimeUnit.MILLISECONDS.sleep(80);
        }
        catch(InterruptedException e) {
            System.out.println("Error");
        }
    }
}