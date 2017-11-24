/**
 * a tax square
 */
public class Tax extends Square {
    private static final double INCOMETAX = 0.1;

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
        System.out.print("Player " + gamer.getName() + " loss " + (int)tax + " money!");
        gamer.subMoney((int)tax);
        System.out.println(" (remain money: " + gamer.getMoney() + ")");
    }
}
