package gui;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Dice implements Runnable{
    private boolean diceOk = false;
    private int num = 0;
    JLabel dice;
    public boolean getDiceStatus(){
        return diceOk;
    }
    //Dice()
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
        String path = "src/dice/";
        //System.out.println(i);
        dice.setIcon(new ImageIcon("src/dice/" + i + ".jpg"));
        //System.out.println("something");

    }


    public void run(){
        Random random = new Random();
        int pre = 0;
        int i = -1;

        while(diceOk){
            do{
                i = random.nextInt(6) + 1;
            }while(i == pre);
            //System.out.println(i);
            spinDice(i);
            try {
                TimeUnit.MILLISECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pre = i;
            Thread.yield();
        }
        num = i;

    }
    /*
    dice.setIcon(new ImageIcon("src/dice/" + i + ".jpg"));
                            try {
        TimeUnit.MILLISECONDS.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    */
}
