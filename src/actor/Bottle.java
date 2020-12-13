package actor;

import control.Controller;

public class Bottle extends Tower{
    private static final int cost = 100;
    public Bottle(int row, int column, int x, int y) {
        super(row, column, x, y);
        this.attackPower = 20;
        this.attackRange = 1;
        this.attackInterval = 20;
        this.count = 3;
    }

    public static int getCost(){
        return cost;
    }
    @Override
    public void attack() {
        // 找到最近的怪物
        int minDist = 1000000000;
        Monster nearestMonster = null;
        synchronized (Controller.allMonster) {
            for (Monster m : Controller.allMonster) {
                if(Math.abs(m.getColumn() - column) <= 2 && (Math.abs(m.getRow() - row) <= 2)){
                    int dist = (int) Math.sqrt(Math.pow(m.getX() - x, 2) + Math.pow(m.getY() - y, 2));
                    if (dist < minDist) {
                        minDist = dist;
                        nearestMonster = m;
                    }
                }

            }
        }
        if(nearestMonster != null){
            synchronized (Controller.allBullet){
                int startX = x;
                int startY = y;
                int endX = nearestMonster.getX();
                int endY = nearestMonster.getY();
//                System.out.println(startX);
//                System.out.println(startY);
//                System.out.println(endX);
//                System.out.println(endY);
                Bullet b = new Bullet(row, column, startX, startY, endX, endY, attackPower);
                b.setTargetMonster(nearestMonster);
                Controller.allBulletLabels.add(Controller.w.addBullet(startX, startY));
//            Thread t = new Thread(b);
                Controller.allBullet.add(b);
            }
        }


    }
}
