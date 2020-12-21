package actor;

import control.Controller;
import control.TowerType;

public class SunFlower extends Tower{
    private static final int buyPrice = 200;
    private static final int sellPrice = 160;
    public SunFlower(int row, int column, int x, int y) {
        super(row, column, x, y);
        this.attackPower = 30;
        this.attackRange = 1;
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
        synchronized (Controller.allMonster){
            for(Monster m: Controller.allMonster){
                // 如果怪物位于范围内
                if(Math.abs(m.getRow() - row) <= attackRange && Math.abs(m.getColumn() - column) <= attackRange) {
                    // 子弹(太阳花的子弹是隐形的)
                    int startX = x;
                    int startY = y;
                    int endX = m.getX();
                    int endY = m.getY();
                    Bullet b = new Bullet(row, column, startX, startY, endX, endY, attackPower, type);
                    b.setTargetMonster(m);
                    synchronized (Controller.allBullet){
                        Controller.allBulletLabels.add(Controller.w.addBullet(startX, startY));
                        Controller.allBullet.add(b);
                    }
                    new Thread(() -> {
                        Controller.w.updateFire(x, y);
                    }).start();//开启线程

                }
            }
        }
    }
}
