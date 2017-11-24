/**
 * a square class
 */
abstract public class Square {
    private String name;

    /**
     * @param name the name of the square
     */
    public Square(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    /**
     * @param gamer do actions to the gamer
     */
    public abstract void action(Gamers gamer);

}
