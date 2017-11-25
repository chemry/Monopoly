package cmd;

import java.io.*;
import java.util.*;

/**
 * use to record the status of a game
 */
public class Game {
    private static final int SQUARE_NUM = 20;
    private static final int MAX_TURN = 100;

    private StdOut stdOut = new StdOut(0);
    
    private Square[] Square;
    private Gamers[] Gamers;
    private static final String[] squareName = {"Go", "Central", "Wan Chai", "Tax", "Stanley", "Jail", "Shek O",
            "Mong Kok", "Chance", "Tsing Yi", "Free Parking", "Shatin", "Chance", "Tuen Men", "Tai Po", "Go To Jail",
            "Sai Kung", "Yuen Long", "Chance", "Tai O"};

    private static final int[][] buildingPrice = {null, {850, 90}, {750, 70}, null, {650, 65}, null, {350, 15}, {550, 35},
            null, {450, 20}, null, {650, 70}, null, {350, 25}, {550, 20}, null, {400, 15}, {450, 25}, null, {650, 30}};

    private int humanPlayerNum;
    private int aiPlayerNum;
    private int totalPlayer;
    private int curTurn = 0;
    // int curGamer = 0;
    private int st = 0;


    {
        Square = new Square[SQUARE_NUM];
        for (int i = 0; i < SQUARE_NUM; i++) {
            switch (squareName[i]) {
                case "Go":
                    Square[i] = new Go(squareName[i]);
                    break;
                case "Tax":
                    Square[i] = new Tax(squareName[i]);
                    break;
                case "Jail":
                    Square[i] = new Jail(squareName[i]);
                    break;
                case "Go To Jail":
                    Square[i] = new GoToJail(squareName[i]);
                    break;
                case "Chance":
                    Square[i] = new Chance(squareName[i]);
                    break;
                case "Free Parking":
                    Square[i] = new FreeParking(squareName[i]);
                    break;
                default:
                    //stdOut.println(i);
                    Square[i] = new Building(squareName[i], buildingPrice[i][0], buildingPrice[i][1]);
                    break;
            }
        }
    }

