package actor;

import control.Controller;

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

    Bullet(int row, int column, int startX, int startY, int endX, int endY, int attackPower) {
        super(row, column, startX, startY);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.attackPower = attackPower;
        this.threshold = 20;
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
        double angle = Math.atan2(endY - y, endX - x);
        // x为长边
        if((Math.abs(angle - Math.PI) < Math.PI/4) || (Math.abs(angle + Math.PI) < Math.PI/4) || (Math.abs(angle) < Math.PI/4)){
            // 角度很大时
            if(Math.abs(angle - Math.PI) < Math.PI/100 || (Math.abs(angle + Math.PI) < Math.PI/100) || (Math.abs(angle) < Math.PI/100)){
                x += getDelta(x, endX);
            }
            else {
                y += getDelta(y, endY);
                x += (int) Math.tan(angle) * getDelta(y, endY);
            }
        }
        else{
            // 角度很大时
            if(Math.abs(angle - Math.PI/2) < Math.PI/100 || (Math.abs(angle + Math.PI/2) < Math.PI/100)){
                y += getDelta(y, endY);
            }
            else{
                x += getDelta(x, endX);
                y += (int) Math.tan(angle) * getDelta(x, endX);
            }
        }
        int dist = (int)Math.sqrt(Math.pow(endX - x,2) + Math.pow(endY - y,2));
//        double angle = Math.atan2(targetMonster.getY() - y, targetMonster.getX() - x);
//        // x为长边
//        if((Math.abs(angle - Math.PI) < Math.PI/4) || (Math.abs(angle + Math.PI) < Math.PI/4) || (Math.abs(angle) < Math.PI/4)){
//            // 角度很大时
//            if(Math.abs(angle - Math.PI) < Math.PI/100 || (Math.abs(angle + Math.PI) < Math.PI/100) || (Math.abs(angle) < Math.PI/100)){
//                x += getDelta(x, targetMonster.getX());
//            }
//            else {
//                y += getDelta(y, targetMonster.getY());
//                x += (int) Math.tan(angle) * getDelta(y, targetMonster.getY());
//            }
//        }
//        else{
//            // 角度很大时
//            if(Math.abs(angle - Math.PI/2) < Math.PI/100 || (Math.abs(angle + Math.PI/2) < Math.PI/100)){
//                y += getDelta(y, targetMonster.getY());
//            }
//            else{
//                x += getDelta(x, targetMonster.getX());
//                y += (int) Math.tan(angle) * getDelta(x, endX);
//            }
//        }
//        int dist = (int)Math.sqrt(Math.pow(targetMonster.getX() - x,2) + Math.pow(targetMonster.getY() - y,2));
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

//    @Override
//    public void run() {
//        while(!isCollide){
//            move();
//
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        // 更新怪物血量
//        targetMonster.setHp(targetMonster.getHp() - attackPower);
//    }

    private int getDelta(int start, int end){
        return start > end? -1 : 1;
    }
}
