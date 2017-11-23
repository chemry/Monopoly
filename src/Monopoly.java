import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Monopoly {

    public static void main(String[] args){
        /*Scanner sc = new Scanner(System.in);
        String a = "-1";
        while(true){
            a = sc.next();
            if(a.equals("1") || a.equals("0"))
                break;
            System.out.println("Please input 0 or 1 !");
        }
        System.out.println(a);*/
        int p = 111;
        double t = p * 0.1;
        t = t - t % 10;
        p = p - (int) t;
        System.out.println(p);
    }
}
