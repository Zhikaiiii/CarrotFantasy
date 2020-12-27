package actor;

import control.Controller;

public class Monster extends Actor{
    // 移动速度
    protected double speed;
    protected int count;

    // 位于道路上的第几个位置
    protected int position;
    // 金钱奖励
    protected int money;

    public Monster(int row, int column, int x, int y, int hp, int speed) {
        super(row, column, x, y, hp);
        this.speed = speed;
        this.position = 0;
        this.money = 12;
        this.count = 0;
    }

    public double getSpeed(){
        return speed;
    }
    public int getPosition(){
        return position;
    }
    public int getMoney(){
        return money;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }
    public void setPosition(int position){
        this.position = position;
    }

    // 需要判断怪物是否死亡
    public void setHp(int hp){
        // 可能有多个防御塔同时攻击怪物
        synchronized (this){
            this.hp = hp;
        }
    }
    // 怪物移动
    public void move(int dx, int dy){

        // 移动速度*时间
        if(count * speed > 10){
            x = x + dx;
            y = y + dy;
            count = 0;
        }
        else{
            count += 1;
        }

    }
    // 判断是否到达萝卜
    public boolean isArrived(int endColumn, int endRow){
        return row == endRow && column == endColumn;
    }
}
