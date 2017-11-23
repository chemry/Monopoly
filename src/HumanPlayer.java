import java.util.Scanner;

public class HumanPlayer extends Gamers {
    public HumanPlayer(int id){
        super(id);
    }
    @Override
    public int doAction() {
        System.out.println("Do you want to:\n1: continue\n2: report\n3: auto\n4: retire\n5: save\n6: load");
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("Input your choice (1 - 6):");
        while(true){
            input = sc.next();
            if(input.length() == 1 && ('1' <= input.charAt(0) && input.charAt(0) <= '6'))
                break;
            System.out.println("Please input between 1 - 6 !");
        }
        return input.charAt(0) - '0';
    }
}
