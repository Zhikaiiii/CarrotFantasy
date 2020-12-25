package actor;

import control.Controller;
import control.TowerType;

public class SnowFlower extends Tower {
    private static final int buyPrice = 150;
    private static final int sellPrice = 120;
    public SnowFlower(int row, int column, int x, int y) {
        super(row, column, x, y);
        this.attackPower = 0;
        this.attackRange = 1;
        this.attackInterval = 20;
        this.count = 0;
        this.type = TowerType.SNOWFLOWER;
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
            boolean hasTarget = false;
            for(Monster m: Controller.allMonster){
                // 如果怪物位于范围内
                if(Math.abs(m.getRow() - row) <= attackRange && Math.abs(m.getColumn() - column) <= attackRange) {
                    // 子弹(雪花的子弹是隐形的)
                    int startX = x;
                    int startY = y;
                    int endX = m.getX();
                    int endY = m.getY();
                    Bullet b = new FrozenBullet(row, column, startX, startY, endX, endY, type);
                    b.setTargetMonster(m);
                    synchronized (Controller.allBullet){
                        Controller.allBulletLabels.add(Controller.w.addBullet(startX, startY, type));
                        Controller.allBullet.add(b);
                    }
                    hasTarget = true;
                }
            }
            if(hasTarget){
                new Thread(() -> {
                    Controller.w.updateCircle(x, y, type);
                }).start();//开启线程
            }
        }
    }
}
