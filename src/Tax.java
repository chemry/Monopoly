public class Tax extends Square {
    private static final double INCOMETAX = 0.1;

    public Tax (String name){
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        double tax = gamer.getMoney() * INCOMETAX;
        tax = tax - tax % 10;
        gamer.subMoney((int)tax);
    }
}
