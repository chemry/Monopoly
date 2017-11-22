public class Go extends Square {
    private static final int SALARY = 1500;

    public Go(String name){
        super(name);
    }
    @Override
    public void action(Gamers gamer, Game game) {
        gamer.addMoney(SALARY);
    }
}
