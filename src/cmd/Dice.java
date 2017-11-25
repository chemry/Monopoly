package cmd;

import java.security.SecureRandom;

/**
 * a dice
 */
public class Dice {
    private int face;

    /**
     * construct the dice;
     */
    public Dice(){
        face = -1;
    }

    /**
     * @return the value of the dice after spin
     */
    public int spin(){
        SecureRandom rand = new SecureRandom();
        face = rand.nextInt(6) + 1;
        return face;
    }


}
