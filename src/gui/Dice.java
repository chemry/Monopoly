package gui;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * displays dices on the board
 */
public class Dice implements Runnable{
    private boolean diceOk = false;
    private boolean forceStop = false;
    private int num = 0;
    private JLabel dice, dice2, counter;
    private JTextField text1;

    Dice(JLabel dice, JLabel dice2, JLabel counter, JTextField text1){
        this.dice = dice;
        this.dice2 = dice2;
        this.text1 = text1;
        this.counter = counter;
    }

    /**
     * diceOk be whether the dice is stopped
     * @param diceOk set the status to diceOk which made the dice stop or keep running
     */
    public void setDiceStatus(boolean diceOk){
        this.diceOk = diceOk;
    }

    /**
     * @return the final value of the dice
     */
    public int getNum(){
        return num;
    }

    /**
     * @return
     */
    public boolean getForceStatus(){return forceStop;}
    public void setForceStatus(boolean forceStop){this.forceStop = forceStop;};
    //public boolean getStatus

    public void spinDice(JLabel dice, int i){
        dice.setIcon(new ImageIcon("src/dice/" + i + ".jpg"));
    }

    public void run(){
        Random random = new Random();
        int prei = 0;
        int prej = 0;
        int i = -1;
        int j = -1;
        long cnt = 0;
        boolean cont = true;
        counter.setText("Auto stop in:\n" + (5 - cnt/20));
        while(diceOk && cont){
            do{
                i = random.nextInt(6) + 1;
            }while(i == prei);

            do{
                j = random.nextInt(6) + 1;
            }while(j == prej);
            //System.out.println(i);
            spinDice(dice, i);
            spinDice(dice2, j);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
                cnt++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(cnt / (1000 / 50) == 5) {
                cont = false;
                forceStop = true;
            }
            if(cnt % 20 == 0){
                counter.setText("Auto stop in:\n" + (5 - cnt/20));
            }

            prei = i;
            prej = j;
            Thread.yield();
        }
        num = i + j;
        text1.setText("  "+String.valueOf(num));
    }

}
