package cmd;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * A building square
 */
public class Building extends Square {
    private boolean isOccupied = false;
    private Gamers owner;
    private int price;
    private int rent;
    private StdOut stdOut = new StdOut(0);
    /**
     * @param name the name of this square
     * @param price the price of this square
     * @param rent the rent of this square
     */
    public Building(String name, int price, int rent) {
        super(name);
        this.price = price;
        this.rent = rent;
    }

    @Override
    public void action(Gamers gamer) {
        if(gamer instanceof AiPlayer){
            SecureRandom rand = new SecureRandom();
            if(!isOccupied) {
                if (rand.nextBoolean() && gamer.getMoney() > price) {
                    buyThis(gamer);
                }
            } else if (!gamer.equals(owner)){
                gaveRent(gamer);
            }
        } else {
            Scanner sc = new Scanner(System.in);
            if(!isOccupied) {
                if (gamer.getMoney() > price) {
                    stdOut.print("Do you want to buy " + this + "? price: " + price);
                    stdOut.println(" (remain money: " + gamer.getMoney() + ")");
                    String choice;
                    while(true){
                        choice = sc.next();
                        if(choice.equals("1") || choice.equals("0"))
                            break;
                        stdOut.println("Please input 0 or 1 !");
                    }
                    if(choice.equals("1")) {
                        buyThis(gamer);
                    }
                } else {
                    stdOut.println("No enough Money to buy!");
                }
            } else if (!gamer.equals(owner)){
                gaveRent(gamer);
            }
            //sc.close();
        }
    }

    private void buyThis(Gamers gamer){
        gamer.addProperty(this);
        gamer.subMoney(price);
        isOccupied = true;
        owner = gamer;
        stdOut.print("Player " + gamer.getName() + " buy " + toString());
        stdOut.println(" (remain money: " + gamer.getMoney() + ")");
    }

    private void gaveRent(Gamers gamer){
        gamer.subMoney(rent);
        owner.addMoney(rent);
        stdOut.print("Player " + gamer.getName() + " gave " + rent + " to player: " + owner.getName());
        stdOut.println(" (player " + gamer.getName() + " remain money: " + gamer.getMoney() + ", " +
                "player " + owner.getName() + " remain money: " + owner.getMoney() + ")");
    }

    /**
     * @param gamer set the owenr of the building
     */
    public void setOwner(Gamers gamer){
        owner = gamer;
        this.isOccupied = true;
    }

    /**
     * @param occupied the status of the building
     */
    public void setOccupied(boolean occupied){
        this.isOccupied = occupied;
    }

    /**
     * @return the owenr of the building
     */
    public Gamers getOwner(){
        return owner;
    }

    /*public boolean isOccupied(){
        return isOccupied;
    }*/

    /**
     * @return the name of the building
     */
    public String getName(){
        return super.toString();
    }

    /**
     * @param caller set the output way
     */
    public void setStdOut(int caller){
        stdOut.setCaller(caller);
    }


}
