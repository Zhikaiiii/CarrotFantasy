package actor;

import control.Controller;

// 子弹类
public class Bullet extends Actor implements Runnable {
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    protected int attackPower;

    protected boolean isCollide;
    protected int threshold;
    protected Monster targetMonster;

    Bullet(int row, int column, int startX, int startY, int endX, int endY, int attackPower) {
        super(row, column, startX, startY);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.attackPower = attackPower;
        this.threshold = 5;
        this.isCollide = false;
    }

    public int getStartX(){
        return startX;
    }
    public int getStartY(){
        return startY;
    }
    public int getEndX(){
        return endX;
    }
    public int getEndY(){
        return endY;
    }
    // 判断是否碰撞
    public boolean getIsCollide(){
        return isCollide;
    }

    public void setTargetMonster(Monster m){
        targetMonster = m;
    }

    // 子弹移动
    public void move(){

    }

    @Override
    public void run() {
        while(!isCollide){
            move();
            int dist = (int)Math.sqrt((targetMonster.getX() - x)^2 + (targetMonster.getY() - y)^2);
            if(dist < threshold){
                isCollide = true;
            }
        }
        // 更新怪物血量
        targetMonster.setHp(targetMonster.getHp() - attackPower);

    }
}
