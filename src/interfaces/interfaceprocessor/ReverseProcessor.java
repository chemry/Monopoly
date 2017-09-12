package interfaces.interfaceprocessor;
import interfaces.reverse.Reverse;

class ReverseAdapter implements Processor{
    Reverse reverse;
    public ReverseAdapter(Reverse reverse){
        this.reverse = reverse;
    }

    @Override
    public String name() {
        return reverse.name();
    }

    @Override
    public String process(Object input) {
        return reverse.process((String)input);
    }
}


public class ReverseProcessor {
    public static String s =
            "IntelliJ IDEA enables you to preserve and share your working environment";
    public static void main(String[] args){
        Apply.process(new ReverseAdapter(new Reverse()), s);

    }

}
