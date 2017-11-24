import java.security.SecureRandom;
import java.util.Scanner;

/**
 * a jail square
 */
public class Jail extends Square{
    private static final int FINE = 90;

    /**
     * @param name name of the square
     */
    public Jail(String name){
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        if(!gamer.getJailStatus()) return;
        //System.out.println(gamer.getName() + " is in jail? " + gamer.getJailStatus());
        Scanner sc = new Scanner(System.in);
        String choice = "0";
        if(gamer.getJailDate() >= 1 && gamer.getMoney() >= FINE) {
            if (gamer.getJailDate() == 1) {
                gamer.subMoney(FINE);
                gamer.setFree();
                gamer.setPosition(gamer.getPosition() + tossDie());
                return;
            }
            if(gamer instanceof HumanPlayer) {
                System.out.println(gamer.getName() + ", Do you want to pay 90 to Leave ?");
                while (true) {
                    choice = sc.next();
                    if (choice.equals("1") || choice.equals("0"))
                        break;
                    System.out.println("Please input 0 or 1 !");
                }
            } else {
                SecureRandom rand = new SecureRandom();
                if(rand.nextBoolean())
                    choice = "1";
            }
            if (choice.equals("1")) {
                gamer.subMoney(FINE);
                System.out.println(gamer.getName() + " gave 90 to leave !");
                gamer.setFree();
                gamer.setPosition(gamer.getPosition() + tossDie());
                return;
            }

        }
        int res = tossDoubleFace();
        if(res == 0) {
            System.out.println("Failed!");
            gamer.reduceJailDate();
        } else {
            System.out.println("Success!");
            gamer.setPosition(gamer.getPosition() + res);
        }
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
