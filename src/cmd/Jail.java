package cmd;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * a jail square
 */
public class Jail extends Square {
    private static final int FINE = 90;
    private StdOut stdOut = new StdOut(0);
    /**
     * @param name name of the square
     */
    public Jail(String name){
        super(name);
    }

    @Override
    public void action(Gamers gamer) {
        if(!gamer.getJailStatus()) return;
        //stdOut.println(gamer.getName() + " is in jail? " + gamer.getJailStatus());
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
                stdOut.println(gamer.getName() + ", Do you want to pay 90 to Leave?");
                while (true) {
                    choice = sc.next();
                    if (choice.equals("1") || choice.equals("0"))
                        break;
                    stdOut.println("Please input 0 or 1!");
                }
            } else {
                SecureRandom rand = new SecureRandom();
                if(rand.nextBoolean())
                    choice = "1";
            }
            if (choice.equals("1")) {
                gamer.subMoney(FINE);
                stdOut.println(gamer.getName() + " gave 90 to leave!");
                gamer.setFree();
                gamer.setPosition(gamer.getPosition() + tossDie());
                return;
            }

        }
        int res = tossDoubleFace();
        if(res == 0) {
            stdOut.println(gamer.getName() + " failed to escape by tossing a double face!");
            gamer.reduceJailDate();
        } else {
            stdOut.println(gamer.getName() + " successfully escaped by tossing a double face!");
            gamer.setFree();
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
