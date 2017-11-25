package cmd;

import java.security.SecureRandom;

/**
 * a chance square
 */
public class Chance extends Square {
    private static final int UPPERRATE = 20;
    private static final int LOWERRATE = 30;
    private StdOut stdOut = new StdOut(0);
    /**
     * @param name name of the chance square
     */
    public Chance(String name) {
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        SecureRandom rand = new SecureRandom();
        if(rand.nextBoolean()){
            int rate = rand.nextInt(UPPERRATE) + 1;
            stdOut.print("Player " + gamer.getName() + " get " + rate * 10 + " Money");
            stdOut.println(" (remain money: " + gamer.getMoney() + ")");
            gamer.addMoney(rate * 10);
        } else {
            int rate = rand.nextInt(LOWERRATE) + 1;
            stdOut.print("Player " + gamer.getName() + " lose " + rate * 10 + " Money");
            stdOut.println(" (remain money: " + gamer.getMoney() + ")");
            gamer.subMoney(rate * 10);
        }

    }
}
