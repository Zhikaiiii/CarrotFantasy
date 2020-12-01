package actor;

public class Bottle extends Tower{
    public Bottle(int x, int y) {
        super(x, y);
        this.attackPower = 20;
        this.attackRange = 1;
        this.attackInterval = 0.5;
        this.cost = 100;
        this.cd = 3;
    }

    @Override
    public void attack() {

    }
}
