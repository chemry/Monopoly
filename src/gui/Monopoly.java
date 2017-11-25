package gui;
// hello trying pull
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import cmd.*;

public class Monopoly {
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
    private JPanel paneltest;
    private JLabel player113;
    private JLabel player111;
    private JLabel player112;
    private JLabel player115;
    private JLabel player114;
    private JLabel player116;
    private JLabel player126;
    private JLabel player125;
    private JLabel player124;
    private JLabel player123;
    private JLabel player122;
    private JLabel player121;
    private JLabel player136;
    private JLabel player135;
    private JLabel player134;
    private JLabel player133;
    private JLabel player132;
    private JLabel player131;
    private JLabel player16;
    private JLabel player15;
    private JLabel player14;
    private JLabel player13;
    private JLabel player12;
    private JLabel player11;
    private JLabel player26;
    private JLabel player25;
    private JLabel player24;
    private JLabel player23;
    private JLabel player22;
    private JLabel player21;
    private JLabel player36;
    private JLabel player35;
    private JLabel player34;
    private JLabel player33;
    private JLabel player32;
    private JLabel player31;
    private JLabel player46;
    private JLabel player45;
    private JLabel player44;
    private JLabel player43;
    private JLabel player42;
    private JLabel player41;
    private JLabel player56;
    private JLabel player55;
    private JLabel player54;
    private JLabel player53;
    private JLabel player52;
    private JLabel player51;
    private JLabel player64;
    private JLabel player66;
    private JLabel player65;
    private JLabel player63;
    private JLabel player62;
    private JLabel player61;
    private JLabel player71;
    private JLabel player76;
    private JLabel player75;
    private JLabel player74;
    private JLabel player73;
    private JLabel player72;
    private JLabel player86;
    private JLabel player85;
    private JLabel player84;
    private JLabel player83;
    private JLabel player82;
    private JLabel player81;
    private JLabel player91;
    private JLabel player92;
    private JLabel player93;
    private JLabel player96;
    private JLabel player95;
    private JLabel player94;
    private JLabel player106;
    private JLabel player105;
    private JLabel player104;
    private JLabel player103;
    private JLabel player102;
    private JLabel player101;
    private JLabel player146;
    private JLabel player145;
    private JLabel player144;
    private JLabel player143;
    private JLabel player142;
    private JLabel player141;
    private JLabel player156;
    private JLabel player155;
    private JLabel player154;
    private JLabel player153;
    private JLabel player152;
    private JLabel player151;
    private JLabel player166;
    private JLabel player165;
    private JLabel player164;
    private JLabel player163;
    private JLabel player162;
    private JLabel player161;
    private JLabel player176;
    private JLabel player175;
    private JLabel player174;
    private JLabel player173;
    private JLabel player172;
    private JLabel player171;
    private JLabel player186;
    private JLabel player185;
    private JLabel player184;
    private JLabel player183;
    private JLabel player182;
    private JLabel player181;
    private JLabel player196;
    private JLabel player195;
    private JLabel player194;
    private JLabel player193;
    private JLabel player192;
    private JLabel player191;
    private JLabel player206;
    private JLabel player205;
    private JLabel player204;
    private JLabel player203;
    private JLabel player202;
    private JLabel player201;
    private JButton button01;
    private JButton button02;
    private JButton button03;
    private JButton button4;
    private JTable table1;
    private JButton button04;
    private JButton button05;
    private JButton button06;
    private JLabel infoField;
    private JTextArea textArea1;
    private JButton testButton;
    private int cnt = 0;
    private Dice dice = new Dice(dice0, dice1, counter, TextField1);
    private Map<String, JLabel> labelMap = new HashMap<String, JLabel>();

    private String state;
    private int humanNum;
    private int aiNum;


    private boolean field1Vis = false;
    public Monopoly() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.addColumn("A name", new Object[] {"Column1", "Column2"});
        model.addColumn("Another name", new Object[] {"row1", "row2"});
        //table1.addColumn();
        model.addRow(new Object[]{"well", "something"});
        model.setValueAt("reset", 1, 1);
        table1.setVisible(true);

        MessageConsole mc = new MessageConsole(textArea1);
        mc.redirectOut(null, System.out);
        mc.redirectErr(Color.RED, null);

        try {
            for(int i = 1; i <= 20; i++) {
                for(int j = 1; j <= 6; j++) {
                    String name = "player" + i + j;
                    Field temp = this.getClass().getDeclaredField(name);
                    temp.setAccessible(true);
                    JLabel tempLabel;
                    tempLabel = (JLabel) temp.get(this);
                    //tempLabel.setVisible(false);
                    labelMap.put(name, tempLabel);
                }
            }

        } catch (Throwable e2) {
            System.out.println("here" + e2);
        }

        counter.setText("Auto stop in:\n" + 5);
//        board0.setVisible(false);
//        board1.setVisible(false);
        paneltest.setOpaque(false);
        field1.setVisible(false);
        button1.addActionListener(e -> {
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
        });
        startButton.addActionListener(e -> {
            if(startButton.getText().equals("NewGame")){
                System.out.println(field1.getSize());
                if(!field1Vis) {
                    field1.setVisible(true);
                    field1Vis = true;
                    infoField.setText("Please input the number of human player");
                    String[] info = {"1", "2", "3", "4", "5", "6"};
                    setButtonText(info);
                    state = "HumanNum";
                    Game game = new Game();

                } else {
                    field1.setVisible(false);
                    field1Vis = false;
                }
            }

        });

        button01.addActionListener(e -> {
            if(state.equals("HumanNum")){
                humanNum = 1;
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Changed");
        frame.setContentPane(new Monopoly().getPanel1());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ImageIcon img = new ImageIcon("src/monopoly-board.png");
        JLabel imgLabel = new JLabel(img);
        frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        Container cp = frame.getContentPane();
        //cp.setLayout(new BorderLayout());
        System.out.println(img.getIconHeight() + " " +img.getIconWidth());
        ((JPanel)cp).setOpaque(false);

    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    private void setButtonText(String[] info){
        int l = info.length;
        if(l <= 0) return;
        button01.setText(info[0]);
        if(l >= 2)
            button02.setText(info[1]);
        if(l >= 3)
            button03.setText(info[2]);
        if(l >= 4)
            button04.setText(info[3]);
        if(l >= 5)
            button05.setText(info[4]);
        if(l >= 6)
            button06.setText(info[5]);

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
//        paneltest.add(new JScrollPane(textArea1));

        //mc.setMessageLines(100);
    }
}

