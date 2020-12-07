package actor;

import control.Controller;

public class Bottle extends Tower{
    public Bottle(int row, int column, int x, int y) {
        super(row, column, x, y);
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
        synchronized (Controller.allMonster) {
            for (Monster m : Controller.allMonster) {
                int dist = (int) Math.sqrt((m.getX() - x) ^ 2 + (m.getY() - y) ^ 2);
                if (dist < minDist) {
                    minDist = dist;
                    nearestMonster = m;
                }
            }
        }
        synchronized (Controller.allBullet){
            int startX = x;
            int startY = y;
            assert nearestMonster != null;
            int endX = nearestMonster.getX();
            int endY = nearestMonster.getY();
            Bullet b = new Bullet(row, column, startX, startY, endX, endY, attackPower);
            Thread t = new Thread(b);
            Controller.allBullet.add(t);
            t.start();
        }

    }
}
