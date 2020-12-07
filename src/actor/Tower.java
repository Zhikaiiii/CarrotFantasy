package actor;
import control.Controller;

public abstract class Tower extends Actor {
    // 攻击力
    protected int attackPower;
    protected int attackRange;
    protected double attackInterval;
    // 价格
    protected int cost;
    // 冷却时间
    protected int cd;

    Tower(int row, int column, int x, int y) {
        super(row, column, x, y);
    }

    public int getAttackPower(){
        return attackPower;
    }
    public int getAttackRange(){
        return attackRange;
    }
    public double getAttackInterval(){
        return attackInterval;
    }
    public int getCost(){
        return cost;
    }
    public int getCd(){
        return cd;
    }

    public abstract void attack();
}
