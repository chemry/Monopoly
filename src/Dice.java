import java.security.SecureRandom;

public class Dice {
    private int face;

    public Dice(){
        face = -1;
    }
    public int spin(){
        SecureRandom rand = new SecureRandom();
        face = rand.nextInt(6) + 1;
        return face;
    }


}
