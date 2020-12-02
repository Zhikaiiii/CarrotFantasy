package actor;

import control.Controller;

public class Bottle extends Tower{
    public Bottle(int x, int y) {
        super(x, y);
        this.attackPower = 20;
        this.attackRange = 1;
        this.attackInterval = 1;
        this.cost = 100;
        this.cd = 3;
    }

    @Override
    public void attack() {
        // 找到最近的怪物
        int minDist = 1000000000;
        Monster nearestMonster = null;
        for(Monster m: Controller.allMonster){
            int dist = (int)Math.sqrt((m.getX() - x)^2 + (m.getY() - y)^2);
            if(dist < minDist){
                minDist = dist;
                nearestMonster = m;
            }
        }
        int startX = x;
        int startY = y;
        assert nearestMonster != null;
        int endX = nearestMonster.getX();
        int endY = nearestMonster.getY();
        Bullet b = new Bullet(startX, startY, endX, endY, attackPower);
        b.attack();
    }
}
