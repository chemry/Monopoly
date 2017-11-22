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
    public void action(Gamers gamer, Game game) {
        if(gamer instanceof AiPlayer){
            SecureRandom rand = new SecureRandom();
            if(!isOccupied) {
                if (rand.nextBoolean() && gamer.getMoney() > price) {
                    gamer.addProperty(this);
                    gamer.subMoney(price);
                    isOccupied = true;
                    owner = gamer;
                }
                System.out.println("Player: " + gamer.getName() + " buy " + toString());
            } else {
                gamer.subMoney(price);
                System.out.println("Player: " + gamer.getName() + " gave " + price + " to player: " + owner.getName());
            }
        } else {
            Scanner sc = new Scanner(System.in);
            if(!isOccupied) {
                if (gamer.getMoney() > price) {
                    System.out.println("Do you want to buy " + this);
                    try {
                        int choice = sc.nextInt();
                    }catch (Exception e){
                        int a;
                    }
                    gamer.addProperty(this);
                    gamer.subMoney(price);
                    isOccupied = true;
                    owner = gamer;
                } else {
                    System.out.println("No enough Money to buy!");
                }

                System.out.println("Player: " + gamer.getName() + " buy " + toString());
            } else {
                gamer.subMoney(price);
                System.out.println("Player: " + gamer.getName() + " gave " + price + " to player: " + owner.getName());
            }
        }
    }
}
