package actor;

import control.Controller;

public class SunFlower extends Tower{
    private static final int cost = 200;
    public SunFlower(int row, int column, int x, int y) {
        super(row, column, x, y);
        this.attackPower = 30;
        this.attackRange = 1;
        this.attackInterval = 2;
        this.count = 5;
    }

    public static int getCost(){
        return cost;
    }
    @Override
    public void attack() {
        synchronized (Controller.allMonster){
            for(Monster m: Controller.allMonster){
                // 如果怪物位于范围内
                if(Math.abs(m.getRow() - row) <= attackRange && Math.abs(m.getColumn() - column) <= attackRange) {
                    // 更新怪物血量
                    m.setHp(m.getHp() - attackPower);
                }
            }
        }
    }
}
