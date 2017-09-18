package other;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Button2 extends JFrame{
    private JButton
            b1 = new JButton("button1"),
            b2 = new JButton("button2");
    private JTextField txt = new JTextField(30);



    /*class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton)e.getSource()).getText();
            //System.out.println(name);
            txt.setText(name);
        }
    }*/
    private ActionListener bl = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton)e.getSource()).getText();
            //System.out.println(name);
            txt.setText(name);
        }
    };



    public Button2(){
        b1.addActionListener(bl);
        b2.addActionListener(bl);

        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(txt);
    }
    public static void main(String[] args){
        SwingConsole.run(new Button2(), 800, 600);
    }
}
