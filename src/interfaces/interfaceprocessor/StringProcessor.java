package interfaces.interfaceprocessor;
import java.util.*;


public abstract class StringProcessor implements Processor{
    public String name(){
        return getClass().getSimpleName();
    }
    public static String s =
            "IntelliJ IDEA enables you to preserve and share your working environment";
    public static void main(String[] args){
        Apply.process(new Upcase(), s);
        Apply.process(new Downcase(), s);
        Apply.process(new Splitter(), s);
    }
}

class Upcase extends StringProcessor{
    public String process(Object input){
        return ((String)input).toUpperCase();
    }
}

class Downcase extends StringProcessor{
    public String process(Object input){
        return ((String)input).toLowerCase();
    }
}

class Splitter extends StringProcessor{
    public String process(Object input){
        return Arrays.toString(((String) input).split(" "));
    }
}
