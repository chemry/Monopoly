import java.util.Scanner;

public class Jail extends Square{
    private static final int FINE = 90;
    public Jail(String name){
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        if(!gamer.getJailStatus()) return;
        Scanner sc = new Scanner(System.in);
        if(gamer.getJailDate() >= 1 && gamer.getMoney() >= FINE) {
            System.out.println("Do you want to pay 90 to Leave ?");
            String choice;
            while (true) {
                choice = sc.next();
                if (choice.equals("1") || choice.equals("0"))
                    break;
                System.out.println("Please input 0 or 1 !");
            }
            if (choice.equals("1")) {
                gamer.subMoney(FINE);
                System.out.println("You gave 90 to leave !");
                gamer.setFree();
                gamer.setPosition(gamer.getPosition() + tossDie());
                return;
            }
        } else if (gamer.getJailDate() == 1){
            gamer.subMoney(FINE);
            gamer.setPosition(gamer.getPosition() + tossDie());
            return;
        }
        int res = tossDoubleFace();
        if(res == 0)
            gamer.reduceJailDate();
        else
            gamer.setPosition(gamer.getPosition() + res);
    }

    private int tossDie(){
        Dice dice = new Dice();
        int face = dice.spin();
        face += dice.spin();
        return face;
    }

    private int tossDoubleFace(){
        Dice dice = new Dice();
        int face1 = dice.spin();
        int face2 = dice.spin();
        if(face1 == face2) return face1 + face2;
        return 0;
    }



}
