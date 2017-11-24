/**
 * a free parking square
 */
public class FreeParking extends Square{
    /**
     * @param name name of the square
     */
    public FreeParking(String name){
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        System.out.println("Free Parking!");
    }
}
