import java.util.*;

/**
 * use to record the status of a game
 */
public class Game {
    private static final int SQUARE_NUM = 20;
    private static final int MAX_TURN = 100;

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
    private int curGamer = 0;

    /**
     * @param humanPlayerNum the number of human player
     * @param aiPlayerNum    the number of AI player
     */
    public Game(int humanPlayerNum, int aiPlayerNum) {
        Square = new Square[SQUARE_NUM];
        //Gamers = new ArrayList<>();
        totalPlayer = humanPlayerNum + aiPlayerNum;

        this.humanPlayerNum = humanPlayerNum;
        this.aiPlayerNum = aiPlayerNum;

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
                    //System.out.println(i);
                    Square[i] = new Building(squareName[i], buildingPrice[i][0], buildingPrice[i][1]);
                    break;
            }
        }
        Gamers = new Gamers[totalPlayer];
        int id = 1;
        for(int i = 0; i < humanPlayerNum; i++)
            Gamers[i] = new HumanPlayer(id++);
        for(int i = 0; i < aiPlayerNum; i++)
            Gamers[i + humanPlayerNum] = new AiPlayer(id++);


    }

    /**
     * start the game
     * @return the status of the game,
     */
    public int startGame(){
        System.out.println("Game Start!");
        boolean isFinish = false;
        while(!isFinish){
            //do something
            for (int i = 0; i < totalPlayer; i++) {
                System.out.println("here: " + Arrays.toString(Gamers));
                if (isFinish || !Gamers[i].isAlive()) {
                    //System.out.println(Gamers[i].getName() + " " + isFinish + " " + Gamers[i].isAlive());
                    continue;
                }
                int choice = 0;
                while (choice != 1){
                    choice = Gamers[i].doAction();
                    if(choice == 2) {
                        drawBoard();
                        //continue;
                    } else if (choice == 3){
                        System.out.println("change player");
                        List<Building> property = Gamers[i].getProperty();
                        Gamers[i] = new AiPlayer(i + 1);
                        Gamers[i].setProperty(property);
                        for(Building building : property){
                            building.setOwner(Gamers[i]);

                        }
                        System.out.println(Gamers[i]);
                    } else if(choice == 4){
                        System.out.println("clear property");
                        List<Building> property = Gamers[i].getProperty();
                        Gamers[i].setAlive(false);
                        clearProperty(Gamers[i]);
                        break;
                    }
                }
                if (!Gamers[i].isAlive()) {
                    continue;
                }
                if(!Gamers[i].getJailStatus()) {
                    moveGamer(Gamers[i]);
                } else {
                    int prePos = Gamers[i].getPosition();
                    Square[prePos].action(Gamers[i]);
                    int curPos = Gamers[i].getPosition();
                    if(prePos != curPos){
                        System.out.println("Player " + Gamers[i].getName() + " moves to square No." + (curPos + 1) + ": " + Square[curPos]);
                        Square[curPos].action(Gamers[i]);
                        if(Gamers[i].isAlive()){
                            Gamers[i].nextTurn();
                        }
                    }
                }
                if (getWinner() != null)
                    isFinish = true;

            }

            curTurn++;
        }
        System.out.println(Arrays.toString(getWinner().toArray()));
        return 0;
    }

    /**
     * @return the game status
     */
    public boolean isGameEnd(){
        return false;
    }

    void moveGamer(Gamers gamer){
        Dice dice = new Dice();
        int step = dice.spin();
        step += dice.spin();

        int newPosition = gamer.getPosition() + step;
        newPosition %= SQUARE_NUM;
        gamer.setPosition(newPosition);
        System.out.println("Player " + gamer.getName() + " moves to square No." + newPosition + ": " + Square[newPosition]);
        Square[gamer.getPosition()].action(gamer);
        if(gamer.isAlive()){
            gamer.nextTurn();
        }
    }

    /*private boolean doubleFace(Gamers gamer){
        return false;
    }*/

    /**
     * @return the list of the winner (may have more than one)
     */
    public List<Gamers> getWinner(){
        if(this.curTurn >= MAX_TURN){
            return getMaxMoneyGamer();
        }

        int alive = 0;
        Gamers winner = null;
        for(Gamers gamer : Gamers){
            if(gamer.isAlive()) {
                alive++;
                winner = gamer;
            }
        }

        if(alive == 1) {
            List<Gamers> gList = new LinkedList<>();
            gList.add(winner);
            return gList;
        }

        return null;
    }

    private List<Gamers> getMaxMoneyGamer(){
        List<Gamers> gList = new LinkedList<>();
        int maxMoney = 0;
        for(Gamers gamer : Gamers){
            if(gamer.getMoney() > maxMoney){
                gList = new LinkedList<>();
                maxMoney = gamer.getMoney();
            }
            if(gamer.getMoney() == maxMoney){
                gList.add(gamer);
            }
        }
        return gList;
    }

    private void drawBoard(){
        System.out.println("A board");
    }

    private void clearProperty(Gamers gamer){
        List<Building> property = gamer.getProperty();
        for(Building building: property){
            building.setOccupied(false);
        }
    }

}
