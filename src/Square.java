abstract public class Square {
    private String name;

    public Square(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public abstract void action(Gamers gamer, Game game);
}
