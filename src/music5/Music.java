package music5;
interface Instrument{
    int VALUE = 5;
    void play(Note n);
    void adjust();
}


class Wind implements Instrument{
    public void play(Note n){
        System.out.println(this + "play" + n);
    }
    public void adjust() {
        System.out.println(this + "adjust");
    }
    @Override
    public String toString() {
        return "Wind";
    }
}


class Percussion implements Instrument{
    public void play(Note n){
        System.out.println(this + "play" + n);
    }
    public void adjust() {
        System.out.println(this + "adjust");
    }
    @Override
    public String toString() {
        return "Percussion";
    }
}

class Brass extends Wind{
    @Override
    public String toString() {
        return "Bass";
    }
}
public class Music {
    static void tune(Instrument i){
        i.play(Note.C);
    }
    static void tuneAll(Instrument[] e){
        for(Instrument i : e){
            tune(i);
        }
    }

    public static void main(String[] args){
        Instrument[] Something = {
                new Wind(),
                new Wind(),
                new Brass(),
                new Percussion(),
        };
        tuneAll(Something);
    }
}
