package cmd;

/**
 * a go square
 */
public class Go extends Square {
    private static final int SALARY = 1500;
    private StdOut stdOut = new StdOut(0);
    /**
     * @param name name of the square
     */
    public Go(String name){
        super(name);
    }
    @Override
    public void action(Gamers gamer) {
        stdOut.print("Player " + gamer.getName() + " get a salary of " + SALARY);
        gamer.addMoney(SALARY);
        stdOut.println(" (remain money: " + gamer.getMoney() + ")");
    }
}
