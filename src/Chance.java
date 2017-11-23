import java.security.SecureRandom;

public class Chance extends Square {
    private static final int UPPERRATE = 20;
    private static final int LOWERRATE = 30;
    public Chance(String name) {
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        SecureRandom rand = new SecureRandom();
        if(rand.nextBoolean()){
            int rate = rand.nextInt(UPPERRATE) + 1;
            System.out.println("Player :" + gamer.getName() + " get " + rate * 10 + "Money");
            gamer.addMoney(rate * 10);
        } else {
            int rate = rand.nextInt(LOWERRATE) + 1;
            System.out.println("Player :" + gamer.getName() + " lose " + rate * 10 + "Money");
            gamer.subMoney(rate * 10);
        }
    }
}
