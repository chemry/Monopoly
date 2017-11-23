public class FreeParking extends Square{
    public FreeParking(String name){
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        System.out.println("Free Parking!");
    }
}
