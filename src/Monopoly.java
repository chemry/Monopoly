import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Monopoly {

    public static void main(String[] args){
        System.out.println("Monopoly!\n=====================");
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("Input number of human player (0 - 6):");
        while(true){
            input = sc.next();
            if(input.length() == 1 && ('0' <= input.charAt(0) && input.charAt(0) <= '6'))
                break;
            System.out.println("Please input between 0 - 6 !");
        }
        int humanNum = input.charAt(0) - '0';
        int leastAi = humanNum <= 1 ? 2 - humanNum : 0;
        int mostAi = 6 - humanNum;
        System.out.println("Input number of AI player (" + leastAi + " - " + mostAi + "):");
        while(true){
            input = sc.next();
            if(input.length() == 1 && ((leastAi + '0') <= input.charAt(0) && input.charAt(0) <= (mostAi + '0')))
                break;
            System.out.println("Please input between " + leastAi + " - " + mostAi + "!");
        }
        int aiNum = input.charAt(0) - '0';
        System.out.println("HUMAN: " + humanNum + "\nAI:" + aiNum);
        //sc.close();
        Game game = new Game(humanNum, aiNum);
        game.startGame();
    }
}