    /**
     * the constructor for loading a game;
     */
    public Game() {
        stdOut.println("Please enter the file path");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        File file = new File(path);

        //boolean readSuc = true;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fr);
            String string;
            string = bReader.readLine();
            stdOut.println(string);
            //str.split(String sign);
            String[] num = string.split(" ");
            //stdOut.println(Arrays.toString(num));
            humanPlayerNum = Integer.parseInt(num[0]);
            aiPlayerNum = Integer.parseInt(num[1]);
            curTurn = Integer.parseInt(num[2]);
            st = Integer.parseInt(num[3]);

            totalPlayer = humanPlayerNum + aiPlayerNum;
            Gamers = new Gamers[totalPlayer];

            bReader.readLine();
            String[] datas = new String[totalPlayer];
            int p = 0;
            while ((string = bReader.readLine()) != null) {
                //stdOut.println(string);
                datas[p++] = string;
            }

            for (int i = 0; i < totalPlayer; i++) {
                String[] gamerInfo = datas[i].split("-");
                gamerInfo[4] = gamerInfo[4].substring(1, gamerInfo[4].length() - 1);
                if(gamerInfo[7].equals("Human")){
                    Gamers[i] = new HumanPlayer(i, gamerInfo[0]);
                } else {
                    Gamers[i] = new AiPlayer(i, gamerInfo[0]);
                }

                List<Building> property = new ArrayList<>();
                for (String buildingName : gamerInfo[4].split(", ")) {
                    for (int j = 0; j < SQUARE_NUM; j++) {
                        if (buildingName.equals(squareName[j])) {
                            ((Building) Square[j]).setOccupied(true);
                            ((Building) Square[j]).setOwner(Gamers[i]);
                            property.add(((Building) Square[j]));
                        }
                    }
                }
                Gamers[i].setProperty(property);

                int pos = Integer.parseInt(gamerInfo[1]);
                int money = Integer.parseInt(gamerInfo[2]);
                int jailDate = Integer.parseInt(gamerInfo[3]);
                boolean jailStatus = Boolean.parseBoolean(gamerInfo[5]);
                boolean isAlive = Boolean.parseBoolean(gamerInfo[6]);
                Gamers[i].setPosition(pos);
                Gamers[i].setMoney(money);
                Gamers[i].setJailStatus(jailStatus);
                Gamers[i].setJailDate(jailDate);
                Gamers[i].setAlive(isAlive);
            }

            fr.close();
        } catch (IOException e) {
            stdOut.println("File open or reading error!, Please check if the file exists or is the file in correct format");
            st = -1;
        }

    }

    /**
     * @param humanPlayer the number of human player
     * @param aiPlayer    the number of AI player
     */
    public Game(String[] humanPlayer, String[] aiPlayer) {

        //Gamers = new ArrayList<>();
        humanPlayerNum = humanPlayer.length;
        aiPlayerNum = aiPlayer.length;
        totalPlayer = humanPlayerNum + aiPlayerNum;
        st = 0;

        Gamers = new Gamers[totalPlayer];
        int id = 1;
        for (int i = 0; i < humanPlayerNum; i++)
            Gamers[i] = new HumanPlayer(id++, humanPlayer[i]);
        for (int i = 0; i < aiPlayerNum; i++)
            Gamers[i + humanPlayerNum] = new AiPlayer(aiPlayer[i]);

    }

    /**
     * start the game
     *
     * @return the status of the game, -1 for reload
     */
    public int startGame() {
        if(st == -1) return 0;
        stdOut.println("Game Start!");
        boolean isFinish = false;
        int firstEnter = st;
        while (!isFinish) {
            //do something
            if(getWinner() != null) break;
            System.out.println("Round " + (curTurn + 1));

            for (int i = firstEnter; i < totalPlayer; i++) {
                firstEnter = 0;
                if (isFinish || !Gamers[i].isAlive()) {
                    //stdOut.println(Gamers[i].getName() + " " + isFinish + " " + Gamers[i].isAlive());
                    clearProperty(Gamers[i]);
                    continue;
                }
                //stdOut.println("here: " + Arrays.toString(Gamers));
                int choice = 0;
                while (choice != 1) {
                    choice = Gamers[i].doAction();
                    if (choice == 2) {
                        drawBoard();
                        //continue;
                    } else if (choice == 3) {
                        stdOut.print("change player to AI");
                        List<Building> property = Gamers[i].getProperty();
                        int money = Gamers[i].getMoney();
                        Gamers[i] = new AiPlayer(i + 1);
                        stdOut.println(" " + Gamers[i].getName());
                        Gamers[i].setProperty(property);
                        Gamers[i].setMoney(money);
                        for (Building building : property) {
                            building.setOwner(Gamers[i]);
                        }
                        //stdOut.println(Gamers[i]);
                    } else if (choice == 4) {
                        stdOut.println("clear property");
                        //List<Building> property = Gamers[i].getProperty();
                        Gamers[i].setAlive(false);
                        clearProperty(Gamers[i]);
                        break;
                    } else if (choice == 5) {

                        saveData(i);
                    } else if (choice == 6) {
                        //loadData();
                        return -1;
                    }
                }
                if (!Gamers[i].isAlive()) {
                    continue;
                }
                if (!Gamers[i].getJailStatus()) {
                    moveGamer(Gamers[i]);
                } else {
                    int prePos = Gamers[i].getPosition();
                    Square[prePos].action(Gamers[i]);
                    int curPos = Gamers[i].getPosition();
                    if (prePos != curPos) {
                        stdOut.println("Player " + Gamers[i].getName() + " moves to square No." + (curPos + 1) + ": " + Square[curPos]);
                        Square[curPos].action(Gamers[i]);
                        if (Gamers[i].isAlive()) {
                            Gamers[i].nextTurn();
                        }
                    }
                }
                if (getWinner() != null)
                    isFinish = true;

            }

            curTurn++;
        }
        List<Gamers> winner = getWinner();
        if(curTurn >= MAX_TURN) {
            System.out.println("Max Turn reached!");
            if(winner.size() >= 2){
                System.out.println("Ties!");
                for(Gamers gamer: Gamers){
                    System.out.println("Player " + gamer.getName() + " wins with " + gamer.getMoney() + " money");
                }
            }else {
                Gamers gamer = winner.get(0);
                System.out.println("Player " + gamer.getName() + " wins with " + gamer.getMoney() + " money");
            }
        } else {
            System.out.println("Only one player is alive!");
            Gamers gamer = winner.get(0);
            System.out.println("Player " + gamer.getName() + " wins with " + gamer.getMoney() + " money");
        }
        return 1;
    }

    /**
     * @return the game status
     */
    public boolean isGameEnd() {
        return false;
    }

    /**
     * @param gamer the gamers to be moved
     */
    void moveGamer(Gamers gamer) {
        Dice dice = new Dice();
        int step = dice.spin();
        step += dice.spin();

        int newPosition = gamer.getPosition() + step;
        newPosition %= SQUARE_NUM;
        gamer.setPosition(newPosition);
        stdOut.println("Player " + gamer.getName() + " moves to square No." + (newPosition + 1) + ": " + Square[newPosition]);
        Square[gamer.getPosition()].action(gamer);
        if (gamer.isAlive()) {
            gamer.nextTurn();
        }
    }

    /*private boolean doubleFace(Gamers gamer){
        return false;
    }*/

    /**
     * @return the list of the winner (may have more than one)
     */
    public List<Gamers> getWinner() {
        if (this.curTurn >= MAX_TURN) {
            return getMaxMoneyGamer();
        }

        int alive = 0;
        Gamers winner = null;
        for (Gamers gamer : Gamers) {
            if (gamer.isAlive()) {
                alive++;
                winner = gamer;
            }
        }

        if (alive == 1) {
            List<Gamers> gList = new LinkedList<>();
            gList.add(winner);
            return gList;
        }

        return null;
    }

    private List<Gamers> getMaxMoneyGamer() {
        List<Gamers> gList = new LinkedList<>();
        int maxMoney = 0;
        for (Gamers gamer : Gamers) {
            if (gamer.getMoney() > maxMoney) {
                gList = new LinkedList<>();
                maxMoney = gamer.getMoney();
            }
            if (gamer.getMoney() == maxMoney) {
                gList.add(gamer);
            }
        }
        return gList;
    }

    private void drawBoard() {
        System.out.println("A board");
        System.out.println();
        Queue<String>[] PS = new Queue[(SQUARE_NUM * 2 - 1)];
        for (int i = 0; i < (SQUARE_NUM * 2 - 1); i++)
            PS[i] = new LinkedList<>();
        for (Gamers gamer : Gamers) {
            if (!gamer.isAlive())
                continue;
            int position = gamer.getPosition();
            PS[position].add(gamer.getName());
        }
        for (int i = 0; i < 6; i++) System.out.print("----------------");
        System.out.print("\n|");
        for (int i = 10; i < (SQUARE_NUM - 4); i++) {
            System.out.format("%-15s|", printOwner(i + 1));
        }
        System.out.print("\n|");
        for (int i = 10; i < (SQUARE_NUM - 4); i++) {
            System.out.format("%-15s|", squareName[i]);
        }
        System.out.print("\n|");
        for (int k = 0; k < 6; k++) {
            for (int i = 10; i < (SQUARE_NUM - 4); i++) {
                if (PS[i].isEmpty()) System.out.format("%15s|", "");
                else System.out.format("%-15s|", PS[i].poll());
            }
            System.out.print("\n|");
        }
        for (int i = 0; i < 6; i++) System.out.print("----------------");
        System.out.print("|\n|");
        for (int k = 0; k < 4; k++) {
            System.out.print("---------------|");
            System.out.format("%63s", "");
            System.out.print("|---------------|\n|");
            System.out.format("%-15s|", printOwner(10 - k));
            System.out.format("%63s|", "");
            System.out.format("%-15s|", printOwner((SQUARE_NUM - 3) + k));
            System.out.print("\n|");
            System.out.format("%-15s|", squareName[9 - k]);
            System.out.format("%63s|", "");
            System.out.format("%-15s|", squareName[(SQUARE_NUM - 4) + k]);
            System.out.print("\n|");
            for (int i = 0; i < 6; i++) {
                if (PS[10 - k].isEmpty()) System.out.format("%15s|", "");
                else System.out.format("%-15s|", PS[10 - k].poll());
                System.out.format("%63s|", "");
                if (PS[(SQUARE_NUM - 3) + k].isEmpty()) System.out.format("%15s|", "");
                else System.out.format("%-15s|", PS[(SQUARE_NUM - 3) + k].poll());
                System.out.print("\n|");
            }
        }
        for (int i = 0; i < 6; i++) System.out.print("----------------");
        System.out.print("\n|");
        for (int i = 0; i < 6; i++) {
            System.out.format("%-15s|", printOwner(6 - i));
        }
        System.out.print("\n|");
        for (int i = 0; i < 6; i++) {
            System.out.format("%-15s|", squareName[5 - i]);
        }
        System.out.print("\n|");
        for (int k = 0; k < 6; k++) {
            for (int i = 5; i >= 0; i--) {
                if (PS[i].isEmpty()) System.out.format("%15s|", "");
                else System.out.format("%-15s|", PS[i].poll());
            }
            System.out.print("\n|");
        }
        for (int i = 0; i < 6; i++) System.out.print("----------------");
        System.out.println();
    }

    private String printOwner(int i) {
        i -= 1;
        if (!(Square[i] instanceof Building)) return i + "";
        Building b = (Building) Square[i];
        if (b.getOwner() == null)
            return i + " (unowned)";
        else
            return i + " (" + b.getOwner().getName() + ")";
    }


    private void clearProperty(Gamers gamer) {
        List<Building> property = gamer.getProperty();
        for (Building building : property) {
            building.setOccupied(false);
        }
    }

    private void saveData(int curGamer) {
        stdOut.println("Please enter the file path");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        File file = new File(path);
        if (file.exists()) {
            stdOut.println("File already exist!");
            return;
        }
        boolean createSuc = true;
        if (!file.getParentFile().exists()) {
            createSuc = file.getParentFile().mkdirs();
        }
        if (!createSuc) {
            stdOut.println("File create failed");
            return;
        }

        try {
            createSuc = file.createNewFile();
        } catch (IOException e) {
            stdOut.println("File create failed");
            return;
        }
        if (!createSuc && !file.exists()) {
            stdOut.println("File create failed");
            return;
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            // 1. board status: hunmanNum, aiNum, curTurn, curGamer
            // 2. gamer name, position, money, jaildate, property, jailstatus, isalive
            String s = humanPlayerNum + " " + aiPlayerNum + " " + curTurn + " " + curGamer;
            bw.write(s + "\r\n");

            bw.write("\r\n");

            for (Gamers gamer : Gamers) {
                s = gamer.getName() + "-" + gamer.getPosition() + "-" + gamer.getMoney() + "-" + gamer.getJailDate()
                        + "-" + gamer.getProperty() + "-" + gamer.getJailStatus() + "-" + gamer.isAlive()
                        + "-" + ((gamer instanceof HumanPlayer) ? "Human" : "AI");
                bw.write(s + "\r\n");
            }
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
