package gui;
// hello trying pull
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.concurrent.TimeUnit;

public class Test {
    private JPanel panel1;
    private JButton button1;
    private JLabel dice0;
    private JButton a1Button;
    private JTextField TextField1;
    private JLabel board11;
    private JLabel board10;
    private JLabel board9;
    private JLabel board8;
    private JLabel board7;
    private JLabel board6;
    private JLabel board12;
    private JLabel board13;
    private JLabel board14;
    private JLabel board15;
    private JLabel board16;
    private JLabel board17;
    private JLabel board18;
    private JLabel board19;
    private JLabel board20;
    private JLabel board1;
    private JLabel board2;
    private JLabel board3;
    private JLabel board4;
    private JLabel board5;
    private JLabel counter;
    private JLabel board0;
    private JPanel field1;
    private JButton button2;
    private static int cnt = 0;
    Dice dice = new Dice(dice0);
    public Test() {
        counter.setText("Auto stop in:\n" + 5);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(cnt == 0) {
                    //TextField1.setText(String.valueOf(dice.getNum()));
                    dice.setDiceStatus(true);
                    Thread t = new Thread(dice);
                    t.start();
                    cnt = 1;
                } else{
                    cnt = 0;
                    dice.setDiceStatus(false);
                    //panel1.setVisible(false);
                    //field1.setVisible(false);
                    //TextField1.setText("reading value");
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    TextField1.setText("   "+String.valueOf(dice.getNum()));
                }
            }

        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setContentPane(new Test().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
