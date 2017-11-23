public class GoToJail extends Square{
    private static final int JAIL = 5;

    public GoToJail(String name) {
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        gamer.setPosition(JAIL);
        gamer.setJailStatus(true);
    }
}
