package actor;

import control.Controller;
import control.TowerType;

public class SunFlower extends Tower{
    private static final int buyPrice = 180;
    private static final int sellPrice = 150;
    public SunFlower(int row, int column, int x, int y) {
        super(row, column, x, y);
        this.attackPower = 30;
        this.attackRange = 1.5;
        this.attackInterval = 20;
        this.count = 0;
        this.type = TowerType.SUNFLOWER;
    }

    public static int getBuyPrice(){
        return buyPrice;
    }
    public static int getSellPrice(){
        return sellPrice;
    }
    @Override
    public void attack() {
        synchronized (Controller.allBullet) {
            synchronized (Controller.allMonster) {
                boolean hasTarget = false;
                for (Monster m : Controller.allMonster) {
                    // 如果怪物位于范围内
                    if (Math.abs(m.getX() - x) <= attackRange * Controller.interval && Math.abs(m.getY() - y) <= attackRange * Controller.interval) {
                        // 子弹(太阳花的子弹是隐形的)
                        int startX = x;
                        int startY = y;
                        int endX = m.getX();
                        int endY = m.getY();
                        Bullet b = new Bullet(row, column, startX, startY, endX, endY, attackPower, type);
                        b.setTargetMonster(m);
//                        synchronized (Controller.allBullet) {
                        Controller.allBulletLabels.add(Controller.w.addBullet(startX, startY, type));
                        Controller.allBullet.add(b);
//                        }
                        hasTarget = true;
                    }
                }
                if (hasTarget) {
                    new Thread(() -> {
                        Controller.updateCircle(x, y, type);
                    }).start();//开启线程
                }
            }
        }
    }
}
