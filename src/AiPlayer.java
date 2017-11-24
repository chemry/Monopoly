public class AiPlayer extends Gamers {
    public AiPlayer(int id){
        super(id);
    }
    public AiPlayer(String id){
        this(Integer.parseInt(id));
    }

    @Override
    public int doAction() {
        return 1;
    }
}
