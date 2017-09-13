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
    private JLabel board1;
    private JLabel counter;
    private JLabel board0;
    private JPanel field1;
    private JButton startButton;
    private JLabel dice1;
    private static int cnt = 0;
    Dice dice = new Dice(dice0, dice1, counter, TextField1);

    private boolean field1Vis = false;
    public Test() {
        counter.setText("Auto stop in:\n" + 5);
//        board0.setVisible(false);
//        board1.setVisible(false);

        field1.setVisible(false);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(dice.getForceStatus()){
                    dice.setForceStatus(false);
                    cnt = 0;
                }
                if(cnt == 0) {
                    //TextField1.setText(String.valueOf(dice.getNum()));
                    cnt = 1;
                    dice.setDiceStatus(true);
                    Thread t = new Thread(dice);
                    t.start();

                } else{
                    cnt = 0;
                    dice.setDiceStatus(false);
                }
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(startButton.getText() == "Start!"){
                    System.out.println(panel1.getSize());
                    System.out.print("good");
                    if(field1Vis == false) {

                        field1.setVisible(true);
                        field1Vis = true;
                    } else {
                        field1.setVisible(false);
                        field1Vis = false;
                    }
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
        ImageIcon img = new ImageIcon("src/monopoly-board.png");
        JLabel imgLabel = new JLabel(img);
        frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
        Container cp = frame.getContentPane();
        //cp.setLayout(new BorderLayout());
        JButton but = new JButton();
        ((JPanel)cp).setOpaque(false);
    }

}
