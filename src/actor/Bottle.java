package actor;

import control.Controller;
import control.TowerType;
import ui.RotateJLabel;

public class Bottle extends Tower{
    private static final int buyPrice = 100;
    private static final int sellPrice = 80;
    public Bottle(int row, int column, int x, int y) {
        super(row, column, x, y);
        this.attackPower = 20;
        this.attackRange = 1;
        this.attackInterval = 20;
        this.count = 0;
        this.type = TowerType.BOTTLE;
    }

    public static int getBuyPrice(){
        return buyPrice;
    }
    public static int getSellPrice(){
        return sellPrice;
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
                // 瓶子转向
                RotateJLabel l = Controller.allTowerLabels.get(Controller.allTower.indexOf(this));
                double angle = Math.atan2(endY - y, endX - x);
                Controller.w.rotateTower(l, angle);

                // 生成新子弹
                Bullet b = new Bullet(row, column, startX, startY, endX, endY, attackPower, type);
                b.setTargetMonster(nearestMonster);
                Controller.allBulletLabels.add(Controller.w.addBullet(startX, startY));
//            Thread t = new Thread(b);
                Controller.allBullet.add(b);
            }
        }
    }
}
