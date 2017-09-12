package interfaces;
interface CanFight{
    void fight();
}

interface CanEat{
    void eat();
}

interface CanFly{
    void fly();
}

class Character{
    public void fight() {}
}

class Hero extends Character
    implements CanFight, CanEat, CanFly{
    public void eat() {}
    public void fly() {}
}

public class Adventure {
    public static void t(CanFight x) {x.fight();}
    public static void main(String[] args) {
        Hero h = new Hero();
        t(h);
    }

}
