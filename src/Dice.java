import java.security.SecureRandom;

public class Dice {
    int result1;
    int result2;

    public Dice(){
        result1 = -1;
        result2 = -1;
    }
    public int spin(){
        SecureRandom rand = new SecureRandom();
        result1 = rand.nextInt(6) + 1;
        result2 = rand.nextInt(6) + 1;
        return result1 + result2;
    }

}
