package actor;

import control.Controller;
import control.TowerType;

// 子弹类
public class Bullet extends Actor{
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    protected int attackPower;

    protected boolean isCollide;
    protected int threshold;
    protected Monster targetMonster;
    protected TowerType parent;

    Bullet(int row, int column, int startX, int startY, int endX, int endY, int attackPower, TowerType parent) {
        super(row, column, startX, startY);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.attackPower = attackPower;
        this.threshold = 20;
        this.isCollide = false;
        this.parent = parent;
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
    public TowerType getParentType(){
        return parent;
    }
    // 判断是否碰撞
    public boolean getIsCollide(){
        return isCollide;
    }
    public Monster getTargetMonster(){
        return targetMonster;
    }

    public void setTargetMonster(Monster m){
        targetMonster = m;
    }

    // 子弹移动
    public void move(){
        double angle = Math.atan2(endY - y, endX - x);
        // x为长边
        if((Math.abs(angle - Math.PI) < Math.PI/4) || (Math.abs(angle + Math.PI) < Math.PI/4) || (Math.abs(angle) < Math.PI/4)){
            // 角度很小时
            if(Math.abs(angle - Math.PI) < Math.PI/100 || (Math.abs(angle + Math.PI) < Math.PI/100) || (Math.abs(angle) < Math.PI/100)){
                x += getDelta(x, endX);
            }
            else {
                y += getDelta(y, endY);
                x += (int) ( 1 / Math.tan(angle) * getDelta(y, endY));
            }
        }
        else{
            // 角度很大时
            if(Math.abs(angle - Math.PI/2) < Math.PI/100 || Math.abs(angle + Math.PI/2) < Math.PI/100){
                y += getDelta(y, endY);
            }
            else{
                x += getDelta(x, endX);
                y += (int) ( Math.tan(angle) * getDelta(x, endX));
            }
        }
        int dist = (int)Math.sqrt(Math.pow(endX - x,2) + Math.pow(endY - y,2));

        if(dist < threshold){
            isCollide = true;
            targetMonster.setHp(targetMonster.getHp() - attackPower);
        }
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(endX);
//        System.out.println(endY);
//        System.out.println(angle);
//        System.out.println(dist);
//        System.out.println("------");
    }

    private int getDelta(int start, int end){
        return start > end? -1 : 1;
    }
}
