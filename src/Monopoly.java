import java.util.Scanner;

/**
 * the Main class for CMD version
 */
public class Monopoly {
    private static final int NAME_MAX_LENGTH = 10;

    /**
     * @param args parameter of main
     */
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
        //System.out.println("HUMAN: " + humanNum + "\nAI:" + aiNum);
        String[] humanNames = new String[humanNum];
        String[] aiNames = new String[aiNum];
        for(int i = 0; i < humanNum; i++){
            System.out.println("Please input P" + (i + 1) + "'s name (1 - 10 characters):");
            humanNames[i] = sc.next();
            while(humanNames[i].length() > NAME_MAX_LENGTH){
                System.out.println("input 1 - 14 characters:");
                humanNames[i] = sc.next();
            }
        }
        for(int i = 0; i < aiNum; i++){
            aiNames[i] = (humanNum + i + 1) + "";
        }

        //sc.close();
        Game game = new Game(humanNames, aiNames);
        int result = game.startGame();
        while(result == -1) {
            game = new Game();
            result = game.startGame();
            while(result == 0){
                game = new Game();
                result = game.startGame();
            }
        }
        System.out.println("Game Finished!!!!!!");
    }
}
