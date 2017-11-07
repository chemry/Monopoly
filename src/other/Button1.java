package other;
import javax.swing.*;
import java.awt.*;

public class Button1 extends JFrame{
    private JButton
        b1 = new JButton(),
        b2 = new JButton();
    public Button1(){
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
    }
    public static void main(String[] args){
        SwingConsole.run(new Button1(), 800, 600);
    }
}
