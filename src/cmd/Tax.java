package cmd;

/**
 * a tax square
 */
public class Tax extends Square {
    private static final double INCOMETAX = 0.1;
    private StdOut stdOut = new StdOut(0);
    /**
     * @param name the name of the square
     */
    public Tax (String name){
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        double tax = gamer.getMoney() * INCOMETAX;
        tax = tax - tax % 10;
        stdOut.print("Player " + gamer.getName() + " loss " + (int)tax + " money!");
        gamer.subMoney((int)tax);
        stdOut.println(" (remain money: " + gamer.getMoney() + ")");
    }
}
