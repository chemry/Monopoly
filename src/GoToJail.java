/**
 * a gotojail square
 */
public class GoToJail extends Square{
    private static final int JAIL = 5;

    /**
     * @param name name of the square
     */
    public GoToJail(String name) {
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        System.out.println(gamer.getName() + " is sent to Jail....");
        gamer.setPosition(JAIL);
        gamer.setJailStatus(true);
    }
}
