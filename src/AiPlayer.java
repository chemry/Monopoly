/**
 * AI Player
 */
public class AiPlayer extends Gamers {
    /**
     * @param id the id used to give a name
     */
    public AiPlayer(int id){
        super(id);
    }

    /**
     * @param id id of the ai player
     * @param name name of the ai player
     */
    public AiPlayer(int id, String name){
        super(id, name);
    }
    /**
     * @param id id in string format
     */
    public AiPlayer(String id){
        this(Integer.parseInt(id));
    }

    @Override
    public int doAction() {
        return 1;
    }
}
