package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import static gui.SwingConsole.*;

public class TextArea extends JFrame{
    private JButton
            b1 = new JButton("add data"),
            b2 = new JButton("clear data");
    private JTextArea t = new JTextArea(20, 40);
    public TextArea() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < 10; i++)
                    t.append(i + "^2 = " + i * i + "\n");
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setText("");
            }
        });
        setLayout(new FlowLayout());
        add(new JScrollPane(t));
        add(b1);
        add(b2);
    }
    public static void main(String[] args){
        run(new TextArea(), 800, 600);
    }

}
