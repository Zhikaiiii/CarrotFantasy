package actor;

public class Monster extends Actor{
    // 移动速度
    private int speed;

    public Monster(int x, int y, int hp, int speed) {
        super(x, y, hp);
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    // 需要判断怪物是否死亡
    public void setHp(int hp){

    }
    // 怪物移动
    public void move(){

    }
    // 判断是否到达萝卜
    public boolean isArrived(){
        return false;
    }
}
