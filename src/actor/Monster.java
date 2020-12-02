package actor;

public class Monster extends Actor{
    // 移动速度
    protected int speed;
    // 位于道路上的第几个位置
    protected int position;

    public Monster(int x, int y, int hp, int speed) {
        super(x, y, hp);
        this.speed = speed;
        this.position = 0;
    }

    public int getSpeed(){
        return speed;
    }
    public int getPosition(){
        return position;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }
    public void setPosition(int position){
        this.position = position;
    }

    // 需要判断怪物是否死亡
    public void setHp(int hp){

    }
    // 怪物移动
    public void move(int dx, int dy){
        x = x + dx;
        y = y + dy;
    }
    // 判断是否到达萝卜
    public boolean isArrived(){
        return false;
    }
}
