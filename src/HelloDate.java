import java.util.*;

//import static net.mindview.utl.Print.*;

class Tank {
    private int level;
    Tank(){
        System.out.println("Tank initialized without level!");
    }
    Tank(int i) {
        System.out.println("Tank initialized with level!");
        /*this.*/level = i;
    }
    Tank(String s, int i){
        this(i);
        System.out.println(s);
    }
    public void printLevel(){
        System.out.println(level);
    }

    @Override
    public String toString() {
        return "level : " + level;
    }
}
abstract class BigTank {
    private Tank t1;

}


public class HelloDate {
    public static void main(String[] args) {
        Tank t1 = new Tank();
        Tank t2 = new Tank(5);
        Tank t3 = new Tank("hello", 5);
        //t1.level = 1;
        //t2.level = 2;
        //System.out.println("t1: " + t1.level + "\nt2: " + t2.level);
        t3.printLevel();
        System.out.println(t2);
        System.out.println("Hello, it is: ");
        System.out.println(new Date());
        StdOut.printf("%d %.2f\n", 1, 0.345);
        float f[] = new float[10];
        Random rand = new Random(12321);
        for(int i = 0; i < 10; i++)
            f[i] = rand.nextFloat();
        for(float x : f)
            System.out.println(x);
    }

}
