package cmd;

import java.util.Scanner;

/**
 * a human player
 */
public class HumanPlayer extends Gamers {
    private StdOut stdOut = new StdOut(0);

    /**
     * @param id the id of the human player
     */
    public HumanPlayer(int id){
        super(id);
    }

    /**
     * @param id id of the human player
     * @param name name of the human player
     */
    public HumanPlayer(int id, String name){
        super(id, name);
    }
    @Override
    public int doAction() {
        stdOut.println(this.getName() + ", Do you want to:\n1: continue\n2: report\n3: auto\n4: retire\n5: save\n6: load");
        Scanner sc = new Scanner(System.in);
        String input;
        stdOut.println("Input your choice (1 - 6):");
        while(true){
            input = sc.next();
            if(input.length() == 1 && ('1' <= input.charAt(0) && input.charAt(0) <= '6'))
                break;
            stdOut.println("Please input between 1 - 6 !");
        }
        return input.charAt(0) - '0';
    }
}
