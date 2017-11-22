import java.util.Scanner;

public class Jail extends Square{
    public Jail(String name){
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        if(!gamer.getJailStatus()) return;
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to pay 90 to ");
    }
}
