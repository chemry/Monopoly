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
            } else {
                gaveRent(gamer);
            }
        } else {
            Scanner sc = new Scanner(System.in);
            if(!isOccupied) {
                if (gamer.getMoney() > price) {
                    System.out.println("Do you want to buy " + this);
                    String choice;
                    while(true){
                        choice = sc.next();
                        if(choice.equals("1") || choice.equals("0"))
                            break;
                        System.out.println("Please input 0 or 1 !");
                    }
                    if(choice.equals("1")) {
                        buyThis(gamer);
                    }
                } else {
                    System.out.println("No enough Money to buy!");
                }
            } else {
                gaveRent(gamer);
            }
            sc.close();
        }
    }

    private void buyThis(Gamers gamer){
        gamer.addProperty(this);
        gamer.subMoney(price);
        isOccupied = true;
        owner = gamer;
        System.out.println("Player: " + gamer.getName() + " buy " + toString());
    }

    private void gaveRent(Gamers gamer){
        gamer.subMoney(rent);
        owner.addMoney(rent);
        System.out.println("Player: " + gamer.getName() + " gave " + price + " to player: " + owner.getName());
    }

}
