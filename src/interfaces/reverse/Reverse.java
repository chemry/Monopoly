package interfaces.reverse;

public class Reverse {
    /*private String s;
    public Reverse(String s){
        this.s = s;
    }*/
    public String name(){
        return getClass().getSimpleName();
    }

    public String process(String s){

        char[] ans = s.toCharArray();
        int l = (s).length();
        for(int i = 0; i < l/2; i++){
            char temp = ans[i];
            ans[i] = ans[l - i - 1];
            ans[l - i - 1] = temp;
        }
        return new String(ans);

    }


    public static void main(String[] args){

    }

}
