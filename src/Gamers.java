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

    /**
     * @param id id of a player
     * @param name name of the player
     */
    public Gamers(int id, String name){
        this.id = id;
        this.name = name;
        property = new ArrayList<>();
    }

    /**
     * @return position of the gamer
     */
    public int getPosition(){
        return position;
    }

    /**
     * @return name of the gamers=
     */
    public String getName(){
        return name;
    }

    /**
     * @return is the gamer alive?
     */
    public boolean isAlive(){
        return isAlive;
    }

    /**
     * @return moeny of the gamer
     */
    public int getMoney(){
        return money;
    }

    /**
     * @return the properties of the gamer
     */
    public List<Building> getProperty(){
        return property;
    }
    /*public int getTotalStep(){
        return totalStep;
    }*/

    /**
     * @return is the gamer in jail?
     */
    public boolean getJailStatus() {
        return isJailed;
    }

    /**
     * @return jail day remains
     */
    public int getJailDate(){
        return jailDate;
    }

    /**
     * @param amount money to be added
     */
    public void addMoney(int amount){
        money += amount;
    }

    /**
     * @param amount money to be subtracted
     */
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

    /**
     * reduce the jail date by 1
     */
    public void reduceJailDate(){
        jailDate--;
    }

    /**
     * @param status send the people in to jail
     */
    public void setJailStatus(boolean status){
        this.isJailed = status;
        if(status)
            jailDate = 3;
    }

    /**
     * @param alive the status of the gamer
     */
    public void setAlive(boolean alive){
        isAlive = alive;
    }

    /**
     * free the gamer from jail
     */
    public void setFree(){
        this.isJailed = false;
        jailDate = 0;
    }

    /**
     * add the gamer's step
     */
    public void nextTurn(){
        totalStep++;
    }

    /**
     * @param building add one building to gamers' property
     */
    public void addProperty(Building building){
        property.add(building);
    }

    /**
     * @param property set the property of the gamer
     */
    public void setProperty(List<Building> property){
        this.property = property;
    }

    /**
     * @param money set the money of the gamer
     */
    public void setMoney(int money){
        this.money = money;
    }

    /**
     * @param jailDate set the jaildate of the gamer
     */
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
