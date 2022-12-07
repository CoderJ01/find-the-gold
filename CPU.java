import java.util.Random;
import java.util.concurrent.TimeUnit;

public final class CPU extends Player {
    private Random rand = new Random(); // field
    private int turnOver;
    
    // constructor
    public CPU(String name) {
        super(name);
    }

    // have CPU select button
    @Override
    public void selectButton(TerrainButton[][] terrain, int rowMax, int colMax) {   
        delay();
        this.turnOver = 0;
        OUTER: while(true) {
            for(int row = 0; row < rowMax; row++) {
                for(int col = 0; col < colMax; col++) {     
                    row = rand.nextInt(Main.getRowMaximun());
                    col = rand.nextInt(Main.getColMaximun()); 
                    if(!terrain[row][col].isRevealed() && this.turnOver == 0) {
                        row = rand.nextInt(Main.getRowMaximun());
                        col = rand.nextInt(Main.getColMaximun());
                        keepScore(terrain, row, col);
                        terrain[row][col].setRevealed(true, colorObject());
                        this.turnOver++;
                        if(terrain[row][col].isGold()) {
                            gameEnd(terrain, row, col);
                        } 
                        if(this.turnOver == 1) {
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
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e) {
            System.out.println("Error");
        }
    }
}