package actor;

import control.Controller;
import control.TowerType;

public class Arrow extends Bottle{
    protected int targetNum;

    private static final int buyPrice = 220;
    private static final int sellPrice = 160;

    public Arrow(int row, int column, int x, int y) {
        super(row, column, x, y);
        this.attackPower = 30;
        this.attackRange = 3;
        this.attackInterval = 20;
        this.type = TowerType.ARROW;

        this.count = 0;
        this.targetNum = 3;
    }

    public static int getBuyPrice(){
        return buyPrice;
    }
    public static int getSellPrice(){
        return sellPrice;
    }


    @Override
    // 只发射3支弓箭
    public void attack() {
        Monster nearestMonster = getNearestMonster();
        // 攻击最接近的怪物
        super.attack();
        if(nearestMonster != null){
            double angle = Math.atan2(nearestMonster.getY()- y, nearestMonster.getX() - x);

            // 60°夹角范围 [angle1, angle2]
            double angle1 = angle - Math.PI/6;
            double angle2 = angle + Math.PI/6;
            if (angle1 <= -Math.PI){
                angle1 += 2*Math.PI;
            }
            if(angle2 >= Math.PI){
                angle2 -= 2*Math.PI;
            }
            int currTargetNum = 1;
            synchronized (Controller.allBullet) {
                synchronized (Controller.allMonster) {
                    for (Monster m : Controller.allMonster) {
                        if (currTargetNum == targetNum) {
                            break;
                        }
                        if (Math.abs(m.getX() - x) <= attackRange * Controller.interval && (Math.abs(m.getY() - y) <= attackRange * Controller.interval)) {
                            double newAngle = Math.atan2(m.getY() - y, m.getX() - x);
                            //与第一个目标不同且在夹角范围内
                            if (m != nearestMonster && ((newAngle > angle1 && newAngle < angle2) || newAngle > angle1 || newAngle < angle2)) {
//                                synchronized (Controller.allBullet) {
                                int startX = x;
                                int startY = y;
                                int endX = m.getX();
                                int endY = m.getY();
                                // 生成新子弹
                                Bullet b = new Bullet(row, column, startX, startY, endX, endY, attackPower, type);
                                b.setTargetMonster(m);
                                Controller.allBulletLabels.add(Controller.w.addBullet(startX, startY, type));
                                Controller.allBullet.add(b);
                                Controller.rotate(b, newAngle);
//                                }
                                currTargetNum += 1;
                            }
                        }
                    }
                }
            }
        }

    }

}
