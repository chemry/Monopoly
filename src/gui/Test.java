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
    private JLabel board;
    private JButton button1;
    private JLabel dice0;
    private JButton a1Button;
    private JTextField TextField1;
    private static int cnt = 0;
    Dice dice = new Dice(dice0);
    public Test() {

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
                    //TextField1.setText("reading value");
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    TextField1.setText(String.valueOf(dice.getNum()));
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
