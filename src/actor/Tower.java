package actor;

public abstract class Tower extends Actor {
    // 攻击力
    protected int attackPower;
    protected int attackRange;
    protected double attackInterval;
    // 冷却时间
    protected int count;

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

    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }
    public abstract void attack();
}
