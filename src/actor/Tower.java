package actor;

import control.TowerType;

public abstract class Tower extends Actor {
    // 攻击力
    protected int attackPower;
    protected double attackRange;

    // 冷却时间
    protected int attackInterval;
    protected int count;

    protected TowerType type;


    Tower(int row, int column, int x, int y) {
        super(row, column, x, y);
    }

    public int getAttackPower(){
        return attackPower;
    }
    public double getAttackRange(){
        return attackRange;
    }
    public int getAttackInterval(){
        return attackInterval;
    }
    public TowerType getTowerType() {
        return type;
    }
    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }
    public abstract void attack();

}
