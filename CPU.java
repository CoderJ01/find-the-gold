import java.util.Random;
import java.util.concurrent.TimeUnit;

public final class CPU extends Player {
    // field
    private Random rand = new Random();
    private boolean turnOver;
    
    // constructor
    public CPU(String name) {
        super(name);
    }

    // have CPU select button
    @Override
    public void selectButton(TerrainButton[][] terrain, int rowMax, int colMax) {   
        delay();
        this.turnOver = false;
        OUTER: while(getPoints() > 0) {
            for(int row = 0; row < rowMax; row++) {
                for(int col = 0; col < colMax; col++) {     
                    if(!terrain[row][col].isRevealed() && !this.turnOver) {
                        row = rand.nextInt(Main.getRowMaximun());
                        col = rand.nextInt(Main.getColMaximun());
                        keepScore(terrain, row, col);
                        terrain[row][col].setRevealed(true, colorObject());
                        this.turnOver = true;
                        if(terrain[row][col].isGold()) {
                            gameEnd(terrain, row, col);
                        } 
                        if(this.turnOver) {
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
            TimeUnit.MILLISECONDS.sleep(750);
        }
        catch(InterruptedException e) {
            System.out.println("Error");
        }
    }
}