package actor;

import control.Controller;
import control.TowerType;

public class FrozenBullet extends Bullet{
    FrozenBullet(int row, int column, int startX, int startY, int endX, int endY, TowerType parent) {
        super(row, column, startX, startY, endX, endY, 0, parent);
    }

    // 子弹移动
    public void update(){
        move();
        int dist = (int)Math.sqrt(Math.pow(endX - x,2) + Math.pow(endY - y,2));
        if(dist < threshold){
            isCollide = true;
            new Thread(() -> {
                // 减速
                targetMonster.setSpeed(targetMonster.getSpeed() * 0.5);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                targetMonster.setSpeed(targetMonster.getSpeed() / 0.5);

            }).start();//开启线程

        }
    }
}
