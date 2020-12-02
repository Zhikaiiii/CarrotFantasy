package actor;

import control.Controller;

public class SunFlower extends Tower{
    public SunFlower(int x, int y) {
        super(x, y);
        this.attackPower = 30;
        this.attackRange = 3;
        this.attackInterval = 2;
        this.cost = 200;
        this.cd = 5;
    }

    @Override
    public void attack() {

    }
}
