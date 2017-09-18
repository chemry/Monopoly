package other;
import javax.swing.*;
import java.util.concurrent.*;
public class SubmitSwingProgram extends JFrame{
    JLabel label;
    public SubmitSwingProgram(){
        super("hello swing");
        label = new JLabel("A label");
        add(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setVisible(true);
    }

    static SubmitSwingProgram ssp;
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ssp = new SubmitSwingProgram();
            }
        });

        TimeUnit.SECONDS.sleep(2);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ssp.label.setText("changed!");
                //ssp.label.setSize(4000,800);
            }
        });
    }
}
