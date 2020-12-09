package actor;

import control.Controller;

public class Monster extends Actor{
    // 移动速度
    protected int speed;
    // 位于道路上的第几个位置
    protected int position;
    // 金钱奖励
    protected int money;

    public Monster(int row, int column, int x, int y, int hp, int speed) {
        super(row, column, x, y, hp);
        this.speed = speed;
        this.position = 0;
        this.money = 10;
    }

    public int getSpeed(){
        return speed;
    }
    public int getPosition(){
        return position;
    }
    public int getMoney(){
        return money;
    }

    public void setSpeed(int speed){
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
//            if(this.hp <=0){
////                Controller.allMonster.remove(this);
//                Controller.updateMoney(this.money);
//            }
        }
    }
    // 怪物移动
    public void move(int dx, int dy){
        x = x + dx;
        y = y + dy;
    }
    // 判断是否到达萝卜
    public boolean isArrived(int endColumn, int endRow){
        return row == endRow && column == endColumn;
    }
}
