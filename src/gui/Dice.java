package gui;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Dice implements Runnable{
    private boolean diceOk = false;
    private int num = 0;
    JLabel dice;
    Dice(JLabel dice){
        this.dice = dice;
    }
    public void setDiceStatus(boolean diceOk){
        this.diceOk = diceOk;
    }
    public int getNum(){
        return num;
    }

    public void spinDice(int i){
        dice.setIcon(new ImageIcon("src/dice/" + i + ".jpg"));
    }

    public void run(){
        Random random = new Random();
        int pre = 0;
        int i = -1;
        long cnt = 0;
        while(diceOk){
            do{
                i = random.nextInt(6) + 1;
            }while(i == pre);
            //System.out.println(i);
            spinDice(i);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
                cnt++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(cnt == 1000/50 * 3)
                System.out.println(3);

            pre = i;
            Thread.yield();
        }
        num = i;
    }

}
