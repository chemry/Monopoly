public class Go extends Square {
    private static final int SALARY = 1500;

    public Go(String name){
        super(name);
    }
    @Override
    public void action(Gamers gamer) {
        System.out.print("Player " + gamer.getName() + " get a salary of " + SALARY);
        gamer.addMoney(SALARY);
        System.out.println(" (remain money: " + gamer.getMoney() + ")");
    }
}
