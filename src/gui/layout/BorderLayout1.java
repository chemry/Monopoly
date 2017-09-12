package gui.layout;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import static gui.SwingConsole.*;


public class BorderLayout1 extends JFrame{
    public BorderLayout1(){
        add(BorderLayout.NORTH, new JButton("North"));
        add(BorderLayout.SOUTH, new JButton("South"));
        add(BorderLayout.WEST, new JButton("West1"));
        //add(BorderLayout.WEST, new JButton("West2"));
        add(BorderLayout.NORTH, new JButton("North2"));
        add(BorderLayout.EAST, new JButton("East"));

        add(BorderLayout.CENTER, new JButton("Center"));
    }
    public static void main(String[] args){
        run(new BorderLayout1(), 800, 600);
    }
}
