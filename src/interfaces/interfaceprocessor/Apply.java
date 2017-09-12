package interfaces.interfaceprocessor;
import java.util.*;

/*

*/

public class Apply {
    public static void process(Processor p, Object s){
        System.out.println("Using processor " + p.name());
        System.out.println(p.process(s));
    }
}
