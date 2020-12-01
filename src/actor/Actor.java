package actor;

public abstract class Actor {
    // 位置
    private int x;
    private int y;
    // 血量
    private int hp;

    // 构造函数
    Actor(int x, int y){
        this.x = x;
        this.y = y;
        this.hp = 1;
    }
    Actor(int x, int y, int hp){
        this.x = x;
        this.y = y;
        this.hp = hp;
    }

    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHp(){
        return hp;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
}
