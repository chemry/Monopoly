package cmd;

/**
 * a free parking square
 */
public class FreeParking extends Square {
    private StdOut stdOut = new StdOut(0);
    /**
     * @param name name of the square
     */
    public FreeParking(String name){
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        stdOut.println("Free Parking!");
    }
}
