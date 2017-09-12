package interfaces;

import java.util.*;

public class RandomDouble {
    private static Random rand = new Random(423);
    public double next() {
        return rand.nextDouble();
    }
    public static void main(String[] args){
        RandomDouble id = new RandomDouble();
        for(int i = 0; i < 8; i++){
            System.out.println(id.next());
        }
    }
}
