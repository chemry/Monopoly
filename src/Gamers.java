import java.util.ArrayList;
import java.util.List;

/**
 * an abstract class
 */
public abstract class Gamers {
    private static final int INITMONEY = 2000;

    private int totalStep = 0;
    private int position = 0;
    private int id = 0;
    private String name;
    private boolean isJailed = false;
    private int jailDate = 0;
    private boolean isAlive = true;
    private int money = INITMONEY;
    private List<Building> property;


    /**
     * @return the choice of the gamer, ai will always return 1, human player will return between 1 - 6
     */
    public abstract int doAction();

    /**
     * @param id the id of the gamer
     */
    public Gamers(int id){
        this.id = id;
        this.name = "P" + id;
        property = new ArrayList<>();
    }

    public Gamers(int id, String name){
        this.id = id;
        this.name = name;
        property = new ArrayList<>();
    }

    public int getPosition(){
        return position;
    }
    public String getName(){
        return name;
    }
    public boolean isAlive(){
        return isAlive;
    }
    public int getMoney(){
        return money;
    }
    public List<Building> getProperty(){
        return property;
    }
    public int getTotalStep(){
        return totalStep;
    }
    public boolean getJailStatus() {
        return isJailed;
    }
    public int getJailDate(){
        return jailDate;
    }
    public void addMoney(int amount){
        money += amount;
    }
    public void subMoney(int amount){
        if(money < amount){
            System.out.println("Player " + name + " is out of money!");
            isAlive = false;
            return;
        }
        money -= amount;
    }

    /**
     * @param position the position to be set
     */
    public void setPosition(int position){
        this.position = position;
    }

    public void reduceJailDate(){
        jailDate--;
    }

    public void setJailStatus(boolean status){
        this.isJailed = status;
        if(status)
            jailDate = 3;
    }

    public void setAlive(boolean alive){
        isAlive = alive;
    }

    public void setFree(){
        this.isJailed = false;
        jailDate = 0;
    }

    public void nextTurn(){
        totalStep++;
    }

    public void addProperty(Building building){
        property.add(building);
    }

    public void setProperty(List<Building> property){
        this.property = property;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public void setJailDate(int jailDate){
        this.jailDate = jailDate;
    }


    @Override
    public String toString() {
        String s = "Player: " + name;
        s += " Properties: "  + property.toString();
        return s;
    }
}
