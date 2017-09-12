package interfaces.filters;

public class Waveform {
    private static long counter = 0;
    private final long id = ++counter;
    public String toString(){
        return "Waveform " + id;
    }
}
