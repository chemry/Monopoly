package other;
import javax.swing.*;
import java.util.concurrent.*;

public class HelloSwing {

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Hello Swing");
        JLabel label = new JLabel("A label");
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(150, 100);
        frame.setVisible(true);
        TimeUnit.SECONDS.sleep(1);
        label.setText("Hey! ");

    }

}
