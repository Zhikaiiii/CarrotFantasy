package actor;

// 子弹类
public class Bullet extends Actor {
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    protected int attackPower;

    Bullet(int startX, int startY, int endX, int endY, int attackPower) {
        super(startX, startY);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.attackPower = attackPower;
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

    public void attack(){

    }
}
