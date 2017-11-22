import java.util.Scanner;

public class Monopoly {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = 0;
        while(sc.hasNextInt()) {
            a = sc.nextInt();
        }
        System.out.println(a);
    }
}
