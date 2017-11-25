package cmd;

/**
 * used to balance GUI and CMD output
 */
public class StdOut {

    private int caller;

    /**
     * @param caller 1 for gui and 0 for cmd
     */
    public StdOut(int caller){
        this.caller = caller;
    }

    /**
     * @return the output String for gui
     * @param s the string to print with a \n at last
     */
    public String println(String s){
        if(caller == 0) {
            System.out.println(s);
            return null;
        } else {
            return s + "\n";
        }
    }

    /**
     * @return the output String for gui
     * @param s the string to print without \n at last
     */
    public String print(String s){
        if(caller == 0){
            System.out.print(s);
            return null;
        } else {
            return s;
        }
    }

    /**
     * @param caller set the caller
     */
    public void setCaller(int caller){
        this.caller = caller;
    }

}
