abstract public class Square {
    private String name;
    private static final int SQUARE_NUM = 20;
    public Square(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
    public abstract void action(Gamers gamer);

}
